package com.example.radiomonastir;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class fiche_serveur_equipViewHolder  extends RecyclerView.ViewHolder {
    public TextView editTextTextPersonName17, editTextDate4, editTextTextMultiLine3, editTextTextMultiLine4;
    public ConstraintLayout contraintficheserveur;
    public fiche_serveur_equipViewHolder (View itemView){
        super(itemView);
        editTextTextPersonName17 = (TextView) itemView.findViewById(R.id.editTextTextPersonName17);
        editTextDate4 = (TextView) itemView.findViewById(R.id.editTextDate4);
        editTextTextMultiLine3 = (TextView) itemView.findViewById(R.id.editTextTextMultiLine3);
        editTextTextMultiLine4=(TextView)itemView.findViewById(R.id.editTextTextMultiLine4);
        contraintficheserveur = (ConstraintLayout)itemView.findViewById(R.id.contraintficheserveur);

    }
}
