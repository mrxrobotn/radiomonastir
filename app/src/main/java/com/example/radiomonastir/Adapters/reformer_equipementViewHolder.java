package com.example.radiomonastir.Adapters;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class reformer_equipementViewHolder extends RecyclerView.ViewHolder {
    public TextView textView24;
    public TextView textView25;
    public TextView textView26;
    public ConstraintLayout item_reformer;
    public reformer_equipementViewHolder(View itemView){
        super(itemView);
        textView24=(TextView)itemView.findViewById(R.id.textView24);
        textView25=(TextView)itemView.findViewById(R.id.textView25);
        textView26=(TextView)itemView.findViewById(R.id.textView26);
        item_reformer=(ConstraintLayout) itemView.findViewById(R.id.item_reformer);

    }
}
