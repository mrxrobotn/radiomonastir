package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class FicheIncidentViewHolder extends RecyclerView.ViewHolder{
    public TextView editTextDate, textArea_Panne, textArea_Observation;
    public ConstraintLayout cl_ficheincident;
    public FicheIncidentViewHolder (View itemView){
        super(itemView);
        editTextDate = (EditText)itemView.findViewById(R.id.editTextDate);
        textArea_Panne = (EditText)itemView.findViewById(R.id.textArea_Panne);
        textArea_Observation = (EditText)itemView.findViewById(R.id.textArea_Observation);
        cl_ficheincident = (ConstraintLayout)itemView.findViewById(R.id.cl_ficheincident);

    }

}
