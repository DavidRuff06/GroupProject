package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.checkerframework.checker.units.qual.C;

public class TransactionActivity extends AppCompatActivity {

    private Switch buy_sell_switch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        buy_sell_switch = findViewById(R.id.buy_sell_switch);


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



   public void onSwitch(View view){

       calculateTotal(view); //app crashes when calling this right now
       changeTheme(view);
   }


    // need to get price of Crypto we're buying
    // need to get name of Crypto


    public void calculateTotal(View view) {

        TextView buyingOrSellingPrice = findViewById(R.id.buying_selling_price);
        TextView userTotalQuantity = findViewById(R.id.totalQuantityTextView);
        TextView totalCostTV = findViewById(R.id.totalCost);
        EditText customQuantity = findViewById(R.id.customQuantity);
//        int customQty = Integer.parseInt(customQuantity.getText().toString());
//        totalQuantity += customQty;


        buyingOrSellingPrice.setText("Buying/Selling price: $" + MainGameActivity.getBitcoinPrice());
        userTotalQuantity.setText("Quantity: " + totalQuantity);

        totalCost = totalQuantity * MainGameActivity.getBitcoinPrice();


        totalCostTV.setText("Total Cost: $" + totalCost);
        changeTheme(view);

        String bitcoinName = MainGameActivity.getCurrencyModalArrayList().get(0).getName();
        String bitcoinTickerSymbol = MainGameActivity.getCurrencyModalArrayList().get(0).getSymbol();

    }

    public void displayCurrentInfo(View view) {
        TextView buyingOrSellingPrice = findViewById(R.id.buying_selling_price);
        TextView currencyName = findViewById(R.id.cryptoName);
        TextView currencySymbol = findViewById(R.id.cryptoSymbol);

        String bitcoinName = MainGameActivity.getCurrencyModalArrayList().get(0).getName();
        String bitcoinTickerSymbol = MainGameActivity.getCurrencyModalArrayList().get(0).getSymbol();

        buyingOrSellingPrice.setText("Buying/Selling price: $" + CryptoSelectorActivity.getBitcoinPrice());
        currencyName.setText("Crypto Name: " + bitcoinName);
        currencySymbol.setText("Crypto Ticker: " + bitcoinTickerSymbol);

    }

    public void sendOrder(View view){
        if(buy_sell_switch.isChecked() == true){
            MainGameActivity.setCryptoCount(MainGameActivity.getCryptoCount() - totalCost);
        }else{
            MainGameActivity.setCryptoCount(MainGameActivity.getCryptoCount() + totalCost);
        }
        Intent intent = new Intent(TransactionActivity.this, MainGameActivity.class);
        startActivity(intent);
    }


    public void changeTheme(View view) {
        Button oneQty = findViewById(R.id.quantityOfOne);
        Button tenQty = findViewById(R.id.quantityOfTen);
        Button hundredQty = findViewById(R.id.quantityOfOneHundred);
        Button sendOrder = findViewById(R.id.sendOrder);

        if(buy_sell_switch.isChecked() == true){
            oneQty.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.buyButtonColor));
            tenQty.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.buyButtonColor));
            hundredQty.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.buyButtonColor));
            sendOrder.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.buyButtonColor));

        }else{
            oneQty.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.sellButtonColor));
            tenQty.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.sellButtonColor));
            hundredQty.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.sellButtonColor));
            sendOrder.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.sellButtonColor));
        }
    }


}