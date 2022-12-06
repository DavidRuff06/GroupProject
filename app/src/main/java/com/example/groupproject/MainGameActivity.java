package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class MainGameActivity extends AppCompatActivity {
    private static ArrayList<CurrencyModal> currencyModalArrayList;
    private static boolean bitCoinOn = true;
    private static boolean dogeCoinOn;
    private CurrencyRVAdapter currencyRVAdapter;



    public static double cryptoCount;
    public final String TAG = "Group";
    SharedPreferences prefs = null;
    private static DecimalFormat df2 = new DecimalFormat("#.##");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game_activity);
        currencyModalArrayList = new ArrayList<>();
        TextView count = findViewById(R.id.cryptoCount);

        count.setText("$" + df2.format(cryptoCount));
       //getData();
        prefs = getSharedPreferences("com.example.groupproject", MODE_PRIVATE);
    }



    public static double getCryptoCount() {
        return cryptoCount;
    }

    public static void setCryptoCount(double cryptoCount) {
        MainGameActivity.cryptoCount = cryptoCount;
    }

    public void cryptoButtonClicked(View view){
        cryptoCount += 100;
        TextView count = findViewById(R.id.cryptoCount);

        count.setText("$" + df2.format(cryptoCount));
        /*
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        if(count.getTextSize() > 1){
            count.setTextSize(count.getTextSize()-);
        }
        */

    }
    public void cryptoSelectorClicked(View view){
        Intent intent = new Intent(this, CryptoSelectorActivity.class);
        startActivity(intent);
    }


    public void upgradesButtonClicked(View view){
        Intent intent = new Intent(this, UpgradesActivity.class);
        startActivity(intent);
    }

    public void saveProgressButtonClicked(View view){
        SignInActivity.firebaseHelper.updateFirebase();
    }

    public void logOutClicked(View view) {
            SignInActivity.firebaseHelper.logOutUser();
            Log.i(TAG, "user logged out");
            Intent intent = new Intent(MainGameActivity.this, SignInActivity.class);
            startActivity(intent);
        }


    // https://stackoverflow.com/questions/7217578/check-if-application-is-on-its-first-run
    // This is a short tutorial on how to implement a boolean to see if it is the users first time opening the app.
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            Intent intent = new Intent(MainGameActivity.this, SignInActivity.class);
            startActivity(intent);
            // using the following line to edit/commit prefs
            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }
    public static void setBitCoinOn(boolean b) {
        bitCoinOn = b;
    }
    public static boolean getBitCoinOn() {
        return bitCoinOn;
    }

    public static boolean getDogeCoinOn() {
        return dogeCoinOn;
    }

    public static void setDogeCoinOn(boolean dogeCoinOn) {
        MainGameActivity.dogeCoinOn = dogeCoinOn;
    }

    public static double getBitcoinPrice() {
        CurrencyModal bitcoin = currencyModalArrayList.get(0);
        return bitcoin.getPrice();
    }

    public static double getDogeCoinPrice() {
        CurrencyModal dogeCoin = currencyModalArrayList.get(1);
        return dogeCoin.getPrice();
    }

}