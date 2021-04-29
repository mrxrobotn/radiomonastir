package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class ServeurEquipementViewHolder extends RecyclerView.ViewHolder {
    public TextView textView22,textView23;
    public ConstraintLayout cl_serverequipement;
    public ServeurEquipementViewHolder (View itemView){
        super(itemView);
        textView22=(TextView)itemView.findViewById(R.id.textView22);
        textView23=(TextView)itemView.findViewById(R.id.textView23);
        cl_serverequipement=(ConstraintLayout)itemView.findViewById(R.id.cl_serverequipement);
    }

}
