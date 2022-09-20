from django.db import models

# Create your models here.
class ChangedPainting(models.Model):
    changed_painting_id = models.BigAutoField(primary_key=True)
    created_at = models.DateTimeField(blank=True, null=True)
    updated_at = models.DateTimeField(blank=True, null=True)
    url = models.CharField(max_length=255, blank=True, null=True)
    painting_painting = models.ForeignKey('Painting', models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'changed_painting'


class FavoriteStyle(models.Model):
    favorite_style_id = models.BigAutoField(primary_key=True)

    class Meta:
        managed = False
        db_table = 'favorite_style'




class Painting(models.Model):
    painting_id = models.BigAutoField(primary_key=True)
    created_at = models.DateTimeField(blank=True, null=True)
    updated_at = models.DateTimeField(blank=True, null=True)
    artist = models.CharField(max_length=255, blank=True, null=True)
    description = models.CharField(max_length=255, blank=True, null=True)
    dimensions = models.CharField(max_length=255, blank=True, null=True)
    hits = models.BigIntegerField(blank=True, null=True)
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


class SelectedPainting(models.Model):
    selected_painting_id = models.BigAutoField(primary_key=True)

    class Meta:
        managed = False
        db_table = 'selected_painting'