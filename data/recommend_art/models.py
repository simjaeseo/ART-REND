from django.db import models
# from user.models import AuthUser
# Create your models here.
from django.conf import settings

class Painting(models.Model):
    paintingId = models.BigAutoField(primary_key=True, db_column="painting_id")
    created_at = models.DateTimeField()
    updated_at = models.DateTimeField()
    artTrend = models.CharField(max_length=255, blank=True, null=True, db_column="art_trend")
    artist = models.CharField(max_length=255, blank=True, null=True)
    description = models.CharField(max_length=5000, blank=True, null=True)
    dimensions = models.CharField(max_length=255, blank=True, null=True)
    genre = models.CharField(max_length=255, blank=True, null=True)
    hits = models.BigIntegerField(blank=True, null=True)
    koreanTitle = models.CharField(max_length=255, blank=True, null=True, db_column="korean_title")
    medium = models.CharField(max_length=255, blank=True, null=True)
    place = models.CharField(max_length=255, blank=True, null=True)
    title = models.CharField(max_length=255, blank=True, null=True)
    totalChangeCount = models.BigIntegerField(blank=True, null=True, db_column="total_change_count")
    totalLikeCount = models.BigIntegerField(blank=True, null=True, db_column="total_like_count")
    url = models.CharField(max_length=255, blank=True, null=True)
    year = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'painting'
        
class DetailRecommendedPainting(models.Model):
    detail_recommended_painting_id = models.BigAutoField(primary_key=True)
    detail_painting = models.ForeignKey(Painting, models.DO_NOTHING, blank=True, null=True, related_name="choose_painting", db_column="painting_id") # db_column자체를 설정
    recommended_painting_id = models.ForeignKey(Painting, models.DO_NOTHING, blank=True, null=True, related_name="choose_painting_recommended", db_column="recommended_id")
    
    class Meta:
        managed = False
        db_table = 'detail_recommended_painting'
        
class ChangedPainting(models.Model):
    changed_painting_id = models.BigAutoField(primary_key=True)
    created_at = models.DateTimeField()
    updated_at = models.DateTimeField()
    member_id = models.BigIntegerField(blank=True, null=True)
    url = models.CharField(max_length=255, blank=True, null=True)
    painting = models.ForeignKey(Painting, models.DO_NOTHING, blank=True, null=True, db_column="painting_id")

    class Meta:
        managed = False
        db_table = 'changed_painting'   
        
            

class DjangoContentType(models.Model):
    app_label = models.CharField(max_length=100)
    model = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'django_content_type'
        unique_together = (('app_label', 'model'),)
        
class DjangoAdminLog(models.Model):
    action_time = models.DateTimeField()
    object_id = models.TextField(blank=True, null=True)
    object_repr = models.CharField(max_length=200)
    action_flag = models.PositiveSmallIntegerField()
    change_message = models.TextField()
    content_type = models.ForeignKey(DjangoContentType, models.DO_NOTHING, blank=True, null=True)
    user = models.ForeignKey(settings.AUTH_USER_MODEL , models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'django_admin_log'





class DjangoMigrations(models.Model):
    id = models.BigAutoField(primary_key=True)
    app = models.CharField(max_length=255)
    name = models.CharField(max_length=255)
    applied = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_migrations'


class DjangoSession(models.Model):
    session_key = models.CharField(primary_key=True, max_length=40)
    session_data = models.TextField()
    expire_date = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_session'


class FavoriteStyle(models.Model):
    painting = models.OneToOneField(Painting, models.DO_NOTHING, primary_key=True)

    class Meta:
        managed = False
        db_table = 'favorite_style'


class LikedPainting(models.Model):
    liked_painting_id = models.BigAutoField(primary_key=True)
    member_id = models.BigIntegerField(blank=True, null=True)
    paintingId = models.BigIntegerField(blank=True, null=True, db_column="painting_id")

    class Meta:
        managed = False
        db_table = 'liked_painting'


class Member(models.Model):
    id = models.BigAutoField(primary_key=True)
    created_date = models.DateTimeField(blank=True, null=True)
    updated_date = models.DateTimeField(blank=True, null=True)
    di = models.CharField(max_length=255, blank=True, null=True)
    google_provider = models.CharField(max_length=255, blank=True, null=True)
    google_provider_id = models.CharField(max_length=255, blank=True, null=True)
    kakao_provider = models.CharField(max_length=255, blank=True, null=True)
    kakao_provider_id = models.CharField(max_length=255, blank=True, null=True)
    name = models.CharField(max_length=255)
    nickname = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'member'


class MemberLog(models.Model):
    log_id = models.BigAutoField(primary_key=True)
    click_count = models.BigIntegerField(blank=True, null=True)
    in_time = models.CharField(max_length=255, blank=True, null=True)
    member_id = models.BigIntegerField(blank=True, null=True)
    out_time = models.CharField(max_length=255, blank=True, null=True)
    painting_id = models.BigIntegerField(blank=True, null=True)
    zoom_count = models.BigIntegerField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'member_log'

class RecommendedPainting(models.Model):
    recommended_painting_id = models.BigAutoField(primary_key=True)
    member_id = models.BigIntegerField(blank=True, null=True)
    painting = models.ForeignKey(Painting, models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'recommended_painting'


class SelectedPainting(models.Model): # 고른 정보
    selected_painting_id = models.BigAutoField(primary_key=True)
    member_id = models.BigIntegerField(blank=True, null=True)
    painting = models.ForeignKey(FavoriteStyle, models.DO_NOTHING, blank=True, null=True, db_column="favorite_style_painting_id")

    class Meta:
        managed = False
        db_table = 'selected_painting'
        
        
    