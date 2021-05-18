package com.example.radiomonastir.Models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Studio {
    private String studioId;
    private String studioNumber;
    private String studioName;

    public Studio() {
        //this constructor is required
    }

    public Studio(String studioId, String studioNumber, String studioName) {
        this.studioId = studioId;
        this.studioNumber = studioNumber;
        this.studioName = studioName;
    }

    public String getStudioId() {
        return studioId;
    }

    public String getStudioNumber() {
        return studioNumber;
    }

    public String getStudioName() {
        return studioName;
    }
    @Override
    public String toString() {
        return studioName ;
    }
}