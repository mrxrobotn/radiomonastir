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

import com.example.radiomonastir.Models.Equipement;
import com.example.radiomonastir.R;
import com.example.radiomonastir.equipementStudioActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class equipementStudioadapter  extends RecyclerView.Adapter<equipementStudioViewHolder> {
    private Context context;
    private List<Equipement> studio_equipementlist;
    private String idstudio;

    public equipementStudioadapter(Context context, List<Equipement> studios, String idstudio) {
        this.context = context;
        this.studio_equipementlist = studios;
        this.idstudio = idstudio;


    }

    @NonNull
    @Override
    public equipementStudioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_studio_equipement_list, viewGroup, false);
        return new equipementStudioViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull equipementStudioViewHolder equipementStudioViewHolder, final int i) {
        Equipement studio = studio_equipementlist.get(i);
        equipementStudioViewHolder.StudioEquipementName.setText(studio.getEquipementNnom());
        equipementStudioViewHolder.StudioEquipementType.setText(studio.getEquipementTtype());
        equipementStudioViewHolder.StudioEquipementNS.setText(studio.getEquipementNumserie());
        equipementStudioViewHolder.constraint_StudioEquipement.setOnClickListener((view) -> {
            Intent intent = new Intent(view.getContext(), equipementStudioActivity.class);
            intent.putExtra("id", studio.getEquipepmentId());
            intent.putExtra("nom", studio.getEquipementNnom());
            intent.putExtra("type", studio.getEquipementTtype());
            intent.putExtra("numserie", studio.getEquipementNumserie());
            context.startActivity(intent);

        });
        equipementStudioViewHolder.constraint_StudioEquipement.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showUpdateDeleteDialog(studio.getEquipepmentId(), studio.getEquipementNnom(), studio.getEquipementTtype(), studio.getEquipementNumserie());
                return false;
            }
        });
        equipementStudioViewHolder.imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envoyer();
            }
        });

    }

    private void envoyer() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.activity_envoyer, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();
        b.show();
    }

    @Override
    public int getItemCount() {
        return studio_equipementlist.size();
    }

    private void showUpdateDeleteDialog(String equipepment_studioId, String equipepment_studionom, String equipepment_studiotype, String equipepment_studionumserie) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.studio_equipement_dialogue, null);
        dialogBuilder.setView(dialogView);

        final EditText nomequipementstudio = (EditText) dialogView.findViewById(R.id.nomequip_studio);
        final EditText typeequipementstudio = (EditText) dialogView.findViewById(R.id.typeequip_studio);
        final EditText serieequipementstudio = (EditText) dialogView.findViewById(R.id.NSequip_studio);
        final Button modifierequipementstudio = (Button) dialogView.findViewById(R.id.butt_modifier_equip_studio);
        final Button supprimerequipementstudio = (Button) dialogView.findViewById(R.id.butt_supprimer_equip_studio);
        nomequipementstudio.setText(equipepment_studionom);
        typeequipementstudio.setText(equipepment_studiotype);
        serieequipementstudio.setText(equipepment_studionumserie);
        dialogBuilder.setTitle(equipepment_studionom);
        final AlertDialog b = dialogBuilder.create();
        b.show();
        modifierequipementstudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = nomequipementstudio.getText().toString().trim();
                String type = typeequipementstudio.getText().toString().trim();
                String numserie = serieequipementstudio.getText().toString().trim();
                if (!TextUtils.isEmpty(nom)) {
                    modifierequipementstudio(idstudio, equipepment_studioId, nom, type, numserie);
                    b.dismiss();
                }
            }
        });
        supprimerequipementstudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                supprimerequipementstudio(idstudio, equipepment_studioId);
                b.dismiss();
            }
        });

    }

    private boolean supprimerequipementstudio(String idstudio, String idEquip) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("equipements").child(idEquip);

        dR.removeValue();
        Toast.makeText(context, "studio equipement supprimé avec succée", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean modifierequipementstudio(String idstudio, String idequip, String nom, String type, String numserie) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("equipements").child(idequip);

        Equipement studios = new Equipement(idequip, nom, type, numserie, "studios", idstudio);
        dR.setValue(studios);
        Toast.makeText(context, "studio equipement modifié avec succée", Toast.LENGTH_LONG).show();
        return true;
    }
}