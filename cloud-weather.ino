// This #include statement was automatically added by the Particle IDE.
#include "Weather.h"



Weather weather("this is the api key", "94100");
int prev=-1;

void setup() 
{
    Serial.begin(9600);

	weather.updateZipcode("94102");
	pinMode(D0,INPUT);



}

void loop() 
{
  //Serial.println(weather.getEvent());
  int value=digitalRead(D0);
  if (prev!=value)
  {
  	prev=value;
  	Serial.println(digitalRead(D0));

  }

}