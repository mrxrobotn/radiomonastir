package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class fiche_cellule_equipViewHolder extends RecyclerView.ViewHolder {
    public TextView textView42, textView43, textView44,textView39;
    public ConstraintLayout constraintfichecellule;
    public fiche_cellule_equipViewHolder (View itemView){
        super(itemView);
        textView42 = (TextView) itemView.findViewById(R.id.textView42);
        textView43 = (TextView) itemView.findViewById(R.id.textView43);
        textView44 = (TextView) itemView.findViewById(R.id.textView44);
        textView39=(TextView)itemView.findViewById(R.id.textView39);
        constraintfichecellule = (ConstraintLayout)itemView.findViewById(R.id.constraintfichecellule);

    }
}
