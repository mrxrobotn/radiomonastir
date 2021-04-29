package com.example.radiomonastir.Models;

public class equipement_cellule {
    private String equipepment_celluleId;
    private String equipement_cellulenom;
    private String equipement_celluletype;
    private String equipement_cellulenumserie;


    public equipement_cellule(String equipepment_celluleId, String equipement_cellulenom, String equipement_celluletype, String equipement_cellulenumserie) {
        this.equipepment_celluleId = equipepment_celluleId;
        this.equipement_cellulenom = equipement_cellulenom;
        this.equipement_celluletype = equipement_celluletype;
        this.equipement_cellulenumserie = equipement_cellulenumserie;
    }

    public equipement_cellule() {
    }

    public String getEquipepment_celluleId() {
        return equipepment_celluleId;
    }

    public void setEquipepment_celluleId(String equipepment_celluleId) {
        this.equipepment_celluleId = equipepment_celluleId;
    }

    public String getEquipement_cellulenom() {
        return equipement_cellulenom;
    }

    public void setEquipement_cellulenom(String equipement_cellulenom) {
        this.equipement_cellulenom = equipement_cellulenom;
    }

    public String getEquipement_celluletype() {
        return equipement_celluletype;
    }

    public void setEquipement_celluletype(String equipement_celluletype) {
        this.equipement_celluletype = equipement_celluletype;
    }

    public String getEquipement_cellulenumserie() {
        return equipement_cellulenumserie;
    }

    public void setEquipement_cellulenumserie(String equipement_cellulenumserie) {
        this.equipement_cellulenumserie = equipement_cellulenumserie;
    }
}
