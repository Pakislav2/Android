package com.example.pakislav.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class DisplayDrinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_drink);
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        float quantity = bundle.getFloat("EXTRA_QUANTITY");
        //Float quantity = intent.getFloatExtra(MainActivity.EXTRA_QUANTITY); czemu to nie dziala?
        String type = intent.getStringExtra(MainActivity.EXTRA_TYPE);
        Toast toast = Toast.makeText(getApplicationContext(), "Q: " + quantity + " T: " + type, Toast.LENGTH_SHORT);
        toast.show();
    }
}
