package com.example.radiomonastir.studio;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Equipement {
    private String id;
    private String equipementName;
    private int rating;

    public Equipement() {

    }

    public Equipement(String id, String equipementName, int rating) {
        this.equipementName = equipementName;
        this.rating = rating;
        this.id = id;
    }

    public String getEquipementName() {
        return equipementName;
    }

    public int getRating() {
        return rating;
    }
}