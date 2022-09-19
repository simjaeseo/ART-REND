from django.db import models

# Create your models here.
class Member(models.Model):
    member_id = models.BigAutoField(primary_key=True)
    created_date = models.DateTimeField(blank=True, null=True)
    favorite_style_id = models.BigIntegerField(blank=True, null=True)
    name = models.CharField(max_length=255, blank=True, null=True)
    nickname = models.CharField(max_length=255, blank=True, null=True)
    updated_date = models.DateTimeField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'member'