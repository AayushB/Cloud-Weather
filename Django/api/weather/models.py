from django.db import models

# Create your models here.
class Weather(models.Model):
	temperature = models.FloatField()
	zipcode = models.IntegerField()
	description = models.CharField(max_length=200)

	def __str__(self):
		return "T:{} Z:{} D:{}".format(self.temperature,self.zipcode,self.description)