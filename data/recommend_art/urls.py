from django.contrib import admin
from django.urls import path
from . import views
app_name = 'recommend_art'

urlpatterns = [
    path('', views.index),
    path('make_detail_recommend/', views.recommend_detail_painting),
    path('main_recommend_painting/', views.main_recommend_painting),
    path('test/', views.test)
]