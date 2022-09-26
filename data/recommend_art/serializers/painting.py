from ..models import ChangedPainting, FavoriteStyle, Painting, SelectedPainting
from rest_framework import serializers



class PaintingListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Painting
        fields = ('title','artist')