package dev.koh.theweatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeLocationActivity extends AppCompatActivity {

    private EditText changeCityLocationEditText;

    //  Time Stamp : 6th October, 2K
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_location);

        init();
    }

    private void init() {

        changeCityLocationEditText = findViewById(R.id.idChangeCityEditTextCLA);

        changeCityLocationEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                String newCityName = changeCityLocationEditText.getText().toString();

                Intent newIntent = new Intent(ChangeLocationActivity.this, MainActivity.class);
                newIntent.putExtra("city", newCityName);
                startActivity(newIntent);

                return false;
            }
        });

    }

    public void onGoBackBtnClickCLA(View view) {

        finish();

    }
}
