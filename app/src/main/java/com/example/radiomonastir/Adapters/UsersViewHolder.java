package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class UsersViewHolder extends RecyclerView.ViewHolder {
    public TextView editTextTextPersonName, editTextTextEmailAddress2;
    public ConstraintLayout cl_gestiontechnicien;

    public UsersViewHolder(View itemView) {
        super(itemView);
        editTextTextPersonName = (TextView) itemView.findViewById(R.id.editTextTextPersonName);
        editTextTextEmailAddress2 = (TextView) itemView.findViewById(R.id.editTextTextEmailAddress2);
        cl_gestiontechnicien = (ConstraintLayout) itemView.findViewById(R.id.cl_gestiontechnicien);
    }
}