package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class TransactionActivity extends AppCompatActivity {

    private Switch buy_sell_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        buy_sell_switch = (Switch) findViewById(R.id.buy_sell_switch);

    }





    // These methods will keep track of the user's total purchase quantity and add to int totalQuantity
    int totalQuantity = 0;
    double totalCost;
    public void addOneQuantity(View view){
        totalQuantity += 1;
    }

    public void addTenQuantity(View view){
        totalQuantity += 10;
    }

    public void addOneHundredQuantity(View view){
        totalQuantity += 100;
    }


    // need to get price of Crypto we're buying
    // need to get name of Crypto
    public void calculateTotal(View view) {
        TextView cryptoTicker = findViewById(R.id.cryptoTicker);
        TextView buyingOrSellingPrice = findViewById(R.id.buying_selling_price);
        TextView userTotalQuantity = findViewById(R.id.totalQuantityTextView);
        TextView totalCostTV = findViewById(R.id.totalCost);
        EditText customQuantity = findViewById(R.id.customQuantity);
        int customQty = Integer.parseInt(customQuantity.getText().toString());
        totalQuantity += customQty;

        double bitcoinPrice = CryptoSelectorActivity.getBitcoinPrice();
        buyingOrSellingPrice.setText("Buying/Selling price: $" + bitcoinPrice);
        userTotalQuantity.setText("Quantity: " + totalQuantity);

        totalCost = totalQuantity * bitcoinPrice;


        totalCostTV.setText("Total Cost: $" + totalCost);

//    TextView bitcoinName = CryptoSelectorActivity.getArrayList().get(0).getName();
//    TextView bitcoinTickerSymbol = CryptoSelectorActivity.getArrayList().get(0).getTicker();

    }

    public void sendOrder(View view){
        if(buy_sell_switch.isChecked() == true){
            MainGameActivity.setCryptoCount(MainGameActivity.getCryptoCount() - totalCost);
        }else{
            MainGameActivity.setCryptoCount(MainGameActivity.getCryptoCount() + totalCost);
        }
    }


    public void changeToGreenTheme(View view) {


    }

    public void changeToRedTheme(View view) {


    }

}