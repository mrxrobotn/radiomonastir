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
import com.example.radiomonastir.Models.Equipement;
import com.example.radiomonastir.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class reformer_equipementAdapter extends RecyclerView.Adapter<reformer_equipementViewHolder> {
    private Context context;
    private List<Equipement> eqiupement_reformerlist;
    public reformer_equipementAdapter(Context context, List<Equipement> reformer) {
        this.context = context;
        this.eqiupement_reformerlist = reformer;

    }
    @NonNull
    @Override
    public reformer_equipementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_reformer_equipement_list, viewGroup, false);
        return new reformer_equipementViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull reformer_equipementViewHolder reformer_equipementViewHolder, final int i) {
        Equipement reformer = eqiupement_reformerlist.get(i);
        reformer_equipementViewHolder.textView24.setText(reformer.getEquipementNnom());
        reformer_equipementViewHolder.textView25.setText(reformer.getEquipementTtype());
        reformer_equipementViewHolder.textView26.setText(reformer.getEquipementNumserie());
        reformer_equipementViewHolder.item_reformer.setOnClickListener((view) -> {
            Intent intent = new Intent(view.getContext(), FicheIncidentActivity.class);
            intent.putExtra("id", reformer.getEquipepmentId());
            intent.putExtra("nom", reformer.getEquipementNnom());
            intent.putExtra("type", reformer.getEquipementTtype());
            intent.putExtra("numserie", reformer.getEquipementNumserie());
            context.startActivity(intent);

        });
        reformer_equipementViewHolder.item_reformer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showUpdateDeleteDialog(reformer.getEquipepmentId(),reformer.getEquipementNnom(),reformer.getEquipementTtype(),reformer.getEquipementNumserie());
                return false;
            }
        });



}
    private void showUpdateDeleteDialog(String reformerId, String reformernom, String reformertype, String reformerNumSerie) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.pop_upequipement_reformer, null);
        dialogBuilder.setView(dialogView);

        final EditText NomEquipement = (EditText) dialogView.findViewById(R.id.editTextTextPersonName4);
        final EditText TypeEquipement = (EditText) dialogView.findViewById(R.id.editTextTextPersonName5);
        final EditText NumSerie = (EditText) dialogView.findViewById(R.id.editTextTextPersonName6);
        final Button buttonUpdatereformer = (Button) dialogView.findViewById(R.id.button8);
        final Button buttonDeletereformer = (Button) dialogView.findViewById(R.id.button9);

        NomEquipement.setText(reformernom);
        TypeEquipement.setText(reformertype);
        NumSerie.setText(reformerNumSerie);
        dialogBuilder.setTitle(reformernom);
        final AlertDialog b = dialogBuilder.create();
        b.show();
        buttonUpdatereformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = NomEquipement.getText().toString().trim();
                String type = TypeEquipement.getText().toString().trim();
                String num_serie = NumSerie.getText().toString().trim();
                if (!TextUtils.isEmpty(nom) && !TextUtils.isEmpty(type) && !TextUtils.isEmpty(num_serie)) {
                    buttonUpdatereformer(reformerId,nom,type,num_serie);
                    b.dismiss();
                }
            }
        });
        buttonDeletereformer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buttonDeletereformer(reformerId);
                b.dismiss();
            }
        });
    }
    @Override
    public int getItemCount() {
        return eqiupement_reformerlist.size();
    }

    private boolean buttonDeletereformer(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("equipements").child(id);

        dR.removeValue();
        Toast.makeText(context, "Equipement supprimé avec succée", Toast.LENGTH_LONG).show();
        return true;
    }
    private boolean buttonUpdatereformer(String id, String nom, String type, String numserie) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("equipements").child(id);

        Equipement reformer = new Equipement(id, nom, type,numserie,"reformer");
        dR.setValue(reformer);
        Toast.makeText(context, "Equipement modifié avec succée", Toast.LENGTH_LONG).show();
        return true;
    }

}


