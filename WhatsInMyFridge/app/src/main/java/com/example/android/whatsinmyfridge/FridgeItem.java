package com.example.android.whatsinmyfridge;

import java.util.Calendar;

public class FridgeItem {
    public String name;
    public myDate expiration;
    public int daysLeft;

    /**
     * Constructor
     *
     * @param name String
     * @param expiration
     */
    public FridgeItem(String name, myDate expiration) {
        this.name = name;
        this.expiration = expiration;
        this.daysLeft = expiration.difference(expiration);
    }
}
