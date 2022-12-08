package com.example.groupproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class UpgradeAdapter extends RecyclerView.Adapter<UpgradeAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Upgrade> UpgradeArrayList;
    private SelectListenerUpgrades listener;
    private static int p;

    //Constructor
    public UpgradeAdapter(Context context, ArrayList<Upgrade> upgradeArrayList, SelectListenerUpgrades listener) {
        this.context = context;
        this.UpgradeArrayList = upgradeArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UpgradeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(context).inflate(R.layout.row_card_upgrade, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpgradeAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // to set data to textview and imageview of each card layout
        Upgrade upgrade = UpgradeArrayList.get(position);
        holder.TVUpgradeName.setText(upgrade.getUpgradeName());
        holder.TVmult.setText("" + upgrade.getCpsMult());
        holder.TVAmtOwned.setText("" + upgrade.getAmtOwned());
        holder.upgradeImage.setImageResource(upgrade.getImageResourceId());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p = position;
                listener.onItemClicked(UpgradeArrayList.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return UpgradeArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView upgradeImage;
        private final TextView TVUpgradeName;
        private final TextView TVmult;
        private final TextView TVAmtOwned;
        public CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            upgradeImage = itemView.findViewById(R.id.upgradeImage);
            TVUpgradeName = itemView.findViewById(R.id.TVUpgradeName);
            TVmult = itemView.findViewById(R.id.mult);
            TVAmtOwned = itemView.findViewById(R.id.amtOwned);
            cardView = itemView.findViewById(R.id.upgradeRow);
        }
    }

    public static int getP() {
        return p;
    }
}
