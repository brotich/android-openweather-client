package local.openweather.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import local.openweather.R;
import local.openweather.adapters.WeatherForecastAdapter;
import local.openweather.model.WeatherCurrentData;
import local.openweather.model.WeatherForecastData;
import local.openweather.util.JSONParser;
import local.openweather.util.LogUtils;
import local.openweather.util.RestClient;
import local.openweather.util.WeatherDataConf;
import local.openweather.util.WeatherIcon;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.fab) {
                    test();
                }
            }
        });

        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setPadding(width / 2 - mProgressBar.getWidth(), height / 2, 0, 0);

        PreferenceManager.setDefaultValues(getApplicationContext(), R.xml.settings, true);
    }

    private void test() {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        String location = sharedPref.getString("pref_weatherLocation", "");

        WeatherTask task = new WeatherTask();

        if (location != "") {
            task.execute(location);
        } else {
            Toast.makeText(this, "The City is Blank", Toast.LENGTH_LONG).show();
        }

    }

    private void updateCurrentWeather(WeatherCurrentData paramdata, View view) {
        TextView location = (TextView) view.findViewById(R.id.txtview_location);
        TextView currentTemperature = (TextView) view.findViewById(R.id.current_temperature);
        TextView mainTemp = (TextView) view.findViewById(R.id.txtview_temperature);
        TextView icon = (TextView) view.findViewById(R.id.weather_icon);
        TextView mainDescription = (TextView) view.findViewById(R.id.main_decription);
        TextView windDetails = (TextView) view.findViewById(R.id.wind_values);
        TextView updated = (TextView) view.findViewById(R.id.textview_last_updated);

        location.setText(String.format("%s, %s", paramdata.loc.city, paramdata.loc.country));
        currentTemperature.setText(String.format("%s - %s",
                displayTemperature(paramdata.temp.temp_min), displayTemperature(paramdata.temp.temp_max)));
        mainTemp.setText(displayTemperature(paramdata.temp.temp));
        icon.setText(WeatherIcon.getIcon(paramdata.weather.icon));
        mainDescription.setText(paramdata.weather.main);
        windDetails.setText(paramdata.wind.speed + " mps " + paramdata.wind.direction);
        updated.setText("update moments ago");

    }

    private String displayTemperature(double paramDouble) {

        double localTemp;
        String units;

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String displayUnits = sharedPref.getString("pref_weatherUnits", "D");

        switch (displayUnits) {
            case "F":
                localTemp = ((9 / 5.0) * paramDouble) + 32;
                units = String.valueOf('\u2109');
                break;
            case "D":
            default:
                localTemp = paramDouble;
                units = String.valueOf('\u2103');
        }

        return String.format("%s%s", new DecimalFormat("00").format(localTemp), units);
    }

    private void updateForecast(WeatherForecastData paramData, ListView view) {
        WeatherForecastAdapter adapter = new WeatherForecastAdapter(getApplicationContext(), paramData.forecastData);
        view.setAdapter(adapter);
    }

    private class WeatherTask extends AsyncTask<String, Void, Void> {

        private WeatherForecastData forecastData;
        private WeatherCurrentData currentData;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (mProgressBar.getVisibility() == View.INVISIBLE) {
                mProgressBar.setVisibility(View.VISIBLE);
            }

            LogUtils.LOGE("---------Start----download");
        }

        @Override
        protected Void doInBackground(String... params) {

            RestClient restClient = new RestClient();

            //current weather Data
            restClient.setBaseUrl("http://api.openweathermap.org/data/2.5/weather");
            restClient.addParam("mode", "json");
            restClient.addParam("units", "metric");
            restClient.addParam("q", params[0]);
            restClient.addParam("appid", WeatherDataConf.API_KEY);

            restClient.getStringFromUrl();

            if (restClient.getResponseCode() == 200) {
                LogUtils.LOGE(">->-.-: Received: Current from server");
            } else {
                LogUtils.LOGE("->->->: Error establishing connection:" + restClient.getResponseCode());
                return null;
            }

            String currentWeatherString = restClient.getResponse();

            restClient.resetRestClient();

            //forecast
            restClient.setBaseUrl("http://api.openweathermap.org/data/2.5/forecast/daily");
            restClient.addParam("cnt", "5");

            restClient.addParam("mode", "json");
            restClient.addParam("units", "metric");
            restClient.addParam("q", "Nairobi,KE");
            restClient.addParam("appid", WeatherDataConf.API_KEY);

            restClient.getStringFromUrl();

            if (restClient.getResponseCode() == 200) {
                LogUtils.LOGE(">->-.-: Received: forecast from server");
            } else {
                LogUtils.LOGE("->->->: Error establishing connection:" + restClient.getResponseCode());
                return null;
            }

            String foreCastString = restClient.getResponse();


            JSONParser localJsonParser = JSONParser.getInstance();

            currentData = localJsonParser.getCurrentWeather(currentWeatherString);
            forecastData = localJsonParser.getForecastData(foreCastString);

            LogUtils.LOGE("Response code:" + localJsonParser.getResponseCode());

            if (forecastData == null) {
                LogUtils.LOGE("Error getting forecastData: response " + localJsonParser.getResponseCode());
            } else {
                LogUtils.LOGE("Data: " + forecastData.loc.city + ", " + forecastData.loc.country);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            LogUtils.LOGE("---------complete----download");

            if (currentData != null && forecastData != null) {
                Object view = findViewById(R.id.main_weather_view);

                updateCurrentWeather(currentData, (View) view);

                view = findViewById(R.id.listview_forecast);

                updateForecast(forecastData, (ListView) view);
                Snackbar.make((ListView) view, "Weather Data Updated", Snackbar.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Failed to Update Data", Toast.LENGTH_SHORT).show();
            }

            if (mProgressBar.getVisibility() == View.VISIBLE) {
                mProgressBar.setVisibility(View.INVISIBLE);
            }


        }

    }

}
