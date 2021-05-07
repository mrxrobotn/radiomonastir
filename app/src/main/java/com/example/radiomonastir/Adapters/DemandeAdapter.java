package com.example.radiomonastir.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.Models.Demande;
import com.example.radiomonastir.Models.Panne;
import com.example.radiomonastir.R;

import java.util.List;

public class DemandeAdapter extends RecyclerView.Adapter<DemandeViewHolder>{
    private Context context;
    private List<Demande> demandeList;

    public DemandeAdapter(Context context, List<Demande> demandes) {
        this.context = context;
        this.demandeList = demandes;
    }

    @NonNull
    @Override
    public DemandeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_demande_list, viewGroup, false);
        return new DemandeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DemandeViewHolder demandeViewHolder, final int i) {
        Demande demande = demandeList.get(i);
        demandeViewHolder.editTextTextMultiLine2.setText(demande.getDemandeDesc());

    }

    @Override
    public int getItemCount() {
        return demandeList.size();
    }
}
