package dev.koh.theweatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button changeLocationBtn;
    private ImageView weatherImageView;
    private TextView temperatureTextView;
    private TextView currentLocationTextView;

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

        //  Initialize the View Elements
        changeLocationBtn = findViewById(R.id.idChangeLocationBtnMA);
        currentLocationTextView = findViewById(R.id.idCurrentLocationTextViewMA);
        temperatureTextView = findViewById(R.id.idTemperatureTextViewMA);
        weatherImageView = findViewById(R.id.idWeatherImageViewMA);

        reset();

    }

    private void reset() {


    }

    public void onChangeLocationBtnClick(View view) {



    }

}

/*
 *  Date Created  : 29th July 2K19, 02:18 PM..!!
 *  Last Modified : 6th October 2K19, 05:07 PM..!!
 *
 *  Change Log:
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