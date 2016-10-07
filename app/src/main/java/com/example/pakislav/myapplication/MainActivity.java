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
import android.widget.TextView;
import android.widget.Toast;

import static com.example.pakislav.myapplication.R.id.textQuantityNumber;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public final static String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";
    public final static String EXTRA_COUNTRY = "com.example.myapplication.SCOUNTRY";
    public final static String EXTRA_QUANTITY = "com.example.myapplication.QUANTITY";
    public final static String EXTRA_TYPE = "com.example.myapplicaiton.TYPE";
    public String scountry;
    public float quantity;
    public double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Button plus = (Button) findViewById(R.id.buttonPlus);
        plus.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                quantity++;
            }
            quantityNumber.setText(""+quantity);
        });
        Button minus = (Button) findViewById(R.id.buttonMinus);
        minus.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                quantity--;
            }
        });*/


        Spinner spinner = (Spinner) findViewById(R.id.langSpinner);
        //spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Spinner spinner2 = (Spinner) findViewById(R.id.typeSpinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.drink, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapter, View v, int position, long id){
            scountry = adapter.getItemAtPosition(position).toString();
            Toast.makeText(getApplicationContext(),"Selected country: " + scountry, // Why does it print selection of the OTHER spinner2?
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

    public void orderDrink(View view){
        Intent intent2 = new Intent(this, DisplayDrinkActivity.class);
        Spinner spinner2 = (Spinner) findViewById(R.id.typeSpinner);
        String type = spinner2.getSelectedItem().toString();
        TextView textQuantity = (TextView) findViewById(textQuantityNumber);
        Float quantityE = Float.valueOf(textQuantity.getText().toString());
        intent2.putExtra(EXTRA_QUANTITY, quantityE);
        intent2.putExtra(EXTRA_TYPE, type);
        startActivity(intent2);
    }
    public void addQuantity(View view){
        quantity++;
        TextView textQuantity = (TextView) findViewById(textQuantityNumber);
        textQuantity.setText(String.valueOf(quantity));
        givePrice(view);
    }

    public void removeQuantity(View view){
        if (quantity > 0){quantity--;}
        TextView textQuantity = (TextView) findViewById(textQuantityNumber);
        textQuantity.setText(String.valueOf(quantity));
        givePrice(view);
    }

    public void givePrice(View view){
        Spinner spinner2 = (Spinner) findViewById(R.id.typeSpinner);
        String type = spinner2.getSelectedItem().toString();
        switch (type){
            case "Coffee":
                price = quantity * 3.50;
                break;
            case "Tea":
                price = quantity * 2.00;
                break;
            case "Soda":
                price = quantity * 2.75;
                break;
        }

        TextView textPrice = (TextView) findViewById(R.id.textPriceNumber);
        textPrice.setText(String.valueOf(price)+"$");
    }
}
