package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class ReformerFicheViewHolder extends RecyclerView.ViewHolder{
    public TextView editTextDate, textArea_Panne, textArea_Observation,textView61;
    public ConstraintLayout cl_ficheincident;
    public ReformerFicheViewHolder(View itemView){
        super(itemView);
        textView61= (TextView)itemView.findViewById(R.id.textView61);
        editTextDate = (TextView) itemView.findViewById(R.id.editTextDate);
        textArea_Panne = (TextView) itemView.findViewById(R.id.textArea_Panne);
        textArea_Observation = (TextView) itemView.findViewById(R.id.textArea_Observation);
        cl_ficheincident = (ConstraintLayout)itemView.findViewById(R.id.cl_ficheincident);

    }

}
