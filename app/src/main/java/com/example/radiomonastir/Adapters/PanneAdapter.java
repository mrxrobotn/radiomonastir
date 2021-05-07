package com.example.radiomonastir.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.Models.Panne;
import com.example.radiomonastir.R;

import java.util.List;

public class PanneAdapter extends RecyclerView.Adapter<PanneViewHolder>{
    private Context context;
    private List<Panne> panneList;

    public PanneAdapter(Context context, List<Panne> pannes) {
        this.context = context;
        this.panneList = pannes;
    }

    @NonNull
    @Override
    public PanneViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_panne_list, viewGroup, false);
        return new PanneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PanneViewHolder panneViewHolder, final int i) {
        Panne panne = panneList.get(i);
        panneViewHolder.editTextTextMultiLine.setText(panne.getPanneDesc());

    }

    @Override
    public int getItemCount() {
        return panneList.size();
    }
}
