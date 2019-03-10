package com.example.android.whatsinmyfridge;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class myDate {
    public int day;
    public int month;
    public int year;

    /**
     * Constructor
     *
     * @param day int
     * @param month int
     * @param year int
     *
     */
    public myDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {

        String stringDay;
        String stringMonth;

        if (day<10){
            stringDay= "0" + Integer.toString(day);
        }
        else{
            stringDay= Integer.toString(day);
        }

        if (month<10){
            stringMonth= "0" + Integer.toString(month);
        }
        else{
            stringMonth= Integer.toString(month);
        }
        return stringDay + "/" + stringMonth + "/" + year;
    }

    /**
     * Gives you the difference between the myDate object entered and the current date.
     *
     * @return  integer, positive, negative or 0 depending on if the date has yet to come
     *   if the date has passed or if it is the same day
     *
     */
    public int difference() {
        Date d = new Date();
        Date old = new Date(this.year - 1900, this.month - 1, this.day);
        long difference = old.getTime() - d.getTime();

        return (int) TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
    }
}
