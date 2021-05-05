package com.example.radiomonastir.Adapters;

import android.app.AlertDialog;
import android.content.Context;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
//commentaire

public class StudioEquipementAdapter extends RecyclerView.Adapter<StudioEquipementViewHolder> {

    private Context context;
    private List<Equipement> studioEquipementList;
    private String idstudio;

    public StudioEquipementAdapter(Context context, List<Equipement> studio_equip, String idstudio) {
        this.context = context;
        this.studioEquipementList = studio_equip;
        this.idstudio = this.idstudio;
    }

    @NonNull
    @Override
    public StudioEquipementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_studio_equipement_list,viewGroup, false);
        return new StudioEquipementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudioEquipementViewHolder studioEquipementViewHolder, int i) {
        Equipement studio_equip = studioEquipementList.get(i);
        studioEquipementViewHolder.StudioEquipementName.setText(studio_equip.getEquipementNnom());
        studioEquipementViewHolder.StudioEquipementType.setText(studio_equip.getEquipementTtype());
        studioEquipementViewHolder.StudioEquipementSN.setText(studio_equip.getEquipementNumserie());


        studioEquipementViewHolder.cl_StudioEquipement.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showUpdateDeleteDialog(studio_equip.getEquipementNnom(),studio_equip.getEquipementTtype(),studio_equip.getEquipementNumserie());
                return false;
            }
        });
    }

    private void showUpdateDeleteDialog(String studioEquipementName, String studioEquipementType, String studioEquipementSN) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.studio_equipement_dialogue, null);
        dialogBuilder.setView(dialogView);

        final EditText steqname = (EditText) dialogView.findViewById(R.id.steqname);
        final EditText steqtype = (EditText) dialogView.findViewById(R.id.steqtype);
        final EditText steqsn = (EditText) dialogView.findViewById(R.id.steqsn);
        final Button btneditsteq = (Button) dialogView.findViewById(R.id.btneditsteq);
        final Button btndeletesteq = (Button) dialogView.findViewById(R.id.btndeletesteq);

        dialogBuilder.setTitle(studioEquipementSN);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        btneditsteq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = steqname.getText().toString().trim();
                String type = steqtype.getText().toString().trim();
                String num_serie = steqsn.getText().toString().trim();
                if (!TextUtils.isEmpty(nom) && !TextUtils.isEmpty(type) && !TextUtils.isEmpty(num_serie)) {
                    updateEquipement(idstudio,nom, type, num_serie);
                    b.dismiss();
                }
            }
        });
        btneditsteq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteEquipement(studioEquipementSN);
                b.dismiss();
            }
        });
    }

    private boolean deleteEquipement(String studioEquipementSN) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("equipements").child(studioEquipementSN);

        dR.removeValue();
        Toast.makeText(context, "Equipement supprimé avec succée", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean updateEquipement( String id,String nom, String type, String num_serie) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("equipements").child(num_serie);

       Equipement studio_equip  = new Equipement(id,nom,type,num_serie,"cellule",idstudio);
        dR.setValue(studio_equip);
        Toast.makeText(context, "Equipement modifié avec succée", Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public int getItemCount() {
        return studioEquipementList.size();
    }
}
