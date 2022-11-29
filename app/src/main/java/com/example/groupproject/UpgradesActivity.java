package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UpgradesActivity extends AppCompatActivity {
    private TextView name;
    private TextView cpsMult;
    private TextView amtOwned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createUpgrades();
        setContentView(R.layout.activity_upgrades);
        name = findViewById(R.id.upgradeName);;
        cpsMult = findViewById(R.id.multAmt);;
        TextView amtOwned = findViewById(R.id.amtOwned);;

    }

    public void createUpgrades(){
        Upgrade[] listOfUpgrades = new Upgrade[1];
        Upgrade miner = new Upgrade("Miner",0.1, 0,  getResources().getIdentifier("miner_200x200" , "drawable" ,
                getPackageName()));
        listOfUpgrades[0] = miner;
        //for (int i = 0; i < listOfUpgrades.length; i++){
            name.setText(/*listOfUpgrades[0].getUpgradeName()*/ "hi");
            String mult = "" + listOfUpgrades[0].getCpsMult();
            //cpsMult.setText("mult");
            //amtOwned.setText("listOfUpgrades[0].getAmtOwned()");
        //}
    }

}