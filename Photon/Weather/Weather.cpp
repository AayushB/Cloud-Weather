#include "Weather.h"
// Getters 
String Weather::getZipcode(){
	return _zipcode;
}
String Weather::getTemperature(){
	return _temperature;
}
String Weather::getCity(){
	return _city;
}
String Weather::getDescription(){
	return _description;
}
//Setters
void Weather::setZipcode(String zipcode){
	_zipcode=zipcode;
}
void Weather::setTemperature(String temperature){
	_temperature=temperature;
}
void Weather::setCity(String city){
	_city=city;
}
void Weather::setDescription(String description){
	_description=description;
}