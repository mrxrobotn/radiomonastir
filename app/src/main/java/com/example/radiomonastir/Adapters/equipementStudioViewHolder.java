package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class equipementStudioViewHolder extends RecyclerView.ViewHolder {
    public TextView StudioEquipementName;
    public TextView StudioEquipementType;
    public TextView StudioEquipementNS;
    public ConstraintLayout constraint_StudioEquipement;
    public equipementStudioViewHolder(View itemView){
        super(itemView);
        StudioEquipementName=(TextView)itemView.findViewById(R.id.StudioEquipementName);
        StudioEquipementType=(TextView)itemView.findViewById(R.id.StudioEquipementType);
        StudioEquipementNS=(TextView)itemView.findViewById(R.id.StudioEquipementNS);
        constraint_StudioEquipement=(ConstraintLayout)itemView.findViewById(R.id.constraint_StudioEquipement);

    }


}

