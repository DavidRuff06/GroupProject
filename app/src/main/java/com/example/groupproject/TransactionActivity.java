package com.example.groupproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class TransactionActivity extends AppCompatActivity {

    private Switch buy_sell_switch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        buy_sell_switch = findViewById(R.id.buy_sell_switch);
        displayCurrentInfo();
        EditText customQuantity = findViewById(R.id.customQuantity);
        customQuantity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    addCustomQuantity(v);
                }
                return false;
            }
        });
    }

    int totalQuantity = 0;
    double totalCost;
    double roundedCryptoValue = (double) Math.round(CryptoSelectorActivity.
            getCurrencyModalArrayList().get(CryptoSelectorActivity.getCryptoIndex()).
            getPrice() * 100) / 100;
    double roundedTotalCost;



    // These methods will keep track of the user's total purchase quantity and add to int totalQuantity
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

    public void clearQuantity(View view){
        TextView userTotalQuantity = findViewById(R.id.totalQuantityTextView);
        totalQuantity = 0;
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

            totalCost = totalQuantity * roundedCryptoValue;
            roundedTotalCost = (double) Math.round(totalCost * 100) / 100;
            if(buy_sell_switch.isChecked()){
                // user is buying
                totalCostTV.setText("Total Cost: $" + roundedTotalCost);
            }else{
                // user is selling
                totalCostTV.setText("Total Cost: ($" + roundedTotalCost + ")");
            }
            changeTheme(view);
    }

    public void displayCurrentInfo() {
        TextView buyingOrSellingPrice = findViewById(R.id.buying_selling_price);
        TextView crypto = findViewById(R.id.cryptoName);
        String cryptoName = CryptoSelectorActivity.getCurrencyModalArrayList().get(CryptoSelectorActivity.getCryptoIndex()).getName();
        buyingOrSellingPrice.setText("Buying/Selling price: $" + roundedCryptoValue);
        crypto.setText("Crypto Name: " + cryptoName);
    }

    public void sendOrder(View view){
        double cashBalance = MainGameActivity.getCryptoCount();

        if(buy_sell_switch.isChecked()){
            // user is buying so their cash balance will decrease
            if(cashBalance < roundedTotalCost){
                //user is trying to buy more than they can afford
                Toast.makeText(this, "You have insufficient funds for this transaction", Toast.LENGTH_SHORT).show();

            }else{
                int i = CryptoSelectorActivity.cryptoQuantity.get(CryptoSelectorActivity.getCryptoIndex());
                i += totalQuantity;
                CryptoSelectorActivity.cryptoQuantity.set(CryptoSelectorActivity.getCryptoIndex(), i);

                MainGameActivity.setCryptoCount(cashBalance - roundedTotalCost);

                Intent intent = new Intent(TransactionActivity.this, MainGameActivity.class);
                startActivity(intent);
            }
        }else{
            // user is selling so their cash balance will increase
            if (totalQuantity > CryptoSelectorActivity.cryptoQuantity.get(CryptoSelectorActivity.getCryptoIndex())) {
                //user is trying to sell more shares than they actually own
                Toast.makeText(this, "You are trying to sell more shares than you own", Toast.LENGTH_SHORT).show();
            }else{
                //CryptoSelectorActivity.cryptoQuantity.set(CryptoSelectorActivity.getCryptoIndex(), CryptoSelectorActivity.getCryptoQuantity(CryptoSelectorActivity.getCryptoIndex()) -=totalQuantity);
                int i = CryptoSelectorActivity.cryptoQuantity.get(CryptoSelectorActivity.getCryptoIndex());
                i -= totalQuantity;
                CryptoSelectorActivity.cryptoQuantity.set(CryptoSelectorActivity.getCryptoIndex(), i);
                Intent intent = new Intent(TransactionActivity.this, MainGameActivity.class);
                startActivity(intent);
            }
        }

    }

    public void changeTheme(View view) {
        Button oneQty = findViewById(R.id.quantityOfOne);
        Button tenQty = findViewById(R.id.quantityOfTen);
        Button hundredQty = findViewById(R.id.quantityOfOneHundred);
        Button sendOrder = findViewById(R.id.sendOrder);
        Button clearQuantity = findViewById(R.id.clearQuantity);
        TextView totalCostTV = findViewById(R.id.totalCost);
        if(buy_sell_switch.isChecked()){
            oneQty.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.buyButtonColor));
            tenQty.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.buyButtonColor));
            hundredQty.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.buyButtonColor));
            sendOrder.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.buyButtonColor));
            clearQuantity.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.buyButtonColor));
            totalCostTV.setText("Total Cost: $" + roundedTotalCost);
        }else{
            oneQty.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.sellButtonColor));
            tenQty.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.sellButtonColor));
            hundredQty.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.sellButtonColor));
            sendOrder.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.sellButtonColor));
            clearQuantity.setBackgroundColor(oneQty.getContext().getResources().getColor(R.color.sellButtonColor));
            totalCostTV.setText("Total Cost: ($" + roundedTotalCost + ")");
        }
    }
}