from django.shortcuts import redirect, render, get_object_or_404, get_list_or_404
from .models import ChangedPainting, FavoriteStyle, Painting, SelectedPainting

from django.views.decorators.http import require_safe
from django.core.paginator import Paginator

from .serializers.painting import PaintingListSerializer
from .cbf_recommend import art_recommend
from rest_framework.response import Response
from rest_framework.decorators import api_view, permission_classes


from cycleGAN.model import CycleGAN
from cycleGAN.checkpoint import load_checkpoint
from cycleGAN.load_data import PhotoDataset
from torch.utils.data import DataLoader
# Create your views here.


@require_safe
@api_view(['GET'])
def index(request):
    paintings = get_list_or_404(Painting.objects.order_by('-painting_id'))
    # art_recommend()
    # print(paintings)
    serializer = PaintingListSerializer(paintings, many=True)
    return Response(serializer.data)


# @api_view(['POST'])
# def change_photo(request, pk):
    