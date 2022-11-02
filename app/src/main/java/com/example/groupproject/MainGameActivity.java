package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainGameActivity extends AppCompatActivity {
    int cryptoCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game_activity);
    }


    public void cryptoButtonClicked(View view){
        cryptoCount ++;
        TextView count = findViewById(R.id.cryptoCount);
        count.setText(cryptoCount + " Crypto");
    }
    public void cryptoSelectorClicked(View view){
        Intent intent = new Intent(this, CryptoSelectorActivity.class);
        startActivity(intent);
    }
}