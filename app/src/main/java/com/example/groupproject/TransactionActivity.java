package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class TransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
    }

    //Switch button stuff
    Switch buy_sell_switch = (Switch) findViewById(R.id.buy_sell_switch);
    // check current state of a Switch (true or false).
    Boolean switchState = buy_sell_switch.isChecked();

    //if buy_sell_button = true --> buyCrypto
    //else --> sellCrypto


    // These methods will keep track of the user's total purchase quantity and add to int totalQuantity
    int totalQuantity = 0;
    public void addOneQuantity(View view){
        totalQuantity += 1;
    }

    public void addTenQuantity(View view){
        totalQuantity += 10;
    }

    public void addOneHundredQuantity(View view){
        totalQuantity += 100;
    }

    public void addCustomQuantity(View view){
        EditText customQuantity = findViewById(R.id.customQuantity);
        int customQty = Integer.parseInt(customQuantity.getText().toString());
        totalQuantity += customQty;
    }

    // need to get price of Crypto we're buying
    // need to get name of Crypto
    public void calculateTotal(View view) {
        TextView cryptoTicker = findViewById(R.id.cryptoTicker);
        TextView buyingOrSellingPrice = findViewById(R.id.buying_selling_price);
        TextView userTotalQuantity = findViewById(R.id.totalQuantityTextView);
        TextView totalCostTV = findViewById(R.id.totalCost);



        double bitcoinPrice = CryptoSelectorActivity.getBitcoinPrice();
        userTotalQuantity.setText("Quantity: " + totalQuantity);
//      TextView bitcoinName = CryptoSelectorActivity.getArrayList().get(0).getName();


        buyingOrSellingPrice.setText("Buying/Selling price: $" + bitcoinPrice);







    }


    public void changeToGreenTheme(View view) {


    }

    public void changeToRedTheme(View view) {


    }


    public Boolean getSwitchState() {
        return switchState;
    }

    public void setSwitchState(Boolean switchState) {
        this.switchState = switchState;
    }

    public Switch getBuy_sell_switch() {
        return buy_sell_switch;
    }
}