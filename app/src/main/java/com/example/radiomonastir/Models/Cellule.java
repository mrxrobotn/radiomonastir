package com.example.radiomonastir.Models;

public class Cellule {
    private String id;
    private String nomCellule;


    public Cellule(String id, String nomCellule) {
        this.nomCellule = nomCellule;
        this.id = id;
    }

    public Cellule() {
    }

    public String getNomCellule() {
        return nomCellule;
    }

    public void setNomCellule(String nomCellule) {
        this.nomCellule = nomCellule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
