package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class TransactionActivity extends AppCompatActivity {

    private Switch buy_sell_switch;
    EditText customQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        buy_sell_switch = findViewById(R.id.buy_sell_switch);
        customQuantity = findViewById(R.id.customQuantity);
        displayCurrentInfo();

        customQuantity.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (event.getAction() == KeyEvent.ACTION_UP) {
                        customQuantity.clearFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }
                }
                return false;
            }
        });






    }

    // These methods will keep track of the user's total purchase quantity and add to int totalQuantity
    int totalQuantity = 0;
    double totalCost;
    public void addOneQuantity(View view){
        TextView userTotalQuantity = findViewById(R.id.totalQuantityTextView);
        totalQuantity += 1;
        userTotalQuantity.setText("Quantity: " + totalQuantity);
        calculateTotal(view);
    }

    public void addTenQuantity(View view){
        TextView userTotalQuantity = findViewById(R.id.totalQuantityTextView);
        totalQuantity += 10;
        userTotalQuantity.setText("Quantity: " + totalQuantity);
        calculateTotal(view);
    }

    public void addOneHundredQuantity(View view){
        TextView userTotalQuantity = findViewById(R.id.totalQuantityTextView);
        totalQuantity += 100;
        userTotalQuantity.setText("Quantity: " + totalQuantity);
        calculateTotal(view);
    }

    public void addCustomQuantity(View view){
        TextView userTotalQuantity = findViewById(R.id.totalQuantityTextView);
        EditText customQuantity = findViewById(R.id.customQuantity);
        int customQty = Integer.parseInt(customQuantity.getText().toString());
        totalQuantity += customQty;
        userTotalQuantity.setText("Quantity: " + totalQuantity);
        calculateTotal(view);
    }





    public void onSwitch(View view){
        changeTheme(view);
    }

    public void calculateTotal(View view) {
            TextView userTotalQuantity = findViewById(R.id.totalQuantityTextView);
            TextView totalCostTV = findViewById(R.id.totalCost);

            userTotalQuantity.setText("Quantity: " + totalQuantity);
            totalCost = totalQuantity * MainGameActivity.getCurrencyModalArrayList().get(CryptoSelectorActivity.getCryptoIndex()).getPrice();

            if(buy_sell_switch.isChecked()){
                // user is buying
                totalCostTV.setText("Total Cost: $" + totalCost);;
            }else{
                // user is selling
                totalCostTV.setText("Total Cost: ($" + totalCost + ")");
            }

            changeTheme(view);

    }

    public void displayCurrentInfo() {
        TextView buyingOrSellingPrice = findViewById(R.id.buying_selling_price);
        TextView crypto = findViewById(R.id.cryptoName);
        String cryptoName = CryptoSelectorActivity.getCurrencyModalArrayList().get(CryptoSelectorActivity.getCryptoIndex()).getName();

        buyingOrSellingPrice.setText("Buying/Selling price: $" + CryptoSelectorActivity.getCurrencyModalArrayList().get(CryptoSelectorActivity.getCryptoIndex()).getPrice());
        crypto.setText("Crypto Name: " + cryptoName);

    }

    public void sendOrder(View view){

        double d = MainGameActivity.getCryptoCount();
        if(buy_sell_switch.isChecked()){
            MainGameActivity.setCryptoCount(d - totalCost);
        }else{
            MainGameActivity.setCryptoCount(d + totalCost);
        }
        Intent intent = new Intent(TransactionActivity.this, MainGameActivity.class);
        startActivity(intent);
    }


    public void changeTheme(View view) {
        Button oneQty = findViewById(R.id.quantityOfOne);
        Button tenQty = findViewById(R.id.quantityOfTen);
        Button hundredQty = findViewById(R.id.quantityOfOneHundred);
        Button sendOrder = findViewById(R.id.sendOrder);

        if(buy_sell_switch.isChecked()){
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