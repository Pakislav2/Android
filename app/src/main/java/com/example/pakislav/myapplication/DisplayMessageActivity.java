package com.example.pakislav.myapplication;

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
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.ALIGN_PARENT_LEFT);
        imageView.setLayoutParams(params);
        switch (scountry) {
            case "English":
                imageView.setImageResource(R.mipmap.ukflag);
                break;
            case "Polish":
                imageView.setImageResource(R.mipmap.polishflag);
                break;
            case "Spanish":
                imageView.setImageResource(R.mipmap.spanishflag);
                break;
            case "German":
                imageView.setImageResource(R.mipmap.germanflag);
                break;
            case "Swahili":
                imageView.setImageResource(R.mipmap.kenyaflag);
                break;
            case "French":
                imageView.setImageResource(R.mipmap.frenchflag);
                break;
        }
        imageView.setBackgroundColor(ContextCompat.getColor(this, R.color.red));


        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
        layout.addView(imageView);


    }
}
