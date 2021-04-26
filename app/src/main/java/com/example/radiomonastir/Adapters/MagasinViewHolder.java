package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class MagasinViewHolder extends RecyclerView.ViewHolder {
    public TextView NomEquipement;
    public TextView TypeEquipement;
    public TextView NumSerie;
    public ConstraintLayout item_magasin;
    public MagasinViewHolder (View itemView){
        super(itemView);
        NomEquipement=(TextView)itemView.findViewById(R.id.NomEquipement);
        TypeEquipement=(TextView)itemView.findViewById(R.id.TypeEquipement);
        NumSerie=(TextView)itemView.findViewById(R.id.NumSerie);
        item_magasin=(ConstraintLayout)itemView.findViewById(R.id.item_magasin);
    }
}
