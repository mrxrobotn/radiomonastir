package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class PanneViewHolder extends RecyclerView.ViewHolder{
    TextView editTextTextMultiLine;
    ConstraintLayout cl_panne;

    public PanneViewHolder(View itemView) {
        super(itemView);
        editTextTextMultiLine = (TextView)itemView.findViewById(R.id.editTextTextMultiLine);
        cl_panne = (ConstraintLayout)itemView.findViewById(R.id.cl_panne);
    }
}