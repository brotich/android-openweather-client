package local.openweather.util;

/**
 * Created by brian on 10/18/2015.
 */
public class Test {
    public static String getCurrentSampleData() {
        return "{\"coord\": {\"lon\": 36.82,\"lat\": -1.28},\"weather\": [{\"id\": 801,\"main\": \"Clouds\",\"description\": \"few clouds\",\"icon\": \"02n\"}],\"base\": \"stations\",\"main\": {\"temp\": 289.617,\"pressure\": 838.59,\"humidity\": 77,\"temp_min\": 289.617,\"temp_max\": 289.617,\"sea_level\": 1026.8,\"grnd_level\": 838.59},\"wind\": {\"speed\": 1.62,\"deg\": 84.5006},\"clouds\": {\"all\": 24},\"dt\": 1445197591,\"sys\": {\"message\": 0.0107,\"country\": \"KE\",\"sunrise\": 1445138013,\"sunset\": 1445181720},\"id\": 184745,\"name\": \"Nairobi\",\"cod\": 200}";
    }

    public static String getDayForecastSampledata() {
        return "{\"city\": {\"id\": 184745,\"name\": \"Nairobi\",\"coord\": {\"lon\": 36.816669,\"lat\": -1.28333},\"country\": \"KE\",\"population\": 0},\"cod\": \"200\",\"message\": 0.0088,\"cnt\": 7,\"list\": [{\"dt\": 1445072400,\"temp\": {\"day\": 18.97,\"min\": 16.53,\"max\": 20.51,\"night\": 16.54,\"eve\": 19.58,\"morn\": 18.97},\"pressure\": 851.01,\"humidity\": 100,\"weather\": [{\"id\": 501,\"main\": \"Rain\",\"description\": \"moderate rain\",\"icon\": \"10d\"}],\"speed\": 1.61,\"deg\": 124,\"clouds\": 92,\"rain\": 7.25},{\"dt\": 1445158800,\"temp\": {\"day\": 21.32,\"min\": 16.41,\"max\": 21.46,\"night\": 16.44,\"eve\": 19.94,\"morn\": 16.53},\"pressure\": 850.89,\"humidity\": 90,\"weather\": [{\"id\": 500,\"main\": \"Rain\",\"description\": \"light rain\",\"icon\": \"10d\"}],\"speed\": 1.67,\"deg\": 132,\"clouds\": 68,\"rain\": 2.52},{\"dt\": 1445245200,\"temp\": {\"day\": 19.95,\"min\": 14.93,\"max\": 21.65,\"night\": 16.03,\"eve\": 19.3,\"morn\": 16.11},\"pressure\": 851.36,\"humidity\": 92,\"weather\": [{\"id\": 501,\"main\": \"Rain\",\"description\": \"moderate rain\",\"icon\": \"10d\"}],\"speed\": 1.42,\"deg\": 129,\"clouds\": 8,\"rain\": 6.89},{\"dt\": 1445331600,\"temp\": {\"day\": 23.34,\"min\": 13.43,\"max\": 24.52,\"night\": 16.31,\"eve\": 21.34,\"morn\": 13.43},\"pressure\": 849.42,\"humidity\": 65,\"weather\": [{\"id\": 501,\"main\": \"Rain\",\"description\": \"moderate rain\",\"icon\": \"10d\"}],\"speed\": 1.65,\"deg\": 148,\"clouds\": 0,\"rain\": 4.06},{\"dt\": 1445418000,\"temp\": {\"day\": 24.45,\"min\": 13.97,\"max\": 24.45,\"night\": 15.22,\"eve\": 19.91,\"morn\": 13.97},\"pressure\": 847.81,\"humidity\": 68,\"weather\": [{\"id\": 502,\"main\": \"Rain\",\"description\": \"heavy intensity rain\",\"icon\": \"10d\"}],\"speed\": 1.61,\"deg\": 130,\"clouds\": 8,\"rain\": 15.55},{\"dt\": 1445504400,\"temp\": {\"day\": 23.98,\"min\": 15.58,\"max\": 23.98,\"night\": 15.58,\"eve\": 17.14,\"morn\": 20.03},\"pressure\": 842.4,\"humidity\": 0,\"weather\": [{\"id\": 501,\"main\": \"Rain\",\"description\": \"moderate rain\",\"icon\": \"10d\"}],\"speed\": 2.47,\"deg\": 104,\"clouds\": 29,\"rain\": 4.21},{\"dt\": 1445590800,\"temp\": {\"day\": 21.21,\"min\": 14.53,\"max\": 21.21,\"night\": 14.53,\"eve\": 16.31,\"morn\": 17.67},\"pressure\": 842.9,\"humidity\": 0,\"weather\": [{\"id\": 501,\"main\": \"Rain\",\"description\": \"moderate rain\",\"icon\": \"10d\"}],\"speed\": 1.34,\"deg\": 77,\"clouds\": 36,\"rain\": 7.13}]}";
    }

}