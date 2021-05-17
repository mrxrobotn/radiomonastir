package com.example.radiomonastir;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class fiche_serveur_equipViewHolder  extends RecyclerView.ViewHolder {
    public TextView textView73,textView74,textView75,textView76;
    public ConstraintLayout contraintficheserveur;
    public fiche_serveur_equipViewHolder (View itemView){
        super(itemView);
        textView73 = (TextView) itemView.findViewById(R.id.textView73);
        textView74 = (TextView) itemView.findViewById(R.id.textView74);
        textView75 = (TextView) itemView.findViewById(R.id.textView75);
        textView76=(TextView)itemView.findViewById(R.id.textView76);
        contraintficheserveur = (ConstraintLayout)itemView.findViewById(R.id.contraintficheserveur);

    }
}
