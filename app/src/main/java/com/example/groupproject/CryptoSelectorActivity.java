package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class CryptoSelectorActivity extends AppCompatActivity {

    // creating variable for recycler view,
    // adapter, array list, progress bar
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    public static ArrayList<CurrencyModal> currencyModalArrayList;


    public int bitCoinCount;
    public int dogeCoinCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_selector);


        currencyModalArrayList = new ArrayList<>();
        getData();
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
                        if(name.equals("Bitcoin")) {
                            currencyModalArrayList.add(0, new CurrencyModal(name, symbol, price));
                        }
                        if(name.equals("Dogecoin")){
                            currencyModalArrayList.add(1, new CurrencyModal(name, symbol, price));
                        }
                        TextView bitCoinPrice = findViewById(R.id.bitCoinPrice);
                        bitCoinPrice.setText(df2.format(getBitcoinPrice()));
                    }
                    // notifying adapter on data change.
                } catch (JSONException e) {
                    // handling json exception.
                    e.printStackTrace();
                    Toast.makeText(CryptoSelectorActivity.this, "Something went amiss. Please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // displaying error response when received any error.
                Toast.makeText(CryptoSelectorActivity.this, "Something went amiss. Please try again later", Toast.LENGTH_SHORT).show();
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
    public ArrayList getArrayList(){
        return currencyModalArrayList;
    }

    public static double getBitcoinPrice(){
        CurrencyModal bitcoin = currencyModalArrayList.get(0);
        return bitcoin.getPrice();
    }


    public void bitCoinClick(View view){
        if(!MainGameActivity.getBitCoinOn())
        MainGameActivity.setBitCoinOn(true);
        else
            MainGameActivity.setBitCoinOn(false);
    }



}