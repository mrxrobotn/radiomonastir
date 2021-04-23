package com.example.radiomonastir.Models;

public class Serveur {
    private String serveurId;
    private String serveurName;

    public Serveur(String serveurId, String serveurName) {
        this.serveurId = serveurId;
        this.serveurName = serveurName;
    }

    public Serveur() {
    }

    public String getServeurId() {
        return serveurId;
    }

    public void setServeurId(String serveurId) {
        this.serveurId = serveurId;
    }

    public String getServeurName() {
        return serveurName;
    }

    public void setServeurName(String serveurName) {
        this.serveurName = serveurName;
    }
}
