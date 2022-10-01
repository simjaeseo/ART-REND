from django.contrib import admin
from django.urls import path
from . import views
app_name = 'recommend_art'

urlpatterns = [
    path('', views.index),
    path('make_detail_recommend/', views.recommend_detail_painting),
    path('like_recommend_painting/<int:pk>', views.like_recommend_painting),
    path('main_recommend_painting/', views.main_recommend_painting),
    path('change_photo/<int:pk>/', views.change_photo),
    path('main_page_recommend/', views.main_page_recommend),
    path('test/', views.test)
]