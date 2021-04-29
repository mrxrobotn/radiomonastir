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

import com.example.radiomonastir.FicheIncidentActivity;
import com.example.radiomonastir.Models.Magasin;
import com.example.radiomonastir.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MagasinAdapter extends RecyclerView.Adapter<MagasinViewHolder> {

    private Context context;
    private List<Magasin> magasinEquipementList;

    public MagasinAdapter(Context context, List<Magasin> magasins) {
        this.context = context;
        this.magasinEquipementList = magasins;

    }


    @NonNull
    @Override
    public MagasinViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_magasin_list, viewGroup, false);
        return new MagasinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MagasinViewHolder magasinViewHolder, final int i) {
        Magasin magasin = magasinEquipementList.get(i);
        magasinViewHolder.NomEquipement.setText(magasin.getMagasinNom());
        magasinViewHolder.TypeEquipement.setText(magasin.getMagasinType());
        magasinViewHolder.NumSerie.setText(magasin.getMagasinNumSerie());
        magasinViewHolder.item_magasin.setOnClickListener((view) -> {
            Intent intent = new Intent(view.getContext(), FicheIncidentActivity.class);
            intent.putExtra("id", magasin.getMagasinId());
            intent.putExtra("nom", magasin.getMagasinNom());
            intent.putExtra("type", magasin.getMagasinType());
            intent.putExtra("numserie", magasin.getMagasinNumSerie());
            context.startActivity(intent);

        });

        magasinViewHolder.item_magasin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showUpdateDeleteDialog(magasin.getMagasinId(),magasin.getMagasinNom(),magasin.getMagasinType(),magasin.getMagasinNumSerie());
                return false;
            }
        });
    }

    private void showUpdateDeleteDialog(String magasinId, String magasinNom, String magasinType, String magasinNumSerie) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.magasin_dialogue, null);
        dialogBuilder.setView(dialogView);

        final EditText NomEquipement = (EditText) dialogView.findViewById(R.id.NomEquipement);
        final EditText TypeEquipement = (EditText) dialogView.findViewById(R.id.TypeEquipement);
        final EditText NumSerie = (EditText) dialogView.findViewById(R.id.NumSerie);
        final Button buttonUpdateMagasin = (Button) dialogView.findViewById(R.id.buttonUpdateMagasin);
        final Button buttonDeleteMagasin = (Button) dialogView.findViewById(R.id.buttonDeleteMagasin);

        dialogBuilder.setTitle(magasinNom);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdateMagasin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = NomEquipement.getText().toString().trim();
                String type = TypeEquipement.getText().toString().trim();
                String num_serie = NumSerie.getText().toString().trim();
                if (!TextUtils.isEmpty(nom) && !TextUtils.isEmpty(type) && !TextUtils.isEmpty(num_serie)) {
                    updateMagasin(magasinId,nom,type,num_serie);
                    b.dismiss();
                }
            }
        });
        buttonDeleteMagasin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteMagasin(magasinId);
                b.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        return magasinEquipementList.size();
    }

    private boolean deleteMagasin(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("magasin").child(id);

        dR.removeValue();
        Toast.makeText(context, "Equipement supprimé avec succée", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean updateMagasin(String id, String nom, String type, String numserie) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("magasin").child(id);

        Magasin magasin = new Magasin(id, nom, type,numserie);
        dR.setValue(magasin);
        Toast.makeText(context, "Equipement modifié avec succée", Toast.LENGTH_LONG).show();
        return true;
    }

}