package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class StudioViewHolder extends RecyclerView.ViewHolder {
    public TextView textViewNumber;
    public TextView textViewName;
    public ConstraintLayout listeitem;
    public StudioViewHolder (View itemView) {
        super(itemView);
        textViewNumber = (TextView) itemView.findViewById(R.id.textViewNumber);
        textViewName = (TextView) itemView.findViewById(R.id.textViewName);
        listeitem = (ConstraintLayout) itemView.findViewById(R.id.listeitem);
    }

}
