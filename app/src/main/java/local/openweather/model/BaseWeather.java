package local.openweather.model;

/**
 * Created by brian on 10/18/2015.
 */
public abstract class BaseWeather {

    public class Location {
        public String city;
        public String country;
        public float sunrise;
        public float sunset;
        public int population;
        public int id;
        public Coordinates coord = new Coordinates();
    }

    public class Coordinates {
        public double lat;
        public double lon;
    }

    public class Wind {
        public String direction;
        public String units;
        public double speed;
        public double deg;
    }

    public class Temperature {
        public double temp;
        public double temp_min;
        public double temp_max;
        public double temp_morn;
        public double temp_even;
        public double temp_night;
        public char units;
    }

    public class Weather {
        public String icon;
        public String main;
        public String description;
        public double pressure;
        public double humidity;
        public double clouds;
        public double rain;
        public int id;
    }


}
