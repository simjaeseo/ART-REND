import jwt

from django.shortcuts import redirect, render, get_object_or_404, get_list_or_404
from .models import ChangedPainting, DetailRecommendedPainting, FavoriteStyle, Painting, RecommendedPainting, SelectedPainting

from django.http import HttpResponse
from django.views.decorators.http import require_safe
from django.core.paginator import Paginator

from .serializers.painting import PaintingListSerializer
from .cbf_recommend import art_recommend

from rest_framework.response import Response
from rest_framework.decorators import api_view, permission_classes
from rest_framework import status
from rest_framework import authentication, exceptions

# from cycleGAN.model import CycleGAN
# from cycleGAN.checkpoint import load_checkpoint
# from cycleGAN.load_data import PhotoDataset
# from torch.utils.data import DataLoader
# Create your views here.

from .jwt_secret_key import JWT_SECRET_KEY
from django.contrib.auth import authenticate


class JWTAuthentication(authentication.BaseAuthentication):
    def authenticate(self, request):
        try:
            token = request.META.get('AUTHORIZATION')
            if token is None:
                return None
            # header에서 받은 token 내용에 선행값 (ex. Bearer)이 있다면 token값이랑 분리 시켜준다.
            jwt, jwt_token = token.split(' ')
            # SECRET_KEY는 django의 SECRET_KEY를 사용함
            decoded = jwt.decode(jwt_token, JWT_SECRET_KEY, algorithms=['HS256'])
            id = decoded.get('id')
            return id, None
        
        except jwt.exceptions.DecodeError:
              raise exceptions.AuthenticationFailed(default='JWT Format Invalid')

@require_safe
@api_view(['GET'])
def index(request):
    paintings = get_list_or_404(Painting.objects.order_by('-painting_id'))
    # art_recommend()
    # print(paintings)
    serializer = PaintingListSerializer(paintings, many=True)
    return Response(serializer.data)


@api_view(['POST'])
def recommend_detail_painting(request):
    art_recommend(20)
    return HttpResponse(status=200)


@api_view(['GET'])
def main_recommend_painting(request):
    try:
        user = request
        print(user)
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


@api_view(['POST'])
def change_photo(request, pk):
    print(request)
    return
    
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




# def login(request):
#     try:
#           # request에서 입력받은 email과 password를 가져온다.
#         email = request.data.get('email', None)
#         password = request.data.get('password', None)

#         # 내가 로그인한 user가 진짜 맞는지 확인하는 작업이 필요함
#         # authenticate는 username(지정한 대로 변경가능)과 password가 필요하다.
#         # authenticate 메서드를 사용해서 User를 찾아야한다.
#         user = authenticate(email=email, password=password)

#         # 사용자를 찾았을때 (user가 None이 아닐때)
#         if user is not None:
#               # JWT값안에 단순히 pk값만 넣어준다.
#             # 그렇다면 JWT값을 encoded해서 token안에 담은 pk값에 대한 내용을 볼 수 있는것이다.
#               encoded_jwt = jwt.encoded(
#                     {
#                       'pk': user.pk
#                 },
#                   JWT_SECRET_KEY,
#                   algorithm='HS256'
#             )
#             return Response(data={'token': encoded_jwt}, status=status.HTTP_201_CREATED)
#         else:
#             return Response(status=status.HTTP_401_UNAUTHORIZED)
#     except Exception as e:
#         return Response(status=status.HTTP_500_INTERNAL_SERVER_ERROR)