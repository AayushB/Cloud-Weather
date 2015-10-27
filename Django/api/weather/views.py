from django.shortcuts import render
from django.http import HttpResponse
from models import Weather
import requests, json

weather_url="http://api.openweathermap.org/data/2.5/weather?zip={},us&appid=bd82977b86bf27fb59a04b61b657fb6f"

#Kelvin to Farenheit
def k_to_f(kelvin):
	return round(kelvin*(9.0/5.0) - 459.67,2)

# Create your views here.
def zipcode_get(request):
		w= Weather.objects.get(id=1)
		return HttpResponse(w.zipcode)

# Create your views here.
def zipcode_post(request,zipcode):
		#return HttpResponse("Hello Weather")
		response = requests.get(weather_url.format(zipcode))
		json_response = json.loads(response.text)

		w= Weather.objects.get(id=1)
		w.temperature= k_to_f(json_response["main"]["temp"])
		w.zipcode=zipcode
		w.description= json_response["weather"][0]["description"]
		w.save()

		if request.method == 'POST':
			return HttpResponse("Succesfully posted weather for {}".format(zipcode))
		else:
			return HttpResponse("405, Method Not Allowed")


def description(request):
		w= Weather.objects.get(id=1)
		return HttpResponse(w.description)

def temperature(request):
		w= Weather.objects.get(id=1)
		return HttpResponse(w.temperature)


