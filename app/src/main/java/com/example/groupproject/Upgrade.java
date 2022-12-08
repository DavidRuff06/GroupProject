package com.example.groupproject;

public class Upgrade {
    private String upgradeName;
    private int price;
    private double cpsMult;
    private int amtOwned;
    private int imageResourceId;



    public Upgrade(String upgradeName,int price, double cpsMult, int amtOwned, int imageResourceId) {
        this.upgradeName = upgradeName;
        this.price = price;
        this.cpsMult = cpsMult;
        this.amtOwned = amtOwned;
        this.imageResourceId = imageResourceId;
    }

    public void buyUpgrade(){
        amtOwned++;
        cpsMult = cpsMult + (cpsMult*.1);
    }



    public String getUpgradeName() {
        return upgradeName;
    }

    public void setUpgradeName(String upgradeName) {
        this.upgradeName = upgradeName;
    }

    public double getCpsMult() {
        return cpsMult;
    }

    public void setCpsMult(double cpsMult) {
        this.cpsMult = cpsMult;
    }

    public int getAmtOwned() {
        return amtOwned;
    }

    public void setAmtOwned(int amtOwned) {
        this.amtOwned = amtOwned;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

}
