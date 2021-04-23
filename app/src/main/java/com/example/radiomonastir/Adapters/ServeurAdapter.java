package com.example.radiomonastir.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.CelluleEquipementActivity;
import com.example.radiomonastir.Models.Cellule;
import com.example.radiomonastir.Models.Serveur;
import com.example.radiomonastir.R;

import java.util.List;

public class ServeurAdapter extends RecyclerView.Adapter<ServeurViewHolder> {
    private Context context;
    private List<Serveur> serveurList;
    public ServeurAdapter (Context context,List<Serveur> serveurs){
        this.context=context;
        this.serveurList=serveurs;

    }

    @NonNull
    @Override
    public ServeurViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_serveur_list,viewGroup,false);
        return new ServeurViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServeurViewHolder serveurViewHolder, final int i) {
        Serveur serveur = serveurList.get(i);
        serveurViewHolder.NomServeur.setText(serveur.getServeurName());
        serveurViewHolder.item_serveur.setOnClickListener((view) -> {
            Intent intent = new Intent(view.getContext(), CelluleEquipementActivity.class);
            intent.putExtra("id",serveur.getServeurId());
            intent.putExtra("nom",serveur.getServeurName());
            context.startActivity(intent);

        });
        serveurViewHolder.item_serveur.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context, "setOnLongClickListener", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return serveurList.size();
    }
}
