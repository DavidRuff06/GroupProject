package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UpgradesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createUpgrades();
        setContentView(R.layout.activity_upgrades);

    }

    public void createUpgrades(){
        TextView name = findViewById(R.id.upgradeName);
        TextView cpsMult = findViewById(R.id.multAmt);
        TextView amtOwned = findViewById(R.id.amtOwned);
        Upgrade[] listOfUpgrades = new Upgrade[1];
        Upgrade miner = new Upgrade("Miner",0.1, 0,  getResources().getIdentifier("miner_200x200" , "drawable" ,
                getPackageName()));
        for (int i = 0; i < listOfUpgrades.length; i++){
            listOfUpgrades[i].getUpgradeName().
        }
    }

}