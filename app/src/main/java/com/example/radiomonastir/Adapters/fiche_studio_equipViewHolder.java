package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class fiche_studio_equipViewHolder extends RecyclerView.ViewHolder {
    public TextView editTextTextPersonName13, editTextDate3, editTextTextPersonName14, editTextTextPersonName15;
    public ConstraintLayout contraintstudiofiche;
    public fiche_studio_equipViewHolder (View itemView){
        super(itemView);
        editTextTextPersonName13 = (TextView) itemView.findViewById(R.id.editTextTextPersonName13);
        editTextDate3 = (TextView) itemView.findViewById(R.id.editTextDate3);
        editTextTextPersonName14 = (TextView) itemView.findViewById(R.id.editTextTextPersonName14);
        editTextTextPersonName15 =(TextView) itemView.findViewById(R.id.editTextTextPersonName15);
        contraintstudiofiche = (ConstraintLayout)itemView.findViewById(R.id.contraintstudiofiche);

    }
}
