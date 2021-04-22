package com.example.radiomonastir.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.CelluleEquipementActivity;
import com.example.radiomonastir.Models.Cellule;
import com.example.radiomonastir.R;

import java.util.List;

public class CelluleAdapter extends RecyclerView.Adapter<CelluleViewHolder> {
    private Context context;
    private List<Cellule> celluleList;

    public CelluleAdapter (Context context,List<Cellule> cellules){
        this.context=context;
        this.celluleList=cellules;

    }



    @NonNull
    @Override
    public CelluleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_cellule_list,viewGroup,false);
        return new CelluleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CelluleViewHolder celluleViewHolder, final int i) {
        Cellule cellule = celluleList.get(i);
        celluleViewHolder.itemNomCellule.setText(cellule.getCelluleId());
        celluleViewHolder.item_cellule.setOnClickListener((view) -> {
            Intent intent = new Intent(view.getContext(), CelluleEquipementActivity.class);
            intent.putExtra("id",cellule.getCelluleId());
            intent.putExtra("nom",cellule.getCelluleName());
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return celluleList.size();
    }
}