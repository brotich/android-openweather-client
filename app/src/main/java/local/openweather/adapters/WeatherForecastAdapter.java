package local.openweather.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import local.openweather.R;
import local.openweather.model.WeatherForecastData;
import local.openweather.util.WeatherIcon;

public class WeatherForecastAdapter extends ArrayAdapter {

    private ArrayList<WeatherForecastData.Forecast> forecastData;
    private LayoutInflater inflater;

    public WeatherForecastAdapter(Context context, ArrayList objects) {
        super(context, -1, objects);
        this.forecastData = objects;
        this.inflater = LayoutInflater.from(context);
    }

    public WeatherForecastData.Forecast getItem(int position) {
        return forecastData.get(position);
    }

    public int getCount() {
        return forecastData.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.forecast_listview_item, parent, false);

            holder = new ViewHolder();
            holder.weatherIcon = (TextView) convertView.findViewById(R.id.weatherIcon);
            holder.dayOfWeek = (TextView) convertView.findViewById(R.id.dayOfWeek);
            holder.weatherDescription = (TextView) convertView.findViewById(R.id.weatherDescription);
            holder.temperatures = (TextView) convertView.findViewById(R.id.temperatures);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        WeatherForecastData.Forecast forecast = getItem(position);

        holder.dayOfWeek.setText(getDayOfWeek(forecast.dt));

        holder.weatherDescription.setText(forecast.weather.description);
        holder.temperatures.setText(String.format("%s - %s", displayTemperature(forecast.temp.temp_min),
                displayTemperature(forecast.temp.temp_max)));
        holder.weatherIcon.setText(WeatherIcon.getIcon(forecast.weather.icon));

        return convertView;
    }

    private String getDayOfWeek(long paramLong) {
        SimpleDateFormat ft = new SimpleDateFormat("EEEE");
        Date dt = new Date(paramLong);

        return ft.format(dt);
    }

    private String displayTemperature(double paramDouble) {

        double localTemp;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        String displayUnits = sharedPref.getString("pref_weatherUnits", "D");

        switch (displayUnits) {
            case "F":
                localTemp = ((9.0 / 5.0) * paramDouble) + 32;
                break;
            default:
            case "D":
                localTemp = paramDouble;
        }

        return String.format("%s", new DecimalFormat("00").format(localTemp));
    }

    private class ViewHolder {

        TextView weatherIcon;
        TextView dayOfWeek;
        TextView weatherDescription;
        TextView temperatures;
    }
}