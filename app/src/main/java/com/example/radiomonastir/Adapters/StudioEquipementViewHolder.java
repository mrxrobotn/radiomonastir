package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class StudioEquipementViewHolder extends RecyclerView.ViewHolder {
    public TextView StudioEquipementName,StudioEquipementType,StudioEquipementSN;
    public ConstraintLayout cl_StudioEquipement;
    public StudioEquipementViewHolder (View itemView){
        super(itemView);
        StudioEquipementName=(TextView)itemView.findViewById(R.id.StudioEquipementName);
        StudioEquipementSN=(TextView)itemView.findViewById(R.id.StudioEquipementSN);
        StudioEquipementType=(TextView)itemView.findViewById(R.id.StudioEquipementType);
        cl_StudioEquipement=(ConstraintLayout)itemView.findViewById(R.id.cl_StudioEquipement);
    }

}
