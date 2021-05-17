package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class fiche_studio_equipViewHolder extends RecyclerView.ViewHolder {
    public TextView textView56, textView57, textView58,textView59;
    public ConstraintLayout contraintstudiofiche;
    public fiche_studio_equipViewHolder (View itemView){
        super(itemView);
        textView56 = (TextView) itemView.findViewById(R.id.textView56);
       textView57 = (TextView) itemView.findViewById(R.id.textView57);
       textView58 = (TextView) itemView.findViewById(R.id.textView58);
       textView59 =(TextView) itemView.findViewById(R.id.textView59);
        contraintstudiofiche = (ConstraintLayout)itemView.findViewById(R.id.contraintstudiofiche);

    }
}
