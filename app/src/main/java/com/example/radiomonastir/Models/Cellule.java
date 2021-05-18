package com.example.radiomonastir.Models;

public class Cellule {
    private String celluleId;
    private String celluleName;

    public Cellule() {
    }

    public Cellule(String celluleId, String celluleName) {
        this.celluleId = celluleId;
        this.celluleName = celluleName;
    }

    public String getCelluleId() {
        return celluleId;
    }

    public void setCelluleId(String celluleId) {
        this.celluleId = celluleId;
    }

    public String getCelluleName() {
        return celluleName;
    }

    public void setCelluleName(String celluleName) {
        this.celluleName = celluleName;
    }
    @Override
    public String toString() {
        return celluleName;
    }
}
