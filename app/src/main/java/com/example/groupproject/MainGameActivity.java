package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainGameActivity extends AppCompatActivity {
    private static boolean bitCoinOn;
    int cryptoCount;
    public final String TAG = "Group";
    SharedPreferences prefs = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game_activity);

        prefs = getSharedPreferences("com.example.groupproject", MODE_PRIVATE);
    }


    public void cryptoButtonClicked(View view){
        if(bitCoinOn){
            cryptoCount += CryptoSelectorActivity.getBitCoinPrice();
        }
        cryptoCount++;
        TextView count = findViewById(R.id.cryptoCount);
        count.setText(cryptoCount + " Crypto");
    }
    public void cryptoSelectorClicked(View view){
        Intent intent = new Intent(this, CryptoSelectorActivity.class);
        startActivity(intent);
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
}