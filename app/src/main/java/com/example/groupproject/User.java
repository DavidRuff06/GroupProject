package com.example.groupproject;

public class User {
    private String Username, Currency;

    public User(){
    }

    public User(String username, String currency) {
        Username = username;
        Currency = currency;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }
}
