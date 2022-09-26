import torch
from cycleGAN.model import CycleGAN
from cycleGAN.checkpoint import load_checkpoint
from cycleGAN.load_data import PhotoDataset
from cycleGAN.functions import unnorm
from torch.utils.data import DataLoader



def change_p():
    device = device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
    ckpt = load_checkpoint('./checkpoint_art/current-manet.ckpt', map_location=device)
    gan = CycleGAN(3,3,100, device)
    gan.epochs=ckpt['epoch']
    gan.gen_mtp.load_state_dict(ckpt['gen_mtp'])
    gan.gen_ptm.load_state_dict(ckpt['gen_ptm'])
    gan.desc_m.load_state_dict(ckpt['desc_m'])
    gan.desc_p.load_state_dict(ckpt['desc_p'])
    gan.adam_gen.load_state_dict(ckpt['optimizer_gen'])
    gan.adam_desc.load_state_dict(ckpt['optimizer_desc'])
    

if __name__ == '__main__':
    change_p()