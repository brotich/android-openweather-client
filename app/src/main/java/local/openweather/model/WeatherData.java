package local.openweather.model;

/**
 * Created by brian on 10/18/2015.
 */
public class WeatherData extends BaseWeather {
    public Location loc = new Location();
    public Wind wind = new Wind();
    public Temperature temp = new Temperature();
    public Weather weather = new Weather();
}
