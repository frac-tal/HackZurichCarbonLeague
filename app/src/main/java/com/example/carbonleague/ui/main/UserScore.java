package com.example.carbonleague.ui.main;

public class UserScore {

    private String userName;
    private int co2;
    private boolean isVegan;

    public UserScore(String userName, int co2, boolean isVegan) {
        this.userName = userName;
        this.co2 = co2;
        this.isVegan = isVegan;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }
}
