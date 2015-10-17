// This #include statement was automatically added by the Particle IDE.
#include "Weather.h"

Weather weather("this is the api key", "94100");

void setup() 
{
    Serial.begin(9600);
	weather.updateZipcode("94102");

}

void loop() 
{
    Serial.println(weather.getEvent());
}