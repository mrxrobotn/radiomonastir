package com.example.radiomonastir.Models;

public class StudioEquipement {
    private String studioEquipementSN;
    private String studioEquipementName;
    private String studioEquipementType;

    public StudioEquipement(String studioEquipementSN, String studioEquipementName, String studioEquipementType) {
        this.studioEquipementSN = studioEquipementSN;
        this.studioEquipementName = studioEquipementName;
        this.studioEquipementType = studioEquipementType;
    }

    public StudioEquipement() {
    }

    public String getstudioEquipementSN() {
        return studioEquipementSN;
    }

    public void setstudioEquipementSN(String studioEquipementSN) {
        this.studioEquipementSN = studioEquipementSN;
    }

    public String getStudioEquipementName() {
        return studioEquipementName;
    }

    public void setStudioEquipementName(String studioEquipementName) {
        this.studioEquipementName = studioEquipementName;
    }

    public String getStudioEquipementType() {
        return studioEquipementType;
    }

    public void setStudioEquipementType(String studioEquipementType) {
        this.studioEquipementType = studioEquipementType;
    }
}
