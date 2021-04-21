package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class CelluleViewHolder extends RecyclerView.ViewHolder {
    public TextView itemNomCellule;
    public ConstraintLayout item_cellule;
    public CelluleViewHolder (View itemView){
        super(itemView);
        itemNomCellule=(TextView)itemView.findViewById(R.id.itemNomCellule);
        item_cellule=(ConstraintLayout)itemView.findViewById(R.id.item_cellule);
    }
}
