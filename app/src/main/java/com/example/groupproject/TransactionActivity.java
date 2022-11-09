package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class TransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
    }

    Switch buy_sell_switch = (Switch) findViewById(R.id.buy_sell_switch);
    // check current state of a Switch (true or false).
    Boolean switchState = buy_sell_switch.isChecked();

    public void changeToGreenTheme(View view) {
//        EditText number1ET = findViewById(R.id.num1ET);
//        EditText number2ET = findViewById(R.id.num2ET);
//        TextView numberSumTV = findViewById(R.id.resultTV);
//
//        double num1 = Double.parseDouble(number1ET.getText().toString());
//        double num2 = Double.parseDouble(number2ET.getText().toString());
//        double sum = num1 + num2;
//        double roundedSumValue = (double) Math.round(sum * 100) / 100;
//
//
//        numberSumTV.setText("   " + roundedSumValue);
    }

    public void calculateTotal(View view) {
        EditText customQuantity = findViewById(R.id.purchaseQuantity);
        EditText cryptoTicker = findViewById(R.id.cryptoTicker);
        EditText buyingOrSellingPrice = findViewById(R.id.buying_selling_price);



        //double num1 = Double.parseDouble(number1ET.getText().toString());
        //double num2 = Double.parseDouble(number2ET.getText().toString());
        //double sum = num1 + num2;
        //double roundedSumValue = (double) Math.round(sum * 100) / 100;


        //numberSumTV.setText("   " + roundedSumValue);
    }


}