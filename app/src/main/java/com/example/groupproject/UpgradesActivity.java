package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpgradesActivity extends AppCompatActivity implements UpgradeSelectListener{
    private RecyclerView upgradesRV;
    private static ArrayList<Upgrade> upgradeArrayList;
    private static ArrayList<Upgrade> upgradeHolderArrayList;
    private UpgradeRVAdapter upgradeRVAdapter;
    public static int upgradeIndex;

    public static int[] upgradeCount = new int[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrades);
        upgradeArrayList = new ArrayList<>();
        upgradeHolderArrayList = new ArrayList<>();
        upgradesRV = findViewById(R.id.upgradeList);

        upgradeRVAdapter = new UpgradeRVAdapter(upgradeArrayList, this, this);

        upgradesRV.setLayoutManager(new LinearLayoutManager(this));

        upgradesRV.setAdapter(upgradeRVAdapter);

        fillUpgrade();


    }

    public static int getUpgradeIndex() {
        return upgradeIndex;
    }

    @Override
    public void onItemClicked(Upgrade upgrade) {
        Toast.makeText(this, upgrade.getUpgradeName(), Toast.LENGTH_SHORT).show();
        if(upgrade.getUpgradeName().equals("Miner"))
            upgradeIndex = 0;
        else if(upgrade.getUpgradeName().equals("Up2"))
            upgradeIndex = 1;
        else if(upgrade.getUpgradeName().equals("Up3"))
            upgradeIndex = 2;

    }

    public void fillUpgrade(){

        for (int a = upgradeHolderArrayList.size()-1; a >= 0; a-- ) {
            String newName = upgradeHolderArrayList.get(a).getUpgradeName();
            double newMult = upgradeHolderArrayList.get(a).getCpsMult();
            int amtOwned = upgradeHolderArrayList.get(a).getAmtOwned();
            if (newName.equals("Miner")) {
                upgradeArrayList.add(0, new Upgrade(newName, newMult, amtOwned));
            }
        }

        for (int a = upgradeHolderArrayList.size()-1; a >= 0; a-- ) {
            String newName = upgradeHolderArrayList.get(a).getUpgradeName();
            double newMult = upgradeHolderArrayList.get(a).getCpsMult();
            int amtOwned = upgradeHolderArrayList.get(a).getAmtOwned();
            if (newName.equals("Up2")) {
                upgradeArrayList.add(1, new Upgrade(newName, newMult, amtOwned));
            }
        }

        for (int a = upgradeHolderArrayList.size()-1; a >= 0; a-- ) {
            String newName = upgradeHolderArrayList.get(a).getUpgradeName();
            double newMult = upgradeHolderArrayList.get(a).getCpsMult();
            int amtOwned = upgradeHolderArrayList.get(a).getAmtOwned();
            if (newName.equals("Up3")) {
                upgradeArrayList.add(2, new Upgrade(newName, newMult, amtOwned));
            }
        }
    }



    public RecyclerView getUpgradesRV() {
        return upgradesRV;
    }

    public void setUpgradesRV(RecyclerView upgradesRV) {
        this.upgradesRV = upgradesRV;
    }

    public static ArrayList<Upgrade> getUpgradeArrayList() {
        return upgradeArrayList;
    }

    public static void setUpgradeArrayList(ArrayList<Upgrade> upgradeArrayList) {
        UpgradesActivity.upgradeArrayList = upgradeArrayList;
    }

    public static ArrayList<Upgrade> getUpgradeHolderArrayList() {
        return upgradeHolderArrayList;
    }

    public static void setUpgradeHolderArrayList(ArrayList<Upgrade> upgradeHolderArrayList) {
        UpgradesActivity.upgradeHolderArrayList = upgradeHolderArrayList;
    }

    public UpgradeRVAdapter getUpgradeRVAdapter() {
        return upgradeRVAdapter;
    }

    public void setUpgradeRVAdapter(UpgradeRVAdapter upgradeRVAdapter) {
        this.upgradeRVAdapter = upgradeRVAdapter;
    }

    public static int[] getUpgradeCount() {
        return upgradeCount;
    }

    public static void setUpgradeCount(int[] upgradeCount) {
        UpgradesActivity.upgradeCount = upgradeCount;
    }


}