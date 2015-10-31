#include "application.h"
#ifndef WEATHER_H
#define WEATHER_H
class Weather
{
public:
	// Getters 
	String getZipcode();
	String getTemperature();
	String getCity();
	String getDescription();
	//Setters
	void setZipcode(String zipcode);
	void setTemperature(String temperature);
	void setCity(String city);
	void setDescription(String description);

private:
	String _zipcode;
	String _temperature;
	String _city;
	String _description;
};
#endif
