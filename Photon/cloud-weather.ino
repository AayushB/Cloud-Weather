/*
	CLOUD WEATHER PHOTON INO CODE
	AUTHOR: AAYUSH BHANDARI
*/

// City = weatherTokens[0]
// Zipcode = weatherTokens[1]
// Temperature = weatherTokens[2]
// Description = weatherTokens[3]

#include "Weather.h"
String * weatherTokens; // Array to hold weather information 
Weather weather;

void setup() 
{
  Serial.begin(9600); //debug
  pinMode(D0,OUTPUT); //debug
  digitalWrite(D0, HIGH); //debug
}
  
void loop() 
{
	// Register the function weather to retrieve the weather data
	Particle.function("weather", postWeatherData);
}


// The format for this should be CITY:ZIPCODE:TEMPERATURE:DESCRIPTION
int postWeatherData(String command)
{
	weatherTokens = tokenizeWeather(command);
	//Set weather
	weather.setCity(weatherTokens[0]);
	weather.setZipcode(weatherTokens[1]);
	weather.setTemperature(weatherTokens[2]);
	weather.setDescription(weatherTokens[3]);
	//Print Weather for debug
	Serial.println("City: " + weather.getCity());
	Serial.println("Zipcode: " + weather.getZipcode());
	Serial.println("Temperature: " + weather.getTemperature());
	Serial.println("Description: " + weather.getDescription());
	//return 1 for true
	return 1;
}

// Weather input is CITY:ZIPCODE:TEMPERATURE:DESCRIPTION
String * tokenizeWeather(String weatherInput)
{
	String * tokens = new String[4]; // for 4 tokens
	String temp; // to hold parsed token
	int j=0; // index for current token

	// Iterate throug the weather Input and tokenize
	for (int i=0; i<weatherInput.length(); i++)
	{
		//add tokenized temp to tokens array
		if(weatherInput.charAt(i)==':')//token is based off :
		{
			tokens[j]=temp;
			j++;
			temp="";
		}
		else
		{	//add token char to temp token string
			temp+=weatherInput.charAt(i);
		}	
	}
	// Last token is the remaning string
	tokens[3]=temp;
	return tokens;
}
