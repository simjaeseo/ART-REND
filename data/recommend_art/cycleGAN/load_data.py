import base64
import cv2
import io
import numpy as np

import os
import torchvision.models as models
import torchvision.transforms as transforms
from PIL import Image
from torch.utils.data import Dataset, random_split, DataLoader

class PhotoDataset(Dataset):
    def __init__(self, photo_dir, size=(256, 256), normalize=True):
        super().__init__()
        self.photo_dir = photo_dir
        self.photo_idx = dict()
        if normalize:
            self.transform = transforms.Compose([
                transforms.Resize(size),
                transforms.ToTensor(),
                transforms.Normalize((0.5, 0.5, 0.5), (0.5, 0.5, 0.5))                                
            ])
        else:
            self.transform = transforms.Compose([
                transforms.Resize(size),
                transforms.ToTensor()                               
            ])
        self.photo_idx[1] = photo_dir
    def __getitem__(self, idx):
        # photo_path = os.path.join(self.photo_idx[1])
        photo_path = self.photo_idx[1]
        photo_img = Image.open(photo_path)
        photo_img = self.transform(photo_img)
        return photo_img

    def __len__(self):
        return len(self.photo_idx.keys())

def stringtoRGB(base64_string):
    imgdata = base64.b64decode(base64_string)
    dataBytesIO = io.BytesIO(imgdata)
    image = Image.open(dataBytesIO)
    return cv2.cvtColor(np.array(image), cv2.COLOR_BGR2RGB)
    # return image

# class PhotoDataset(Dataset):
#     def __init__(self, photo_dir, size=(256, 256), normalize=True):
#         super().__init__()
#         self.photo_dir = photo_dir
#         self.photo_idx = dict()
#         if normalize:
#             self.transform = transforms.Compose([
#                 transforms.Resize(size),
#                 transforms.ToTensor(),
#                 transforms.Normalize((0.5, 0.5, 0.5), (0.5, 0.5, 0.5))                                
#             ])
#         else:
#             self.transform = transforms.Compose([
#                 transforms.Resize(size),
#                 transforms.ToTensor()                               
#             ])
#         for i, fl in enumerate(os.listdir(self.photo_dir)):
#             self.photo_idx[i] = fl

#     def __getitem__(self, idx):
#         photo_path = os.path.join(self.photo_dir, self.photo_idx[idx])
#         photo_img = Image.open(photo_path)
#         photo_img = self.transform(photo_img)
#         return photo_img

#     def __len__(self):
#         return len(self.photo_idx.keys())