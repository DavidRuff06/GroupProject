package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
       getData();
        prefs = getSharedPreferences("com.example.groupproject", MODE_PRIVATE);
    }

    public static double getCryptoCount() {
        return cryptoCount;
    }

    public static void setCryptoCount(double cryptoCount) {
        MainGameActivity.cryptoCount = cryptoCount;
    }

    public void cryptoButtonClicked(View view){
        if(bitCoinOn){
            CryptoSelectorActivity.setTotalBitcoin(CryptoSelectorActivity.getTotalBitcoin() + 1);
            cryptoCount = CryptoSelectorActivity.getTotalBitcoin();
        } else if(dogeCoinOn){
            cryptoCount += CryptoSelectorActivity.getDogeCoinPrice();
        }else {
            cryptoCount++;

        }
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
        SignInActivity.firebaseHelper.addData(cryptoCount);
    }

    public void logOutClicked(View view) {
        boolean isFirstTime = true;
        if(!isFirstTime) {
            SignInActivity.firebaseHelper.logOutUser();
            Log.i(TAG, "user logged out");
        }else {
            Intent intent = new Intent(MainGameActivity.this, SignInActivity.class);
            startActivity(intent);
            isFirstTime= false;
        }
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


    private void getData() {
        // creating a variable for storing our string.
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        // creating a variable for request queue.
        RequestQueue queue = Volley.newRequestQueue(this);
        // making a json object request to fetch data from API.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // inside on response method extracting data
                // from response and passing it to array list
                // on below line we are making our progress
                // bar visibility to gone.
                try {
                    // extracting data from json.
                    JSONArray dataArray = response.getJSONArray("data");
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject dataObj = dataArray.getJSONObject(i);
                        String symbol = dataObj.getString("symbol");
                        String name = dataObj.getString("name");
                        JSONObject quote = dataObj.getJSONObject("quote");
                        JSONObject USD = quote.getJSONObject("USD");
                        double price = USD.getDouble("price");
                        // adding all data to our array list.
                        if (name.equals("Bitcoin")) {
                            currencyModalArrayList.add(0, new CurrencyModal(name, symbol, price));
                        }
                        if (name.equals("Dogecoin")) {
                            currencyModalArrayList.add(1, new CurrencyModal(name, symbol, price));
                        }
                    }
                    // notifying adapter on data change.
                } catch (JSONException e) {
                    // handling json exception.
                    e.printStackTrace();
                    Toast.makeText(MainGameActivity.this, "Something went amiss. Please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // displaying error response when received any error.
                Toast.makeText(MainGameActivity.this, "Something went amiss. Please try again later", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                // in this method passing headers as
                // key along with value as API keys.
                HashMap<String, String> headers = new HashMap<>();
                headers.put("X-CMC_PRO_API_KEY", "1cae5f1b-5842-4ad9-8920-69ed8bb95f58");
                // at last returning headers
                return headers;
            }
        };
        // calling a method to add our
        // json object request to our queue.
        queue.add(jsonObjectRequest);
    }

    public static ArrayList<CurrencyModal> getCurrencyModalArrayList() {
        return currencyModalArrayList;
    }

    public static void setCurrencyModalArrayList(ArrayList<CurrencyModal> currencyModalArrayList) {
        MainGameActivity.currencyModalArrayList = currencyModalArrayList;
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