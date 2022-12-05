package com.example.groupproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class UpgradeRVAdapter extends RecyclerView.Adapter<UpgradeRVAdapter.UpgradeViewholder> {
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private ArrayList<Upgrade> upgrades;
    private Context context;
    private UpgradeSelectListener listener;

    public UpgradeRVAdapter(ArrayList<Upgrade> upgrades, Context context, UpgradeSelectListener listener) {
        this.upgrades = upgrades;
        this.context = context;
        this.listener = listener;
    }

    public void filterList(ArrayList<Upgrade> filterlist) {
        // adding filtered list to our
        // array list and notifying data set changed
        upgrades = filterlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UpgradeRVAdapter.UpgradeViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        View view = LayoutInflater.from(context).inflate(R.layout.currency_rv_item, parent, false);
        return new UpgradeRVAdapter.UpgradeViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpgradeRVAdapter.UpgradeViewholder holder, int position) {
        // on below line we are setting data to our item of
        // recycler view and all its views.
        Upgrade u = upgrades.get(position);
        holder.upgradeNameTV.setText(u.getUpgradeName());
        holder.upgradeMultTV.setText("$ " + df2.format(u.getCpsMult()));
        holder.amtOwnedTV.setText(u.getAmtOwned());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(upgrades.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        // on below line we are returning
        // the size of our array list.
        return upgrades.size();
    }


    public class UpgradeViewholder extends RecyclerView.ViewHolder {
        private TextView amtOwnedTV, upgradeMultTV, upgradeNameTV;
        public CardView cardView;

        public UpgradeViewholder(@NonNull View itemView) {
            super(itemView);
            // on below line we are initializing all
            // our text views along with  its ids.
            upgradeNameTV = itemView.findViewById(R.id.upgradeName);
            upgradeMultTV = itemView.findViewById(R.id.multAmt);
            amtOwnedTV = itemView.findViewById(R.id.amtOwned);
            cardView = itemView.findViewById(R.id.upgradeCV);
        }
    }

    public static void onItemClick(View view, ArrayList<Upgrade> al){
        if(al.get(0).getUpgradeName().equals("Miner")){
            Log.i("Logan", "MINER HERE");
        } if (al.get(1).getUpgradeName().equals("up2")){
            Log.i("Tag", "UP2 HERE");
        }
    }
}
