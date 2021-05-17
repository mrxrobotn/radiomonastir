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

public class fiche_studio_equipAdapter extends RecyclerView.Adapter<fiche_studio_equipViewHolder>{
    private Context context;
    private List<FicheIncident> ficheStudioList;
    public fiche_studio_equipAdapter(Context context, List<FicheIncident> ficheStudio) {
        this.context = context;
        this.ficheStudioList = ficheStudio;

    }
    @NonNull
    @Override
    public fiche_studio_equipViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_studio_fiche_list, viewGroup, false);
        return new fiche_studio_equipViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull fiche_studio_equipViewHolder fiche_studio_equipViewHolder, final int i) {
        FicheIncident fichestudio= ficheStudioList.get(i);
        fiche_studio_equipViewHolder.editTextTextPersonName13.setText(fichestudio.getFichenomtech());
        fiche_studio_equipViewHolder.editTextDate3.setText(fichestudio.getFicheDate());
        fiche_studio_equipViewHolder.editTextTextPersonName14.setText(fichestudio.getFichePanne());
        fiche_studio_equipViewHolder.editTextTextPersonName15.setText(fichestudio.getFicheObservation());



    }
    @Override
    public int getItemCount() {
        return ficheStudioList.size();
    }

}
