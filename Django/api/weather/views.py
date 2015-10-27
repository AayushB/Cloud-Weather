from django.shortcuts import render
from django.http import HttpResponse


# Create your views here.
def index(request):
        return HttpResponse("Hello Weather")

def zipcode(request,zipcode):
		#return HttpResponse("Hello Weather {}".format(zipcode))
		return HttpResponse("Hello Weather")

def description(request):
		#return HttpResponse("Hello Weather {}".format(zipcode))
		return HttpResponse("Hello Weather")

def temperature(request):
		#return HttpResponse("Hello Weather {}".format(zipcode))
		return HttpResponse("Hello Weather")