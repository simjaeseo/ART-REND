from django.shortcuts import redirect, render, get_object_or_404, get_list_or_404
from .models import ChangedPainting, DetailRecommendedPainting, FavoriteStyle, Painting, RecommendedPainting, SelectedPainting

from django.http import HttpResponse
from django.views.decorators.http import require_safe
from django.core.paginator import Paginator

from .serializers.painting import PaintingListSerializer
from .cbf_recommend import art_recommend
from rest_framework.response import Response
from rest_framework.decorators import api_view, permission_classes


# from cycleGAN.model import CycleGAN
# from cycleGAN.checkpoint import load_checkpoint
# from cycleGAN.load_data import PhotoDataset
# from torch.utils.data import DataLoader
# Create your views here.


@require_safe
@api_view(['GET'])
def index(request):
    paintings = get_list_or_404(Painting.objects.order_by('-painting_id'))
    # art_recommend()
    # print(paintings)
    serializer = PaintingListSerializer(paintings, many=True)
    return Response(serializer.data)


@api_view(['GET'])
def recommend_detail_painting(request):
    art_recommend(20)
    return HttpResponse(status=200)


            

@api_view(['POST'])
def main_recommend_painting(request):
    try:
        user = request.user
        selected_painting = SelectedPainting.objects.filter(member_id = user.id)
        user_recommend_painting = set()
        for painting_object in selected_painting:
            detail_recommend_painting = DetailRecommendedPainting.objects.filter(detail_painting=painting_object)
            user_recommend_painting.update(detail_recommend_painting)
        for paint in user_recommend_painting:
            recommended_painting = RecommendedPainting()
            recommended_painting.member_id = user.id
            recommended_painting.painting = paint
            recommended_painting.save()
        return HttpResponse(status=200)
    except:
        return HttpResponse(status=404)
# @api_view(['POST'])
# def change_photo(request, pk):
    
@api_view(['GET'])
def test(request):
    painting = Painting.objects.get(painting_id=1)
    detail_recommend_painting = DetailRecommendedPainting.objects.filter(detail_painting=painting)
    print(detail_recommend_painting)
    a = set()
    for detail in detail_recommend_painting:
        a.add(Painting.objects.get(painting_id=detail.recommended_painting_id.painting_id))
    serializer = PaintingListSerializer(a, many=True)
    return Response(serializer.data)