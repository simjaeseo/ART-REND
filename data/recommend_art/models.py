from django.db import models

# Create your models here.
class Painting(models.Model):
    painting_id = models.BigAutoField(primary_key=True)
    artist = models.CharField(max_length=255, blank=True, null=True)
    created_date = models.DateTimeField(blank=True, null=True)
    dimesions = models.CharField(max_length=255, blank=True, null=True)
    explanation = models.CharField(max_length=255, blank=True, null=True)
    hits = models.BigIntegerField(blank=True, null=True)
    medium = models.CharField(max_length=255, blank=True, null=True)
    place = models.CharField(max_length=255, blank=True, null=True)
    title = models.CharField(max_length=255, blank=True, null=True)
    updated_date = models.DateTimeField(blank=True, null=True)
    year = models.DateField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'painting'