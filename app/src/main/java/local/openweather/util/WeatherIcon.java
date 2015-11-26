package local.openweather.util;

/**
 * Created by brian on 10/22/2015.
 */
// FIXME: 10/22/2015 temporay class. need better method

public class WeatherIcon {
    static String Cloud = "!";

    //    01d.png 01n.png sky is clear
//    02d.png 02n.png few clouds
//    03d.png 03n.png scattered clouds
//    04d.png 04n.png broken clouds
//    09d.png 09n.png shower rain
//    10d.png 10n.png Rain
//    11d.png 11n.png Thunderstorm
//    13d.png 13n.png snow
//    50d.png 50n.png mist
    static String CloudSun = "\"";
    static String CloudMoon = "#";
    static String Rain = "$";
    static String RainSun = "%";
    static String RainMoon = "&";
    static String RainAlt = "\"";
    static String RainSunAlt = "(";
    static String RainMoonAlt = ")";
    static String Downpour = "*";
    static String DownpourSun = "+";
    static String DownpourMoon = ";";
    static String Drizzle = "-";
    static String DrizzleSun = ".";
    static String DrizzleMoon = "/";
    static String Sleet = "0";
    static String SleetSun = "1";
    static String SleetMoon = "2";
    static String Hail = "3";
    static String HailSun = "4";
    static String HailMoon = "5";
    static String Flurries = "6";
    static String FlurriesSun = "7";
    static String FlurriesMoon = "8";
    static String Snow = "9";
    static String SnowSun = ":";
    static String SnowMoon = ";";
    static String Fog = "<";
    static String FogSun = "=";
    static String FogMoon = ">";
    static String Haze = "?";
    static String HazeSun = "@";
    static String HazeMoon = "A";
    static String Wind = "B";
    static String WindCloud = "C";
    static String WindCloudSun = "D";
    static String WindCloudMoon = "E";
    static String Lightning = "F";
    static String LightningSun = "G";
    static String LightningMoon = "H";
    static String Sun = "I";
    static String Sunset = "J";
    static String Sunrise = "K";
    static String SunLow = "L";
    static String SunLower = "M";
    static String Moon = "N";
    static String MoonNew = "O";
    static String MoonWaxingCrescent = "P";
    static String MoonWaxingQuarter = "Q";
    static String MoonWaxingGibbous = "R";
    static String MoonFull = "S";
    static String MoonWaningGibbous = "T";
    static String MoonWaningQuarter = "U";
    static String MoonWaningCrescent = "V";
    static String Snowflake = "W";
    static String Tornado = "X";
    static String Thermometer = "Y";
    static String ThermometerLow = "Z";
    static String ThermometerMediumLoew = "[";
    static String ThermometerMediumHigh = "\\";
    static String ThermometerHigh = "]";
    static String ThermometerFull = "^";
    static String Celsius = "_";
    static String Fahrenheit = "\"";
    static String Compass = "a";
    static String CompassNorth = "b";
    static String CompassEast = "c";
    static String CompassSouth = "d";
    static String CompassWest = "e";
    static String Umbrella = "f";
    static String Sunglasses = "g";
    static String CloudRefresh = "h";
    static String CloudUp = "i";
    static String CloudDown = "j";

    public static String getIcon(String paramString) {

        String icon;

        switch (paramString) {
            case "01d":
                icon = Sun;
                break;
            case "01n":
                icon = Moon;
                break;
            case "02n":
            case "03n":
            case "04n":
                icon = CloudSun;
                break;

            case "02d":
            case "03d":
            case "04d":
                icon = CloudMoon;
                break;
            case "09d":
                icon = DrizzleSun;
                break;
            case "09n":
                icon = DrizzleMoon;
                break;
            case "10d":
            case "10n":
                icon = Rain;
                break;
            case "11d":
            case "11n":
                icon = Downpour;
                break;
            case "13d":
            case "13n":
                icon = Snow;
                break;
            case "50d":
            case "50n":
                icon = Haze;
                break;

            default:
                icon = "";
        }

        return icon;
    }
}
