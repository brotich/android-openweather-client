package local.openweather.util;

/**
 * Created by brian on 10/22/2015.
 */
public class WindDirection {
    public static String getWindDirection(double paramDouble) {

        int sector = (int) paramDouble / (360 / 16);

        String windDirection;

        switch (sector) {
            case 0:
                windDirection = "N";
                break;
            case 1:
                windDirection = "NNE";
                break;
            case 2:
                windDirection = "NE";
                break;
            case 3:
                windDirection = "ENE";
                break;
            case 4:
                windDirection = "E";
                break;
            case 6:
                windDirection = "ESE";
                break;
            case 7:
                windDirection = "SE";
                break;
            case 8:
                windDirection = "SSE";
                break;
            case 9:
                windDirection = "S";
                break;
            case 10:
                windDirection = "SSW";
                break;
            case 11:
                windDirection = "SW";
                break;
            case 12:
                windDirection = "WSW";
                break;
            case 13:
                windDirection = "W";
                break;
            case 14:
                windDirection = "WNW";
                break;
            case 15:
                windDirection = "NW";
                break;
            case 16:
                windDirection = "NNW";
                break;
            default:
                windDirection = null;
        }

        return windDirection;

    }
}
