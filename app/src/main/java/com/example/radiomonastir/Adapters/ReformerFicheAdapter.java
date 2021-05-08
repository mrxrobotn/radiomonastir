package com.example.radiomonastir.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.Models.FicheIncident;
import com.example.radiomonastir.R;

import java.util.List;

public class ReformerFicheAdapter extends RecyclerView.Adapter<ReformerFicheViewHolder>{

    private Context context;
    private List<FicheIncident> ficheIncidentList;
    public ReformerFicheAdapter(Context context, List<FicheIncident> ficheIncident) {
        this.context = context;
        this.ficheIncidentList = ficheIncident;

    }
    @NonNull
    @Override
    public ReformerFicheViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_reformer_fiche_item, viewGroup, false);
        return new ReformerFicheViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReformerFicheViewHolder reformerFicheViewHolder, final int i) {
        FicheIncident ficheIncident = ficheIncidentList.get(i);
        reformerFicheViewHolder.editTextDate.setText(ficheIncident.getFicheDate());
        reformerFicheViewHolder.textArea_Panne.setText(ficheIncident.getFichePanne());
        reformerFicheViewHolder.textArea_Observation.setText(ficheIncident.getFicheObservation());

    }

    @Override
    public int getItemCount() {
        return ficheIncidentList.size();
    }
}
