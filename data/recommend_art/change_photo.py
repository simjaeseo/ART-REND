import base64
import cv2
from PIL import Image

import torch
from .cycleGAN.model import CycleGAN
from .cycleGAN.checkpoint import load_checkpoint
from .cycleGAN.load_data import PhotoDataset, stringtoRGB
from .cycleGAN.functions import unnorm
from torch.utils.data import DataLoader
import torchvision.transforms as transforms
import os
from recommend_art.models import ChangedPainting
def check_artist(artist:str)-> str:
    return '{}.ckpt'.format(artist)


def upload_image_encode_base64(path):
    # img = cv2.imread(path)
    print(path)
    print(type(path))
    jpg_img = cv2.imencode('.jpg',path)
    b64_string = base64.b64encode(jpg_img[1]).decode('utf-8')
    return b64_string

def image_encode_base64(path):
    img = cv2.imread(path)
    print(img)
    print(type(img))
    jpg_img = cv2.imencode('.jpg',img)
    b64_string = base64.b64encode(jpg_img[1]).decode('utf-8')
    return b64_string


def change_p(artist: str, request_image: base64, member_id: int):
    ckpt = check_artist(artist)
    device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
    BASE_PATH = os.path.dirname((os.path.abspath(__file__)))
    ckpt = load_checkpoint(BASE_PATH+'\checkpoint_art\{}'.format(ckpt), map_location=device)
    gan = CycleGAN(3,3,100, device)
    gan.epochs=ckpt['epoch']
    gan.gen_mtp.load_state_dict(ckpt['gen_mtp'])
    gan.gen_ptm.load_state_dict(ckpt['gen_ptm'])
    gan.desc_m.load_state_dict(ckpt['desc_m'])
    gan.desc_p.load_state_dict(ckpt['desc_p'])
    gan.adam_gen.load_state_dict(ckpt['optimizer_gen'])
    gan.adam_desc.load_state_dict(ckpt['optimizer_desc'])
    image = stringtoRGB(request_image)
    # print(image.shape)
    image = Image.fromarray(image)
    custom_transform = transforms.Compose([
                transforms.Resize((256,256)),
                transforms.ToTensor(),
                transforms.Normalize((0.5, 0.5, 0.5), (0.5, 0.5, 0.5))                                
            ])
    image = custom_transform(image)
    image = image.reshape(1,3,256,256)
    ph_dl = DataLoader(image, batch_size=1, pin_memory=True)
    trans = transforms.ToPILImage()
    for photo in ph_dl:
        with torch.no_grad():
            pred_artist = gan.gen_ptm(photo.to(device)).cpu().detach()
        pred_artist = unnorm(pred_artist)
        img = trans(pred_artist[0]).convert("RGB")
    img.save(BASE_PATH + f'\photo\{member_id} {artist}.jpg')
    path = BASE_PATH + f'\photo\{member_id} {artist}.jpg'
    # return path
    b64_string=image_encode_base64(path)
    return b64_string
    
    
    

# if __name__ == '__main__':
#     b64_string = image_encode_base64('./test/1cdb990c46.jpg')
#     img = change_p('Van Gogh',b64_string)
#     print(img)