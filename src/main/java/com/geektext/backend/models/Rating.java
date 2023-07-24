package com.geektext.backend.models;

import java.util.Date;

public class Rating {
    private int value; // Rating value (between 1 and 5)
    private Date date; // Date of the rating


    public Rating(int value, Date date) {
        this.value = value;
        this.date = date;
    }


    public int getValue() {
        return value;
    }


    public Date getDate() {
        return date;
    }

}
