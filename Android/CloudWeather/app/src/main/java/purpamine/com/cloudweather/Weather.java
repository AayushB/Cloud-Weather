package purpamine.com.cloudweather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Aayush on 10/29/15.
 */
public class Weather
{
    private int temperature;
    private int zipcode;
    private String city;
    private String description;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    private String icon;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void update(String jsonData, int zipcode) throws JSONException
    {
        //Get the required json objects
        JSONObject response = new JSONObject(jsonData);
        JSONObject weather = (JSONObject)response.getJSONArray("weather").get(0);
        JSONObject main = response.getJSONObject("main");
        // Set the member variables from the json responses
        city = response.getString("name");
        description= weather.getString("description");
        icon=weather.getString("icon");
        temperature=kelvinToF(main.getDouble("temp"));
        //update the zipcode
        this.zipcode=zipcode;
    }

    // Change kelvin to Fahrenheit
    private int kelvinToF(double temp)
    {
        return (int) ((temp - 273.15)* 1.8000 + 32.00);
    }
}
