#include "application.h"
class Weather
{
public:
	Weather(String apiKey, String zipcode);
	void updateZipcode(String zipcode);
	String getEvent();
private:
	String weatherData;
	String event;
	String apiKey;
	void getWeather(String apiKey,String zipcode);
};

