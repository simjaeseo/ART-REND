from django.db import models

# Create your models here.




class ChangedPainting(models.Model):
    changed_painting_id = models.BigAutoField(primary_key=True)
    created_at = models.DateTimeField(blank=True, null=True)
    updated_at = models.DateTimeField(blank=True, null=True)
    url = models.CharField(max_length=255, blank=True, null=True)
    painting_painting = models.ForeignKey('Painting', models.DO_NOTHING, blank=True, null=True)
    member = models.ForeignKey('Member', models.DO_NOTHING, blank=True, null=True)
    painting = models.ForeignKey('Painting', models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'changed_painting'


class DjangoAdminLog(models.Model):
    action_time = models.DateTimeField()
    object_id = models.TextField(blank=True, null=True)
    object_repr = models.CharField(max_length=200)
    action_flag = models.PositiveSmallIntegerField()
    change_message = models.TextField()
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING, blank=True, null=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'django_admin_log'


class DjangoContentType(models.Model):
    app_label = models.CharField(max_length=100)
    model = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'django_content_type'
        unique_together = (('app_label', 'model'),)


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
    favorite_style_id = models.BigAutoField(primary_key=True)
    painting = models.ForeignKey('Painting', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'favorite_style'


class LikedPainting(models.Model):
    liked_painting_id = models.BigAutoField(primary_key=True)
    painting_id = models.BigIntegerField(blank=True, null=True)
    member = models.ForeignKey('Member', models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'liked_painting'


class Member(models.Model):
    member_id = models.BigAutoField(primary_key=True)
    name = models.CharField(max_length=255, blank=True, null=True)
    nickname = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'member'


class Painting(models.Model):
    painting_id = models.BigAutoField(primary_key=True)
    created_at = models.DateTimeField(blank=True, null=True)
    updated_at = models.DateTimeField(blank=True, null=True)
    art_trend = models.CharField(max_length=255, blank=True, null=True)
    artist = models.CharField(max_length=255, blank=True, null=True)
    description = models.CharField(max_length=5000, blank=True, null=True)
    dimensions = models.CharField(max_length=255, blank=True, null=True)
    genre = models.CharField(max_length=255, blank=True, null=True)
    hits = models.BigIntegerField(blank=True, null=True)
    korean_title = models.CharField(max_length=255, blank=True, null=True)
    medium = models.CharField(max_length=255, blank=True, null=True)
    place = models.CharField(max_length=255, blank=True, null=True)
    title = models.CharField(max_length=255, blank=True, null=True)
    total_change_count = models.BigIntegerField(blank=True, null=True)
    total_like_count = models.BigIntegerField(blank=True, null=True)
    url = models.CharField(max_length=255, blank=True, null=True)
    year = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'painting'


class RecommendedPainting(models.Model):
    liked_painting_id = models.BigAutoField(primary_key=True)

    class Meta:
        managed = False
        db_table = 'recommended_painting'


class SelectedPainting(models.Model):
    selected_painting_id = models.BigAutoField(primary_key=True)
    painting = models.ForeignKey(FavoriteStyle, models.DO_NOTHING, blank=True, null=True)
    member = models.ForeignKey(Member, models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'selected_painting'