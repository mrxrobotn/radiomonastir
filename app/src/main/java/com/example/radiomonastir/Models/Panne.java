package com.example.radiomonastir.Models;

public class Panne {
    private String panneId;
    private String panneDesc;

    public Panne(String panneId, String panneDesc) {
        this.panneId = panneId;
        this.panneDesc = panneDesc;
    }

    public Panne() {
    }

    public String getPanneId() {
        return panneId;
    }

    public void setPanneId(String panneId) {
        this.panneId = panneId;
    }

    public String getPanneDesc() {
        return panneDesc;
    }

    public void setPanneDesc(String panneDesc) {
        this.panneDesc = panneDesc;
    }
}
