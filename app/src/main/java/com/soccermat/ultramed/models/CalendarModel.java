package com.soccermat.ultramed.models;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class CalendarModel implements Serializable {

    @DatabaseField(generatedId = true)
    public int id;

    @DatabaseField(
            columnName = "Date"
    )
    public String date;

    @DatabaseField(
            columnName = "Status"
    )
    public String status;

    @DatabaseField(
            columnName = "Month"
    )
    public String month;

    @DatabaseField(
            columnName = "Year"
    )
    public String year;


    public CalendarModel(String date, String status, String month, String year) {
        this.date = date;
        this.status = status;
        this.month = month;
        this.year = year;
    }

    public CalendarModel() {
    }
}

