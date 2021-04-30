package com.example.radiomonastir.Adapters;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.CelluleEquipementActivity;
import com.example.radiomonastir.Models.Cellule;
import com.example.radiomonastir.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        celluleViewHolder.itemNomCellule.setText(cellule.getCelluleName());
        celluleViewHolder.item_cellule.setOnClickListener((view) -> {
            Intent intent = new Intent(context, CelluleEquipementActivity.class);
            intent.putExtra("id",cellule.getCelluleId());
            intent.putExtra("nom",cellule.getCelluleName());
            context.startActivity(intent);

        });
        celluleViewHolder.item_cellule.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showUpdateDeleteDialog(cellule.getCelluleId(),cellule.getCelluleName());
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return celluleList.size();
    }

    private void showUpdateDeleteDialog(String celluleId, String celluleName) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.cellule_dialogue, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextTextPersonName = (EditText) dialogView.findViewById(R.id.editTextTextPersonName);
        final Button buttonUpdateCellule = (Button) dialogView.findViewById(R.id.buttonUpdateCellule);
        final Button buttonDeleteCellule = (Button) dialogView.findViewById(R.id.buttonDeleteCellule);
        editTextTextPersonName.setText(celluleName);
        dialogBuilder.setTitle(celluleName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdateCellule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextTextPersonName.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    updateCellule(celluleId,name);
                    b.dismiss();
                }
            }
        });
        buttonDeleteCellule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteCellule(celluleId);
                b.dismiss();
            }
        });
    }

    private boolean deleteCellule(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("cellules").child(id);

        dR.removeValue();
        Toast.makeText(context, "Cellule supprimé avec succée", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean updateCellule(String id, String name) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("cellules").child(id);

        Cellule cellule = new Cellule(id, name);
        dR.setValue(cellule);
        Toast.makeText(context, "Cellule modifié avec succée", Toast.LENGTH_LONG).show();
        return true;
    }


}