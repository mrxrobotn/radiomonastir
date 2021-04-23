package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class ServeurViewHolder extends RecyclerView.ViewHolder {
    public TextView NomServeur;
    public ConstraintLayout item_serveur;
    public ServeurViewHolder (View itemView){
        super(itemView);
        NomServeur=(TextView)itemView.findViewById(R.id.NomServeur);
        item_serveur=(ConstraintLayout)itemView.findViewById(R.id.item_Serveur);
    }
}
