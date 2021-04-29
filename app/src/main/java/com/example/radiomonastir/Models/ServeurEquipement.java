package com.example.radiomonastir.Models;

public class ServeurEquipement {
    private String servEquipementId;
    private String servEquipementType;
    private String servEquipementSN;

    public ServeurEquipement() {
    }


    public ServeurEquipement(String servEquipementId, String servEquipementType, String servEquipementSN) {
        this.servEquipementId = servEquipementId;
        this.servEquipementType = servEquipementType;
        this.servEquipementSN = servEquipementSN;
    }


    public String getServEquipementType() {
        return servEquipementType;
    }

    public void setServEquipementType(String servEquipementType) {
        this.servEquipementType = servEquipementType;
    }

    public String getServEquipementSN() {
        return servEquipementSN;
    }

    public void setServEquipementSN(String servEquipementSN) {
        this.servEquipementSN = servEquipementSN;
    }

    public String getServEquipementId() {
        return servEquipementId;
    }

    public void setServEquipementId(String servEquipementId) {
        this.servEquipementId = servEquipementId;
    }
}
