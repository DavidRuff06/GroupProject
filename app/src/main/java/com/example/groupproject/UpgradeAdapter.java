package com.example.groupproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class UpgradeAdapter extends ArrayAdapter<Upgrade>{
    public UpgradeAdapter(Context context, Upgrade[] upgrades){
        super(context, 0, upgrades);
    }
public View getView(int position,  View convertView, ViewGroup parent){
    Upgrade upgrade = getItem(position);
    TextView tvUpgradeName = (TextView) convertView.findViewById(R.id.upgradeName);
    TextView tvCpsMult = (TextView) convertView.findViewById(R.id.multAmt);
    TextView tvAmtOwned = (TextView) convertView.findViewById(R.id.amtOwned);
    // populate the data into the template view using the data object
    tvUpgradeName.setText(myFood.getName());
    tvItemPrice.setText("$" + myFood.getPrice());
    tvItemDesc.setText(myFood.getDesc());
    // return completed view to render on screen
    return convertView;
}



}
