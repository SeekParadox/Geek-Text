package com.geektext.backend.models;

import java.util.Date;

public class Comment {
    private final String comment;
    private final Date date;


    public Comment(String comment, Date date) {
        this.comment = comment;
        this.date = date;
    }


    public String getComment() {
        return comment;
    }


    public Date getDate() {
        return date;
    }

}
