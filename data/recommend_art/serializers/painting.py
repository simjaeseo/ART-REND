from ..models import ChangedPainting, FavoriteStyle, Painting, SelectedPainting
from rest_framework import serializers



class PaintingListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Painting
        fields = ('title','artist')
        
        
class LikePaintSerailizer(serializers.ModelSerializer):
    
    class Meta:
        model = Painting
        exclude = ('created_at','updated_at','art_trend','dimensions', 'korean_title', 'place','total_change_count',)