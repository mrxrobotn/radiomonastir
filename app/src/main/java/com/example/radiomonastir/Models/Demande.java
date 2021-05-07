package com.example.radiomonastir.Models;

public class Demande {
    private String demandeId;
    private String demandeDesc;

    public Demande(String demandeId, String demandeDesc) {
        this.demandeId = demandeId;
        this.demandeDesc = demandeDesc;
    }

    public Demande() {
    }

    public String getDemandeId() {
        return demandeId;
    }

    public void setDemandeId(String demandeId) {
        this.demandeId = demandeId;
    }

    public String getDemandeDesc() {
        return demandeDesc;
    }

    public void setDemandeDesc(String demandeDesc) {
        this.demandeDesc = demandeDesc;
    }
}
