package com.example.pakislav.myapplication;

import android.app.ActionBar;
import android.content.Intent;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayMessageActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    SeekBar seekBar;
    //TextView seekBarHint;
    //Toast toast;
    private final int duration = 10;
    private final int sampleRate = 8000;
    private final int numSamples = duration * sampleRate;
    private final double sample[] = new double[numSamples];
    private double freqOfTone = 440; //hz
    private final byte generatedSnd[] = new byte[2 * numSamples];

    Handler handler = new Handler();
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
            case "Nazi":
                imageView.setImageResource(R.drawable.naziflag);
                break;
        }
        imageView.setBackgroundColor(ContextCompat.getColor(this, R.color.red));


        ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        layout.addView(textView);
        layout.addView(imageView);

        seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
         //if (toast!=null){toast.cancel();}
        //Toast toast = Toast.makeText(getApplicationContext(),"seekbar progress: "+progress+" Hz", Toast.LENGTH_SHORT);
        //toast.show();
        TextView seekBarHint = (TextView) findViewById(R.id.seekBarHint);
        seekBarHint.setText(""+progress);
        freqOfTone = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar){
        //Toast.makeText(getApplicationContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar){
        //Toast.makeText(getApplicationContext(),"seekbar touch stopped!", Toast.LENGTH_SHORT).show();
    }

    //PRETTY MUCH EVERYTHING BELOW IS JUST PASTED AND NOT UNDERSTOOD

    @Override
    protected void onResume(){
        super.onResume();

        final Thread thread = new Thread(new Runnable() {
            public void run() {
                genTone();
                handler.post(new Runnable(){

                    public void run(){
                        playSound();
                    }
                });
            }

        });
        thread.start();
    }

    void genTone(){
        for (int i = 0; i <numSamples; ++i){
            sample[i] = Math.sin(2 * Math.PI * i / (sampleRate/freqOfTone));
        }

        int idx = 0;
        for (final double dVal : sample) {
            final short val = (short) ((dVal * 32767));
            generatedSnd[idx++] = (byte) (val & 0x00ff);
            generatedSnd[idx++] = (byte) ((val & 0xff0) >>> 8);
        }
    }

    void playSound(){
        final AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                sampleRate, AudioFormat.CHANNEL_CONFIGURATION_MONO,
                AudioFormat.ENCODING_PCM_16BIT, numSamples,
                AudioTrack.MODE_STATIC);
        audioTrack.write(generatedSnd, 0, generatedSnd.length);
        audioTrack.play();
    }
}
