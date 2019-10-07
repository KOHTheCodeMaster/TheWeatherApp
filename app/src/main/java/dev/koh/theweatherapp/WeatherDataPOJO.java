package dev.koh.theweatherapp;

import org.json.JSONException;
import org.json.JSONObject;

class WeatherDataPOJO {

    private static final String TAG = "WEATHER_DATA_POJO_DEBUG_01";

    private String mCity;
    private String mIconName;
    private String mTemperature;
    private int mCondition;

    static WeatherDataPOJO parseJsonToWeatherData(JSONObject jsonObject) {

        try {

            WeatherDataPOJO weatherDataPOJO = new WeatherDataPOJO();

            weatherDataPOJO.mCity = jsonObject.getString("name");
            weatherDataPOJO.mCondition = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            weatherDataPOJO.mIconName = updateWeatherIcon(weatherDataPOJO.mCondition);

            double tempKelvin = jsonObject.getJSONObject("main").getDouble("temp");
            double tempCelsius = tempKelvin - 273.15;

            //  Round-off to nearest even Integer
            weatherDataPOJO.mTemperature = (int) Math.rint(tempCelsius) + "";

//            Log.d(TAG, "parseJsonToWeatherData: JsonObject Parsed into WeatherData successfully!" +
//                    "\nweatherDataPOJO : " + weatherDataPOJO);

            return weatherDataPOJO;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    private static String updateWeatherIcon(int weatherCondition) {

        if (weatherCondition >= 0 && weatherCondition < 300)
            return "tstorm1";
        if (weatherCondition >= 300 && weatherCondition < 500)
            return "light_rain";
        if (weatherCondition >= 500 && weatherCondition < 600)
            return "shower3";
        if (weatherCondition >= 600 && weatherCondition < 701)
            return "snow4";
        if (weatherCondition >= 701 && weatherCondition <= 771)
            return "fog";
        if (weatherCondition >= 772 && weatherCondition < 800)
            return "tstorm3";
        if (weatherCondition == 800)
            return "sunny";
        if (weatherCondition >= 801 && weatherCondition < 805)
            return "cloudy2";
        if (weatherCondition >= 900 && weatherCondition < 903)
            return "tstorm3";
        if (weatherCondition == 903)
            return "snow5";
        if (weatherCondition == 904)
            return "sunny";
        if (weatherCondition >= 905 && weatherCondition <= 1000)
            return "tstorm3";

        return null;

    }

    String getmCity() {
        return mCity;
    }

    String getmIconName() {
        return mIconName;
    }

    String getmTemperature() {
        return mTemperature + "°";
    }

    @Override
    public String toString() {
        return "WeatherDataPOJO{" +
                "mCity : '" + mCity + '\'' +
                "\n mIconName : '" + mIconName + '\'' +
                "\n mTemperature : '" + mTemperature + "°" + '\'' +
                "\n mCondition=" + mCondition +
                '}';
    }

}
