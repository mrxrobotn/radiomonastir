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
import com.example.radiomonastir.Models.equipement_cellule;
import com.example.radiomonastir.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class equipement_celluleadapter extends RecyclerView.Adapter<cellule_equipementviewHolder> {
    private Context context;
    private List<equipement_cellule>cellule_equipementlist;

    private String idCell;
    public equipement_celluleadapter (Context context,List<equipement_cellule> cellules,String idCell){
        this.context=context;
        this.cellule_equipementlist=cellules;
        this.idCell = idCell;


    }
    @NonNull
    @Override
    public cellule_equipementviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_equipement_cellule_list,viewGroup,false);
        return new cellule_equipementviewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull cellule_equipementviewHolder cellule_equipementviewHolder, final int i) {
        equipement_cellule cellule = cellule_equipementlist.get(i);
        cellule_equipementviewHolder.textView21.setText(cellule.getEquipement_cellulenom());
        cellule_equipementviewHolder.textView22.setText(cellule.getEquipement_celluletype());
        cellule_equipementviewHolder.constraintequipementcellule.setOnClickListener((view) -> {
            Intent intent = new Intent(view.getContext(), CelluleEquipementActivity.class);
            intent.putExtra("id",cellule.getEquipepment_celluleId());
            intent.putExtra("nom",cellule.getEquipement_cellulenom());
            intent.putExtra("type",cellule.getEquipement_celluletype());
            intent.putExtra("numserie",cellule.getEquipement_cellulenumserie());
            context.startActivity(intent);

        });
        cellule_equipementviewHolder.constraintequipementcellule.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showUpdateDeleteDialog(cellule.getEquipepment_celluleId(),cellule.getEquipement_cellulenom(),cellule.getEquipement_celluletype(),cellule.getEquipement_cellulenumserie());
                return false;
            }
        });

    }
    @Override
    public int getItemCount() {
        return cellule_equipementlist.size();
    }

    private void showUpdateDeleteDialog(String equipepment_celluleId , String equipepment_cellulenom ,String equipepment_celluletype,String equipepment_cellulenumserie ) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.pop_upequipementcellule, null);
        dialogBuilder.setView(dialogView);

        final EditText nomequipementcellule = (EditText) dialogView.findViewById(R.id.nomequipementcellule);
        final EditText typeequipementcellule = (EditText) dialogView.findViewById(R.id.typeequipementcellule);
        final EditText serieequipementcellule = (EditText) dialogView.findViewById(R.id.serieequipementcellule);
        final Button modifierequipementcellule = (Button) dialogView.findViewById(R.id.modifierequipementcellule);
        final Button supprimerequipementcellule = (Button) dialogView.findViewById(R.id.supprimerequipementcellule);

        dialogBuilder.setTitle(equipepment_cellulenom);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        modifierequipementcellule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = nomequipementcellule.getText().toString().trim();
                String type = typeequipementcellule.getText().toString().trim();
                String numserie = serieequipementcellule.getText().toString().trim();
                if (!TextUtils.isEmpty(nom)) {
                    modifierequipementcellule(idCell,equipepment_celluleId,nom,type,numserie);
                    b.dismiss();
                }
            }
        });
        supprimerequipementcellule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                supprimerequipementcellule(idCell,equipepment_celluleId);
                b.dismiss();
            }
        });
    }

    private boolean supprimerequipementcellule(String idCell,String idEquip) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("cellules/"+idCell+"/equipement").child(idEquip);

        dR.removeValue();
        Toast.makeText(context, "cellule equipement supprimé avec succée", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean modifierequipementcellule(String idCell,String idequip, String nom ,String type, String numserie) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("cellules/"+idCell+"/equipement").child(idequip);

        equipement_cellule cellule = new equipement_cellule(idequip, nom,type,numserie);
        dR.setValue(cellule);
        Toast.makeText(context, "Cellule equipement modifié avec succée", Toast.LENGTH_LONG).show();
        return true;
    }


}
