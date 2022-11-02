package com.example.groupproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Crypto {
    private String cryptoName;
    private Double cryptoValue;
    private String cryptoTag;
    private int cryptoImageId;

    public Crypto(String cryptoName, Double cryptoValue, String cryptoTag) {
        this.cryptoName = cryptoName;
        this.cryptoValue = cryptoValue;
        this.cryptoTag = cryptoTag;
    }

    public Crypto(String cryptoName, Double cryptoValue, String cryptoTag, int cryptoImageId) {
        this.cryptoName = cryptoName;
        this.cryptoValue = cryptoValue;
        this.cryptoTag = cryptoTag;
        this.cryptoImageId = cryptoImageId;
    }

    public Crypto() {
        this.cryptoName = "No Name";
        this.cryptoValue = 0.0;
        this.cryptoTag = "No Tag";
        this.cryptoImageId = 0;
    }

    // Parcel Implementation

    public Crypto(Parcel parcel){
        this.cryptoName = parcel.readString();
        this.cryptoValue = parcel.readDouble();
        this.cryptoTag = parcel.readString();
        this.cryptoImageId = parcel.readInt();
    }

    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(cryptoName);
        dest.writeDouble(cryptoValue);
        dest.writeString(cryptoTag);
        dest.writeInt(cryptoImageId);
    }
    public static final Parcelable.Creator<Crypto> CREATOR = new
            Parcelable.Creator<Crypto>() {

                @Override
                public Crypto createFromParcel(Parcel parcel) {
                    return new Crypto(parcel);
                }

                @Override
                public Crypto[] newArray(int size) {
                    return new Crypto[0];
                }
            };


    public int describeContents() {
        return 0;
    }

    // End of parcel implementation

    public String toString() {
        return "Value: " + cryptoValue + " " + cryptoName;
    }



    // Getter and setters

    public String getCryptoName() {
        return cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    public Double getCryptoValue() {
        return cryptoValue;
    }

    public void setCryptoValue(Double cryptoValue) {
        this.cryptoValue = cryptoValue;
    }

    public String getCryptoTag() {
        return cryptoTag;
    }

    public void setCryptoTag(String cryptoTag) {
        this.cryptoTag = cryptoTag;
    }

    public int getCryptoImageId() {
        return cryptoImageId;
    }

    public void setCryptoImageId(int cryptoImageId) {
        this.cryptoImageId = cryptoImageId;
    }
}
