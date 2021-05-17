package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class fiche_cellule_equipViewHolder extends RecyclerView.ViewHolder {
    public TextView editTextTextPersonName10, editTextDate2, editTextTextPersonName11,editTextTextPersonName12;
    public ConstraintLayout constraintfichecellule;
    public fiche_cellule_equipViewHolder (View itemView){
        super(itemView);
        editTextTextPersonName10 = (TextView) itemView.findViewById(R.id.editTextTextPersonName10);
        editTextDate2 = (TextView) itemView.findViewById(R.id.editTextDate2);
        editTextTextPersonName11 = (TextView) itemView.findViewById(R.id.editTextTextPersonName11);
        editTextTextPersonName12=(TextView)itemView.findViewById(R.id.editTextTextPersonName12);
        constraintfichecellule = (ConstraintLayout)itemView.findViewById(R.id.constraintfichecellule);

    }
}
