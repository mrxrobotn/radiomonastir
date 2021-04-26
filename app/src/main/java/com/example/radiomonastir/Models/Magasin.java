package com.example.radiomonastir.Models;

public class Magasin {
    private String magasinId;
    private String magasinNom;
    private String magasinType;
    private String magasinNumSerie;

    public Magasin(String magasinId, String magasinNom, String magasinType, String magasinNumSerie) {
        this.magasinId = magasinId;
        this.magasinNom = magasinNom;
        this.magasinType = magasinType;
        this.magasinNumSerie = magasinNumSerie;
    }

    public Magasin() {
    }

    public String getMagasinId() {
        return magasinId;
    }

    public void setMagasinId(String magasinId) {
        this.magasinId = magasinId;
    }

    public String getMagasinNom() {
        return magasinNom;
    }

    public void setMagasinNom(String magasinNom) {
        this.magasinNom = magasinNom;
    }

    public String getMagasinType() {
        return magasinType;
    }

    public void setMagasinType(String magasinType) {
        this.magasinType = magasinType;
    }

    public String getMagasinNumSerie() {
        return magasinNumSerie;
    }

    public void setMagasinNumSerie(String magasinNumSerie) {
        this.magasinNumSerie = magasinNumSerie;
    }
}
