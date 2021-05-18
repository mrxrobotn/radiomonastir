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

import com.example.radiomonastir.ActivityEnvoyer;
import com.example.radiomonastir.Models.Equipement;
import com.example.radiomonastir.Models.Serveur;
import com.example.radiomonastir.R;
import com.example.radiomonastir.ServeurFicheActivity;
import com.example.radiomonastir.StudioFicheActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ServeurEquipementAdapter extends RecyclerView.Adapter<ServeurEquipementViewHolder> {

    private Context context;
    private List<Equipement> serv_equip_list;
    private String idserveur;

    public ServeurEquipementAdapter(Context context, List<Equipement> serv_equip, String idserveur) {
        this.context = context;
        this.serv_equip_list = serv_equip;
        this.idserveur= idserveur;
    }

    @NonNull
    @Override
        public ServeurEquipementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_serveur_equipement_list,viewGroup, false);
            return new ServeurEquipementViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull ServeurEquipementViewHolder serveurEquipementViewHolder, final int i) {
        Equipement serveurEquipement = serv_equip_list.get(i);
        serveurEquipementViewHolder.textView22.setText(serveurEquipement.getEquipementTtype());
        serveurEquipementViewHolder.textView23.setText(serveurEquipement.getEquipementNumserie());
        serveurEquipementViewHolder.cl_serverequipement.setOnClickListener((view) -> {
            Intent intent = new Intent(view.getContext(), ServeurFicheActivity.class);
            intent.putExtra("nom", serveurEquipement.getEquipementNnom());
            intent.putExtra("type", serveurEquipement.getEquipementTtype());
            intent.putExtra("numserie", serveurEquipement.getEquipementNumserie());
            context.startActivity(intent);
        });
        serveurEquipementViewHolder.cl_serverequipement.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showUpdateDeleteDialog(serveurEquipement.getEquipepmentId(), serveurEquipement.getEquipementTtype(), serveurEquipement.getEquipementNumserie());
                return false;
            }
        });
      serveurEquipementViewHolder.imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityEnvoyer.class);
                intent.putExtra("id", serveurEquipement.getEquipementNnom());
                intent.putExtra("type", serveurEquipement.getEquipementTtype());
                intent.putExtra("numserie", serveurEquipement.getEquipementNumserie());
                context.startActivity(intent);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return serv_equip_list.size();
    }

    private void showUpdateDeleteDialog(String servEquipementId, String servEquipementType, String servEquipementSN) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.serveur_equipement_dialogue, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextTextPersonName2 = (EditText) dialogView.findViewById(R.id.editTextTextPersonName2);
        final EditText editTextTextPersonName3 = (EditText) dialogView.findViewById(R.id.editTextTextPersonName3);
        final Button btn_modifier = (Button) dialogView.findViewById(R.id.btn_modifier);
        final Button btn_supprimer = (Button) dialogView.findViewById(R.id.btn_supprimer);
        editTextTextPersonName2.setText(servEquipementType);
        editTextTextPersonName3.setText(servEquipementSN);
        dialogBuilder.setTitle(servEquipementType);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        btn_modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = editTextTextPersonName2.getText().toString().trim();
                String num_serie = editTextTextPersonName3.getText().toString().trim();
                if (!TextUtils.isEmpty(type) && !TextUtils.isEmpty(num_serie)) {
                    updateEquipement(idserveur,servEquipementId, type, num_serie);
                    b.dismiss();
                }
            }
        });
        btn_supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteEquipement(idserveur,servEquipementId);
                b.dismiss();
            }
        });
    }

    private boolean deleteEquipement(String idserveur,String servEquipementId) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("equipements").child(servEquipementId);

        dR.removeValue();
        Toast.makeText(context, "Equipement supprimé avec succée", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean updateEquipement(String idserveur,String servEquipementId, String type, String num_serie) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("equipements").child(servEquipementId);

       Equipement serveurEquipement = new Equipement(servEquipementId, type, num_serie,"serveur",idserveur);
        dR.setValue(serveurEquipement);
        Toast.makeText(context, "Equipement modifié avec succée", Toast.LENGTH_LONG).show();
        return true;
    }
}
