package com.example.radiomonastir.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.CelluleEquipementActivity;
import com.example.radiomonastir.Models.FicheIncident;
import com.example.radiomonastir.R;

import java.util.List;

public class fiche_cellule_equipAdapter  extends RecyclerView.Adapter<fiche_cellule_equipViewHolder> {

    private Context context;
    private List<FicheIncident> ficheCellList;
    public fiche_cellule_equipAdapter(Context context, List<FicheIncident> ficheCell) {
        this.context = context;
        this.ficheCellList = ficheCell;

    }
    @NonNull
    @Override
    public fiche_cellule_equipViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_fiche_incident_cellule, viewGroup, false);
        return new fiche_cellule_equipViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull fiche_cellule_equipViewHolder fiche_cellule_equipViewHolder, final int i) {
        FicheIncident ficheCell = ficheCellList.get(i);
        fiche_cellule_equipViewHolder.textView42.setText(ficheCell.getFicheDate());
        fiche_cellule_equipViewHolder.textView43.setText(ficheCell.getFichePanne());
      fiche_cellule_equipViewHolder.textView44.setText(ficheCell.getFicheObservation());
        fiche_cellule_equipViewHolder.constraintfichecellule.setOnClickListener((view) -> {
            Intent intent = new Intent(view.getContext(), CelluleEquipementActivity.class);
            intent.putExtra("id",ficheCell.getFicheId());
            intent.putExtra("date",ficheCell.getFicheDate());
            intent.putExtra("panne",ficheCell.getFichePanne());
            intent.putExtra("observation",ficheCell.getFicheObservation());
            context.startActivity(intent);

        });

    }
    @Override
    public int getItemCount() {
        return ficheCellList.size();
    }

}
