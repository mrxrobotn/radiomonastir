package com.example.radiomonastir.Models;

public class Equipement {
    private String equipepmentId;
    private String equipementNnom;
    private String equipementTtype;
    private String equipementNumserie;
    private String equipementPlace;
    private String equipementParentId;

    public Equipement(String equipepmentId, String equipementNnom, String equipementTtype, String equipementNumserie, String equipementPlace, String equipementParentId) {
        this.equipepmentId = equipepmentId;
        this.equipementNnom = equipementNnom;
        this.equipementTtype = equipementTtype;
        this.equipementNumserie = equipementNumserie;
        this.equipementPlace = equipementPlace;
        this.equipementParentId = equipementParentId;
    }

    public Equipement(String equipementPlace, String equipementParentId) {
        this.equipementPlace = equipementPlace;
        this.equipementParentId = equipementParentId;
    }

    public Equipement(String equipepmentId, String equipementNnom, String equipementTtype, String equipementNumserie, String equipementPlace) {
        this.equipepmentId = equipepmentId;
        this.equipementNnom = equipementNnom;
        this.equipementTtype = equipementTtype;
        this.equipementNumserie = equipementNumserie;
        this.equipementPlace = equipementPlace;
    }

    public Equipement() {
    }

    public String getEquipepmentId() {
        return equipepmentId;
    }

    public void setEquipepmentId(String equipepmentId) {
        this.equipepmentId = equipepmentId;
    }

    public String getEquipementNnom() {
        return equipementNnom;
    }

    public void setEquipementNnom(String equipementNnom) {
        this.equipementNnom = equipementNnom;
    }

    public String getEquipementTtype() {
        return equipementTtype;
    }

    public void setEquipementTtype(String equipementTtype) {
        this.equipementTtype = equipementTtype;
    }

    public String getEquipementNumserie() {
        return equipementNumserie;
    }

    public void setEquipementNumserie(String equipementNumserie) {
        this.equipementNumserie = equipementNumserie;
    }

    public String getEquipementPlace() {
        return equipementPlace;
    }

    public void setEquipementPlace(String equipementPlace) {
        this.equipementPlace = equipementPlace;
    }

    public String getEquipementParentId() {
        return equipementParentId;
    }

    public void setEquipementParentId(String equipementParentId) {
        this.equipementParentId = equipementParentId;
    }
}
