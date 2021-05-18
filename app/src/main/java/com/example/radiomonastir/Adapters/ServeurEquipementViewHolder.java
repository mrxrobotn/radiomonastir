package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class ServeurEquipementViewHolder extends RecyclerView.ViewHolder {

    public TextView textView22,textView23;
    public ConstraintLayout cl_serverequipement;
    public ImageView imageView13;
    public ServeurEquipementViewHolder (View itemView){
        super(itemView);
        textView22=(TextView)itemView.findViewById(R.id.textView22);
        textView23=(TextView)itemView.findViewById(R.id.textView23);
        imageView13=(ImageView)itemView.findViewById(R.id.imageView13);
        cl_serverequipement=(ConstraintLayout)itemView.findViewById(R.id.cl_serverequipement);
    }

}
