package com.example.groupproject;

import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

public class UpgradesActivity extends AppCompatActivity implements SelectListenerUpgrades{

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    private static ArrayList<Upgrade> upgradeArrayList = new ArrayList<Upgrade>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrades);

        RecyclerView upgradesRV = findViewById(R.id.idRVUpgrades);


        if(upgradeArrayList.size() == 0) {
            upgradeArrayList.add(new Upgrade("Miner", 10, .1, 0, R.drawable.miner_200x200));
            upgradeArrayList.add(new Upgrade("Up2", 100, 1, 0, R.drawable.miner_200x200));
            upgradeArrayList.add(new Upgrade("Up3", 1000, 10, 0, R.drawable.miner_200x200));
        }
        UpgradeAdapter upgradeAdapter = new UpgradeAdapter(this, upgradeArrayList, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        upgradesRV.setLayoutManager(linearLayoutManager);
        upgradesRV.setAdapter(upgradeAdapter);
    }


    @Override
    public void onItemClicked(Upgrade upgrade) {
        int delay = 5000; // delay for 5 sec.
        int period = 1000; // repeat every sec.
        Timer timer = new Timer();
        if(MainGameActivity.getCryptoCount() - upgradeArrayList.get(UpgradeAdapter.getP()).getPrice() < 0){
            Toast.makeText(this, "Insufficient Funds",Toast.LENGTH_SHORT).show();
        }else {
            MainGameActivity.setCryptoCount(MainGameActivity.getCryptoCount() - upgradeArrayList.get(UpgradeAdapter.getP()).getPrice());
            upgradeArrayList.get(UpgradeAdapter.getP()).setAmtOwned(upgradeArrayList.get(UpgradeAdapter.getP()).getAmtOwned() + 1);
            TextView count = findViewById(R.id.amtOwned);
            count.setText(upgradeArrayList.get(UpgradeAdapter.getP()).getAmtOwned() + "");

            Log.i("Logan", upgradeArrayList.get(UpgradeAdapter.getP()).getAmtOwned() + " Of this upgrade owned");
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            double d = MainGameActivity.getCryptoCount() + upgradeArrayList.get(UpgradeAdapter.getP()).getCpsMult();
                            MainGameActivity.setCryptoCount(d);
                            Log.i("Logan", MainGameActivity.getCryptoCount() + " currency");
                        }
                    });
//                    MainGameActivity.setCryptoCount(d);
//                    Log.i("Logan", MainGameActivity.getCryptoCount() + " currency");

                }
            }, delay, period);
        }
    }

    public static ArrayList<Upgrade> getUpgradeArrayList() {
        return upgradeArrayList;
    }
}