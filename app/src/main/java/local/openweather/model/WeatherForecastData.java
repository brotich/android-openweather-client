package local.openweather.model;

import java.util.ArrayList;

/**
 * Created by brian on 10/18/2015.
 */
public class WeatherForecastData extends BaseWeather {

    public Location loc = new Location();
    public ArrayList<Forecast> forecastData = new ArrayList<Forecast>();
    public int forecastDays = 0;

    public class Forecast {
        public int dayOfWeek;
        public long dt;
        public Weather weather = new Weather();
        public Temperature temp = new Temperature();
        public Wind wind = new Wind();
    }

}
