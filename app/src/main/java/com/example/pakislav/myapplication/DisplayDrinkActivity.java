package com.example.pakislav.myapplication;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class DisplayDrinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_drink);
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        int quantity = intent.getIntExtra(MainActivity.EXTRA_QUANTITY, 0); //IT FINALLY FUCKING WORKS... there needs to be a 0 there for some reason...
        //int quantity = intent.getIntExtra("EXTRA_QUANTITY", 0); //Why doesn't it work god damn it?
        //int quantity = bundle.getInt("EXTRA_QUANTITY", 0); //Nie zwraca null ale nie daje prawidlowej wartosci z MainActivity
        //String type = bundle.getString("EXTRA_TYPE"); //czemu to daje null?
        //Float quantity = intent.getFloatExtra(MainActivity.EXTRA_QUANTITY); //czemu to nie dziala?
        String type = intent.getStringExtra(MainActivity.EXTRA_TYPE);
        Toast toast = Toast.makeText(getApplicationContext(), "Q: " + quantity + " T: " + type, Toast.LENGTH_SHORT);
        toast.show();

        ScrollView scrollView = (ScrollView) findViewById(R.id.ScrollView);

        for (int i = 0; i < quantity; i++) {
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            linearLayout.setBackgroundColor(getResources().getColor(R.color.red));
            ImageView image = new ImageView(this);
            //image.setImageResource(R.drawable.coffee); //cholerny bug androida image nie istnieje ale istnieje
            image.setId(i);
            linearLayout.addView(image);
            //linearLayout.setBackground(R.color.coffee); //requires API 16, current is 15.
            //Image i = new Image();
        }
    }
}
