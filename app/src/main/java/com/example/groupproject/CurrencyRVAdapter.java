package com.example.groupproject;

import android.annotation.SuppressLint;
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

// on below line we are creating our adapter class
// in this class we are passing our array list
// and our View Holder class which we have created.
public class CurrencyRVAdapter extends RecyclerView.Adapter<CurrencyRVAdapter.CurrencyViewholder> {
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private ArrayList<CurrencyModal> currencyModals;
    private Context context;
    private SelectListener listener;
    private static int p;

    public CurrencyRVAdapter(ArrayList<CurrencyModal> currencyModals, Context context, SelectListener listener) {
        this.currencyModals = currencyModals;
        this.context = context;
        this.listener = listener;
    }

    // below is the method to filter our list.
    public void filterList(ArrayList<CurrencyModal> filterlist) {
        // adding filtered list to our
        // array list and notifying data set changed
        currencyModals = filterlist;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CurrencyRVAdapter.CurrencyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        View view = LayoutInflater.from(context).inflate(R.layout.currency_rv_item, parent, false);
        return new CurrencyRVAdapter.CurrencyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyRVAdapter.CurrencyViewholder holder, @SuppressLint("RecyclerView") int position) {
        // on below line we are setting data to our item of
        // recycler view and all its views.
        CurrencyModal modal = currencyModals.get(position);
        holder.nameTV.setText(modal.getName());
        holder.rateTV.setText("$" + df2.format(modal.getPrice()));
        holder.symbolTV.setText(modal.getSymbol());
        holder.amountTV.setText("" + modal.getAmount());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = position;
                listener.onItemClicked(currencyModals.get(position));
            }
        });


    }


    @Override
    public int getItemCount() {
        // on below line we are returning
        // the size of our array list.
        return currencyModals.size();
    }

    // on below line we are creating our view holder class
    // which will be used to initialize each view of our layout file.
    public class CurrencyViewholder extends RecyclerView.ViewHolder {
        private TextView symbolTV, rateTV, nameTV, amountTV;
        public CardView cardView;

        public CurrencyViewholder(@NonNull View itemView) {
            super(itemView);
            // on below line we are initializing all
            // our text views along with  its ids.
            symbolTV = itemView.findViewById(R.id.idTVSymbol);
            rateTV = itemView.findViewById(R.id.idTVRate);
            nameTV = itemView.findViewById(R.id.idTVName);
            cardView = itemView.findViewById(R.id.idCVCurrency);
            amountTV = itemView.findViewById(R.id.cryptoNum);
        }
    }

    public static int getP() {
        return p;
    }
}
