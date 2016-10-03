package com.example.pakislav.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public final static String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";
    public final static String EXTRA_COUNTRY = "com.example.myapplication.SCOUNTRY";
    public String scountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.langSpinner);
        //spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapter, View v, int position, long id){
            scountry = adapter.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(),"Selected country: " + scountry,
                    Toast.LENGTH_LONG).show();
        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //automatic stub
    }


    public void sendMessage(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        Spinner spinner = (Spinner) findViewById(R.id.langSpinner);
        String message = editText.getText().toString();
        String scountry = spinner.getSelectedItem().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_COUNTRY, scountry);
        startActivity(intent);
    }
}
