package com.example.groupproject;

import android.os.Parcel;
import android.os.Parcelable;

public class CurrencyModal {
    // variable for currency name,
    // currency symbol and price.
    private String name;
    private String symbol;
    private double price;

    public CurrencyModal(String name, String symbol, double price) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
    }
    public CurrencyModal(){
        this.name = "";
        this.symbol = "";
        this.price = 0.0;
    }

    // Implementation of parcel

    public CurrencyModal(Parcel parcel){
        this.name = parcel.readString();
        this.symbol = parcel.readString();
        this.price = parcel.readDouble();
    }
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(name);
        dest.writeString(symbol);
        dest.writeDouble(price);
    }
    public static final Parcelable.Creator<CurrencyModal> CREATOR = new
            Parcelable.Creator<CurrencyModal>() {

                @Override
                public CurrencyModal createFromParcel(Parcel parcel) {
                    return new CurrencyModal(parcel);
                }

                @Override
                public CurrencyModal[] newArray(int size) {
                    return new CurrencyModal[0];
                }
            };

    public int describeContents() {
        return 0;
    }

    // End of parcel implementation

    public String toString() {
        return "Value: " + price + " " + name;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}