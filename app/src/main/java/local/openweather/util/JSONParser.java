package local.openweather.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import local.openweather.model.WeatherCurrentData;
import local.openweather.model.WeatherForecastData;

public class JSONParser {

    private static JSONParser jsonParser = new JSONParser();
    private final String LOG_TAG = "Open_JSON";
    private int cod;

    private JSONParser() {
    }

    public static JSONParser getInstance() {
        return jsonParser;
    }

    public WeatherCurrentData getCurrentWeather(String paramString) {
        try {
            JSONObject localJSONObject = new JSONObject(paramString);

            //request successful cod = 200
            this.cod = localJSONObject.getInt("cod");

            //request not succeful;
            if (cod != 200)
                return null;

            //get location data
            String cityName = localJSONObject.getString("name");
            int cityId = localJSONObject.getInt("id");
            Object localObject = localJSONObject.getJSONObject("sys");
            String country = ((JSONObject) localObject).getString("country");
            long sunrise = ((JSONObject) localObject).getLong("sunrise");
            long sunset = ((JSONObject) localObject).getLong("sunset");
            localObject = localJSONObject.getJSONObject("coord");
            double lat = ((JSONObject) localObject).getDouble("lat");
            double lon = ((JSONObject) localObject).getDouble("lon");

            //main weather details
            localObject = (localJSONObject.getJSONArray("weather")).getJSONObject(0);
            int weatherId = ((JSONObject) localObject).getInt("id");
            String icon = ((JSONObject) localObject).getString("icon");
            String mainWeather = ((JSONObject) localObject).getString("main");
            String description = ((JSONObject) localObject).getString("description");

            //wind values
            localObject = localJSONObject.getJSONObject("wind");
            double windSpeed = ((JSONObject) localObject).getDouble("speed");
            double windDeg = ((JSONObject) localObject).getDouble("deg");

            //clouds
            localObject = localJSONObject.getJSONObject("clouds");
            double clouds = ((JSONObject) localObject).getDouble("all");

            //main weather
            localObject = localJSONObject.getJSONObject("main");
            double temp = ((JSONObject) localObject).getDouble("temp");
            double temp_max = ((JSONObject) localObject).getDouble("temp_max");
            double temp_min = ((JSONObject) localObject).getDouble("temp_min");
            double pressure = ((JSONObject) localObject).getDouble("pressure");
            double humidity = ((JSONObject) localObject).getDouble("humidity");

            //set data to weather current bean
            WeatherCurrentData localCurrentWeather = new WeatherCurrentData();

            localCurrentWeather.loc.city = cityName;
            localCurrentWeather.loc.id = cityId;
            localCurrentWeather.loc.country = country;
            localCurrentWeather.loc.coord.lat = lat;
            localCurrentWeather.loc.coord.lon = lon;
            localCurrentWeather.loc.sunrise = sunrise;
            localCurrentWeather.loc.sunset = sunset;

            localCurrentWeather.wind.speed = windSpeed;
            localCurrentWeather.wind.deg = windDeg;
            localCurrentWeather.wind.direction = WindDirection.getWindDirection(windDeg);
            localCurrentWeather.wind.units = "mps"; //// FIXME: 10/19/2015 

            localCurrentWeather.temp.temp = temp;
            localCurrentWeather.temp.temp_max = temp_max;
            localCurrentWeather.temp.temp_min = temp_min;
            localCurrentWeather.temp.units = '\u2103'; //FIXME updates units here

            localCurrentWeather.weather.main = mainWeather;
            localCurrentWeather.weather.description = description;
            localCurrentWeather.weather.clouds = clouds;
            localCurrentWeather.weather.icon = icon;
            localCurrentWeather.weather.pressure = pressure;
            localCurrentWeather.weather.humidity = humidity;
            localCurrentWeather.weather.id = weatherId;

            LogUtils.LOGE(mainWeather + " " + icon);

            return localCurrentWeather;

        } catch (JSONException localJSONException) {
            LogUtils.LOGE(LOG_TAG, "Error parsing json string(current)", localJSONException);
        }
        return null;
    }

