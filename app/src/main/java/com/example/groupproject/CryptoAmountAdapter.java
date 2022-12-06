package com.example.groupproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CryptoAmountAdapter extends RecyclerView.Adapter<com.example.groupproject.CryptoAmountAdapter.CurrencyViewholder>{
        private static DecimalFormat df2 = new DecimalFormat("#.##");
        private ArrayList<Integer> ints;
        private Context context;
        private static int p;

        public CryptoAmountAdapter(ArrayList<Integer> ints, Context context) {
        this.ints  = ints;
            this.context = context;
        }



        @NonNull
        @Override
        public com.example.groupproject.CryptoAmountAdapter.CurrencyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // this method is use to inflate the layout file
            // which we have created for our recycler view.
            // on below line we are inflating our layout file.
            View view = LayoutInflater.from(context).inflate(R.layout.crypto_cardview, parent, false);
            return new com.example.groupproject.CryptoAmountAdapter.CurrencyViewholder(view);
        }

    @Override
        public void onBindViewHolder(@NonNull com.example.groupproject.CryptoAmountAdapter.CurrencyViewholder holder, int position) {

            // on below line we are setting data to our item of
            // recycler view and all its views.
            int modal = ints.get(position);


        }


        @Override
        public int getItemCount() {
            // on below line we are returning
            // the size of our array list.
            return ints.size();
        }

        // on below line we are creating our view holder class
        // which will be used to initialize each view of our layout file.
        public class CurrencyViewholder extends RecyclerView.ViewHolder {
            private TextView amountTV;
            public CardView cardView;

            public CurrencyViewholder(@NonNull View itemView) {
                super(itemView);
                // on below line we are initializing all
                // our text views along with  its ids.
                amountTV = itemView.findViewById(R.id.cryptoNum);
                cardView = itemView.findViewById(R.id.cardView);
            }
        }

        public static int getP() {
            return p;
        }
    }
