from django.contrib import admin
from django.urls import path
from . import views
app_name = 'recommend_art'

urlpatterns = [
    path('', views.index),
]