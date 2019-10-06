package dev.koh.theweatherapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN_ACTIVITY_DEBUG_01";
    private static final String API_APP_ID = "0600af5f009819e24fd8bf882383c839";
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather";
    private final int REQUEST_CODE = 567;
    private final int MIN_TIME = 100;
    private final float MIN_DISTANCE = 1f;

    private LocationManager locationManager;
    private LocationListener locationListener;

    private Button changeLocationBtn;
    private ImageView weatherImageView;
    private TextView temperatureTextView;
    private TextView currentLocationTextView;
    private Context mainActivityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        major();

    }

    private void major() {

        //  Time Stamp : 29th July 2K19, 02:18 PM..!!
        init();

    }

    private void init() {

        initializeViewElements();

        initializeDataMembers();

        checkForPermissions();

        reset();

    }

    private void initializeViewElements() {

        //  Initialize the View Elements
        changeLocationBtn = findViewById(R.id.idChangeLocationBtnMA);
        currentLocationTextView = findViewById(R.id.idCurrentLocationTextViewMA);
        temperatureTextView = findViewById(R.id.idTemperatureTextViewMA);
        weatherImageView = findViewById(R.id.idWeatherImageViewMA);

    }

    private void initializeDataMembers() {

        this.mainActivityContext = this;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.d(TAG, "onLocationChanged: Device Location has been Changed.");

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                Log.d(TAG, "onLocationChanged: Latitude : " + latitude +
                        "\nLongitude : " + longitude);

                RequestParams requestParams = new RequestParams();
                requestParams.put("lat", latitude);
                requestParams.put("lon", longitude);
                requestParams.put("appid", API_APP_ID);

                generateAPIRequest(requestParams);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.d(TAG, "onStatusChanged: Device Status has been Changed.");
            }

            @Override
            public void onProviderEnabled(String provider) {
                Log.d(TAG, "onProviderEnabled: GPS has been Enabled.");
            }

            @Override
            public void onProviderDisabled(String provider) {
                Log.d(TAG, "onProviderDisabled: GPS has been Disabled.");
            }
        };

    }

    private void generateAPIRequest(RequestParams requestParams) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(WEATHER_API_URL, requestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
                Log.d(TAG, "onSuccess: API Request Successful.");
                Log.d(TAG, "onSuccess: Response Status Code : " + statusCode);
                Log.d(TAG, "onSuccess: Response: " + responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d(TAG, "onSuccess: API Request Successful.");
                Log.d(TAG, "onSuccess: Response Status Code : " + statusCode);
                Log.d(TAG, "onSuccess: Response: " + response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.e(TAG, "onFailure: error", throwable);
                Log.d(TAG, "onFailure: API Request Failed.");
                Log.d(TAG, "onFailure: Response Status Code : " + statusCode);
                Log.d(TAG, "onFailure: Response: " + responseString);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Log.e(TAG, "onFailure: error", throwable);
                Log.d(TAG, "onFailure: API Request Failed.");
                Log.d(TAG, "onFailure: Response Status Code : " + statusCode);
                Log.d(TAG, "onFailure: Response: " + errorResponse.toString());

            }

        });

    }

    private void checkForPermissions() {

        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_FINE_LOCATION
        };

        if (ActivityCompat.checkSelfPermission(this, permissions[0])
                != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, permissions[1])
                        != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "checkForPermissions: Requesting Permissions!");
            ActivityCompat.requestPermissions(this, permissions, this.REQUEST_CODE);
        } else {
            //  Only When acquired the access to Permissions
            //  Register the locationManager with GPS_PROVIDER, MIN_TIME MIN_DISTANCE
            //  & locationListener
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    MIN_TIME, MIN_DISTANCE, locationListener);
            Log.d(TAG, "checkForPermissions: locationManager Registered " +
                    "for Location Updates Successfully!");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume: onResume callback invoked.");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: Permission Granted!");
                reset();
            } else
                Log.d(TAG, "onRequestPermissionsResult: Permission Denied!");

        }

    }

    private void reset() {


    }

    public void onChangeLocationBtnClick(View view) {


    }

}

/*
 *  Date Created  : 29th July 2K19, 02:18 PM..!!
 *  Last Modified : 6th October 2K19, 07:27 PM..!!
 *
 *  Change Log:
 *
 *  3rd Commit - [OpenWeatherApp API Added]
 *  1. Using AsyncHTTP Requests to OpenWeatherApp API on Location Changed.
 *
 *  2nd Commit - [ChangeLocationActivity Added]
 *  1. Designed the ChangeLocationActivity XML Layout using Constraint Layout.
 *
 *  Init Commit - [XML Layout - MainActivity]
 *  1. Designed the MainActivity XML Layout from scratch using Constraint Layout.
 *
 *  Code Developed By,
 *  ~K.O.H..!! ^__^
 */