    public WeatherForecastData getForecastData(String paramString) {
        try {
            JSONObject localJSONObject = new JSONObject(paramString);

            this.cod = localJSONObject.getInt("cod");

            if (this.cod != 200)
                return null;

            Object localObject;
            //location details
            localObject = localJSONObject.getJSONObject("city");
            int cityId = ((JSONObject) localObject).getInt("id");
            String country = ((JSONObject) localObject).getString("country");
            String cityName = ((JSONObject) localObject).getString("name");
            int population = ((JSONObject) localObject).getInt("population");
            localObject = ((JSONObject) localObject).getJSONObject("coord");
            double lat = ((JSONObject) localObject).getDouble("lat");
            double lon = ((JSONObject) localObject).getDouble("lon");

            //get weather forecast
            int forecastDays = localJSONObject.getInt("cnt");

            //get the days forecast
            ArrayList<WeatherForecastData.Forecast> localForecastData = new ArrayList<>();
            localObject = localJSONObject.getJSONArray("list");

            for (int i = 0; i < forecastDays; i++) {
                JSONObject dayForecast = ((JSONArray) localObject).getJSONObject(i);

                //time of forecast
                long dt = dayForecast.getLong("dt");

                //wind values
                double windSpeed = dayForecast.getDouble("speed");
                double windDirection = dayForecast.getDouble("deg");

                //clouds
                double clouds = dayForecast.getDouble("clouds");

                //humidity
                double humidity = dayForecast.getDouble("humidity");

                //pressure
                double pressure = dayForecast.getDouble("pressure");

                //temperature
                JSONObject object = dayForecast.getJSONObject("temp");
                double temp_day = object.getDouble("day");
                double temp_min = object.getDouble("min");
                double temp_max = object.getDouble("max");
                double temp_morn = object.getDouble("morn");
                double temp_eve = object.getDouble("eve");
                double temp_night = object.getDouble("night");


                //main weather details
                object = (dayForecast.getJSONArray("weather")).getJSONObject(0);
                int id = object.getInt("id");
                String main = object.getString("main");
                String description = object.getString("description");
                String icon = object.getString("icon");

                //create forecast object add to array
                WeatherForecastData.Forecast localForecast = new WeatherForecastData().new Forecast();

                localForecast.dt = dt * 1000L;

                localForecast.weather.main = main;
                localForecast.weather.description = description;
                localForecast.weather.clouds = clouds;
                localForecast.weather.icon = icon;
                localForecast.weather.pressure = pressure;
                localForecast.weather.humidity = humidity;
                localForecast.weather.id = id;

                localForecast.temp.temp = temp_day;
                localForecast.temp.temp_min = temp_min;
                localForecast.temp.temp_max = temp_max;
                localForecast.temp.temp_even = temp_eve;
                localForecast.temp.temp_morn = temp_morn;
                localForecast.temp.temp_night = temp_night;

                localForecast.wind.speed = windSpeed;
                localForecast.wind.deg = windDirection;

                localForecast.dayOfWeek = i;

                localForecastData.add(localForecast);

            }

            WeatherForecastData forecastData = new WeatherForecastData();
            forecastData.loc.id = cityId;
            forecastData.loc.city = cityName;
            forecastData.loc.country = country;
            forecastData.loc.population = population;
            forecastData.loc.coord.lat = lat;
            forecastData.loc.coord.lon = lon;

            forecastData.forecastData = localForecastData;
            forecastData.forecastDays = forecastDays;

            return forecastData;
        } catch (JSONException localJsonException) {
            LogUtils.LOGE(LOG_TAG, "Error parsing json String(forecast)", localJsonException);
        }
        return null;
    }

    public int getResponseCode() {
        return this.cod;
    }
}
