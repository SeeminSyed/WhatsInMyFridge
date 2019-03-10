package com.example.android.whatsinmyfridge;

import java.util.Calendar;

public class myDate {
    public int day;
    public int month;
    public int year;

    Calendar calendar = Calendar.getInstance();
    int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
    int currentMonth = calendar.get(Calendar.MONTH);
    int currentYear = calendar.get(Calendar.YEAR);


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
        return day + "/" + month + "/" + year;
    }

    /**
     * Gives you the difference between the myDate object entered and the current date.
     *
     * @param date myDate - date to compare
     * @return  integer, positive, negative or 0 depending on if the date has yet to come
     *   if the date has passed or if it is the same day
     *
     *   Not Completed
     */
    public int difference(myDate date) {
        return 0;
    }
}
