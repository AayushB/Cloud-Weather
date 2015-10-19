#include "Weather.h"
// Public api
Weather::Weather(String apiKey, String zipcode)
{
	this->apiKey=apiKey;
	this->getWeather(apiKey,zipcode);
}

void Weather::updateZipcode(String zipcode)
{
	this->getWeather(this->apiKey,zipcode);
}

String Weather::getEvent()
{
	return event;
}

//private

void Weather::getWeather(String apiKey,String zipcode)
{
	this->event="Event for "+ zipcode +"\n";
}