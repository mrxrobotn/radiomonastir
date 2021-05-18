package com.example.radiomonastir.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.Models.FicheIncident;
import com.example.radiomonastir.R;
import com.example.radiomonastir.fiche_serveur_equipViewHolder;

import java.util.List;

public class fiche_serveur_equipAdapter  extends RecyclerView.Adapter<fiche_serveur_equipViewHolder> {
    private Context context;
    private List<FicheIncident> ficheServeurList;
    public fiche_serveur_equipAdapter(Context context, List<FicheIncident> ficheServeur) {
        this.context = context;
        this.ficheServeurList = ficheServeur;

    }
    @NonNull
    @Override
    public fiche_serveur_equipViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_serveur_fiche_item, viewGroup, false);
        return new fiche_serveur_equipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull fiche_serveur_equipViewHolder fiche_serveur_equipViewHolder, final int i) {
        FicheIncident ficheServeur = ficheServeurList.get(i);
        fiche_serveur_equipViewHolder.editTextTextPersonName17.setText(ficheServeur.getFichenomtech());
        fiche_serveur_equipViewHolder.editTextDate4.setText(ficheServeur.getFicheDate());
        fiche_serveur_equipViewHolder.editTextTextMultiLine3.setText(ficheServeur.getFichePanne());
        fiche_serveur_equipViewHolder.editTextTextMultiLine4.setText(ficheServeur.getFicheObservation());


    }

    @Override
    public int getItemCount() {
        return ficheServeurList.size();
    }
}

