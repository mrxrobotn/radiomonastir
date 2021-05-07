package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class DemandeViewHolder extends RecyclerView.ViewHolder {
    TextView editTextTextMultiLine2;
    ConstraintLayout cl_demande;
    public DemandeViewHolder(View itemView) {
        super(itemView);
        editTextTextMultiLine2 = (TextView)itemView.findViewById(R.id.editTextTextMultiLine2);
        cl_demande = (ConstraintLayout)itemView.findViewById(R.id.cl_demande);
    }

}
