package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.checkerframework.checker.units.qual.C;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class CryptoSelectorActivity extends AppCompatActivity implements SelectListener{


    // creating variable for recycler view,
    // adapter, array list, progress bar
    private RecyclerView currencyRV;
    private EditText searchEdt;
    private static ArrayList<CurrencyModal> currencyModalArrayList;
    private static ArrayList<CurrencyModal> holderArrayList;
    private CurrencyRVAdapter currencyRVAdapter;
    private ProgressBar loadingPB;
    private static int totalBitcoin;
    private TextView bitCointot;
    public static int cryptoIndex;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_selector);

        // initializing all our variables and array list.
        loadingPB = findViewById(R.id.idPBLoading);
        currencyRV = findViewById(R.id.idRVcurrency);
        bitCointot = findViewById(R.id.bitCointotal);
        holderArrayList = new ArrayList<>();
        currencyModalArrayList = new ArrayList<>();

        // initializing our adapter class.
        currencyRVAdapter = new CurrencyRVAdapter(currencyModalArrayList, this, this);

        // setting layout manager to recycler view.
        currencyRV.setLayoutManager(new LinearLayoutManager(this));

        // setting adapter to recycler view.
        currencyRV.setAdapter(currencyRVAdapter);


/*
Need to research how to get an onItemClickListener for the recyclerview.  Need to add it to the recyclerview
Try adding this to currencyRV
*/

        // calling get data method to get data from API.
        getData();
        bitSet();
        //fillCurrencyModel();


    }

    public void bitSet(){
        bitCointot.setText("" + (int)MainGameActivity.getCryptoCount());
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
                loadingPB.setVisibility(View.GONE);
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
                        holderArrayList.add(new CurrencyModal(name, symbol, price));
                    }
                    fillCurrencyModel();


                    // notifying adapter on data change.
                    currencyRVAdapter.notifyDataSetChanged();
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

    public ArrayList getArrayList() {
        return currencyModalArrayList;
    }

    public static double getBitcoinPrice() {
        CurrencyModal bitcoin = currencyModalArrayList.get(0);
        return bitcoin.getPrice();
    }

    public static double getDogeCoinPrice() {
        CurrencyModal dogeCoin = currencyModalArrayList.get(1);
        return dogeCoin.getPrice();
    }


    public void bitCoinClick(View view) {
        CurrencyRVAdapter.onItemClick(view, currencyModalArrayList);
//        transactionClicked(view);
    }

    public void transactionClicked(View view){
        Intent intent = new Intent(this, TransactionActivity.class);
        startActivity(intent);
    }

    public static int getTotalBitcoin() {
        return totalBitcoin;
    }

    public static void setTotalBitcoin(int totalBitcoin) {
        CryptoSelectorActivity.totalBitcoin = totalBitcoin;
    }

    public static int getCryptoIndex() {
        return cryptoIndex;
    }

    @Override
    public void onItemClicked(CurrencyModal currencyModal) {
        Toast.makeText(this, currencyModal.getName(), Toast.LENGTH_SHORT).show();
        if(currencyModal.getName().equals("Bitcoin"))
            cryptoIndex = 0;
        else if(currencyModal.getName().equals("Dogecoin"))
            cryptoIndex = 1;
        Intent intent = new Intent(this, TransactionActivity.class);
        startActivity(intent);
    }

    public void fillCurrencyModel(){
//        Log.i("David", a+"");
        for (int a = holderArrayList.size()-1; a > 0; a-- ) {
            Log.i("David", "Cool");
            String newName = holderArrayList.get(a).getName();
            String newSymbol = holderArrayList.get(a).getSymbol();
            double newPrice = holderArrayList.get(a).getPrice();
            if (newName.equals("Dogecoin")) {
                currencyModalArrayList.add(0, new CurrencyModal(newName, newSymbol, newPrice));
            }
        }

        for (int a = holderArrayList.size()-1; a > 0; a-- ) {
            Log.i("David", "Cool");
            String newName = holderArrayList.get(a).getName();
            String newSymbol = holderArrayList.get(a).getSymbol();
            double newPrice = holderArrayList.get(a).getPrice();
            if (newName.equals("Cosmos")) {
                currencyModalArrayList.add(1, new CurrencyModal(newName, newSymbol, newPrice));
            }
        }

        for (int a = holderArrayList.size()-1; a > 0; a-- ) {
            String newName = holderArrayList.get(a).getName();
            String newSymbol = holderArrayList.get(a).getSymbol();
            double newPrice = holderArrayList.get(a).getPrice();
            if (newName.equals("Bitcoin")) {
                currencyModalArrayList.add(2, new CurrencyModal(newName, newSymbol, newPrice));
            }
        }
    }
}
