#This is the url file for weather
from django.conf.urls import url
from . import views

urlpatterns = [
url(r'^$', views.index, name='index'),
url(r'^zipcode/(?P<zipcode>\d{5})/$', views.zipcode, name='zipcode'),
url(r'^description$', views.description, name='description'),
url(r'^temperature$', views.temperature, name='temperature')
]