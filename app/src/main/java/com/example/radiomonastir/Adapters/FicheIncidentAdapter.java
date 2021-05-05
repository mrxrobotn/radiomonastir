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

public class FicheIncidentAdapter extends RecyclerView.Adapter<FicheIncidentViewHolder>{

    private Context context;
    private List<FicheIncident> ficheIncidentList;
    public FicheIncidentAdapter(Context context, List<FicheIncident> ficheIncident) {
        this.context = context;
        this.ficheIncidentList = ficheIncident;

    }
    @NonNull
    @Override
    public FicheIncidentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_fiche_incident_item, viewGroup, false);
        return new FicheIncidentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FicheIncidentViewHolder ficheIncidentViewHolder, final int i) {
        FicheIncident ficheIncident = ficheIncidentList.get(i);
        ficheIncidentViewHolder.editTextDate.setText(ficheIncident.getFicheDate());
        ficheIncidentViewHolder.textArea_Panne.setText(ficheIncident.getFichePanne());
        ficheIncidentViewHolder.textArea_Observation.setText(ficheIncident.getFicheObservation());

    }

    @Override
    public int getItemCount() {
        return ficheIncidentList.size();
    }
}
