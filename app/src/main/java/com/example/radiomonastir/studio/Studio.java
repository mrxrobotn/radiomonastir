package com.example.radiomonastir.studio;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Studio {
    private String studioNumber;
    private String studioName;

    public Studio(){
        //this constructor is required
    }

    public Studio(String studioNumber, String studioName) {
        this.studioNumber = studioNumber;
        this.studioName = studioName;
    }


    public String getStudioNumber() {
        return studioNumber;
    }

    public String getStudioName() {
        return studioName;
    }
}