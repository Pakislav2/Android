package com.example.pakislav.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String scountry = intent.getStringExtra(MainActivity.EXTRA_COUNTRY);
        TextView textView = new TextView(this);
        textView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        textView.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
        textView.setTextSize(40);
        textView.setText(message);

        ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        imageView.setLayoutParams(params);
        //imageView.setScaleType(ImageView.ScaleType.FIT_XY); //to nic nie robi bez wzgledu na wartosc
        //imageView.setAdjustViewBounds(true); // nic nie zmienia
        //imageView.setPadding(0,0,0,0); //nic nie zmienia
        switch (scountry) {
            case "English":
                imageView.setImageResource(R.drawable.ukflag);
                break;
            case "Polish":
                imageView.setImageResource(R.drawable.polishflag);
                break;
            case "Spanish":
                imageView.setImageResource(R.drawable.spanishflag);
                break;
            case "German":
                imageView.setImageResource(R.drawable.germanflag);
                break;
            case "Swahili":
                imageView.setImageResource(R.drawable.kenyaflag);
                break;
            case "French":
                imageView.setImageResource(R.drawable.frenchflag);
                break;
        }
        imageView.setBackgroundColor(ContextCompat.getColor(this, R.color.red));


        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
        layout.addView(imageView);


    }
}
