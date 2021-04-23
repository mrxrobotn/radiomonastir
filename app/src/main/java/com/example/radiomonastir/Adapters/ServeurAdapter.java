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
import com.example.radiomonastir.Models.Serveur;
import com.example.radiomonastir.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ServeurAdapter extends RecyclerView.Adapter<ServeurViewHolder> {
    private Context context;
    private List<Serveur> serveurList;
    public ServeurAdapter (Context context,List<Serveur> serveurs){
        this.context=context;
        this.serveurList=serveurs;

    }

    @NonNull
    @Override
    public ServeurViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_serveur_list,viewGroup,false);
        return new ServeurViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServeurViewHolder serveurViewHolder, final int i) {
        Serveur serveur = serveurList.get(i);
        serveurViewHolder.NomServeur.setText(serveur.getServeurName());
        serveurViewHolder.item_serveur.setOnClickListener((view) -> {
            Intent intent = new Intent(view.getContext(), CelluleEquipementActivity.class);
            intent.putExtra("id",serveur.getServeurId());
            intent.putExtra("nom",serveur.getServeurName());
            context.startActivity(intent);

        });
        serveurViewHolder.item_serveur.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showUpdateDeleteDialog(serveur.getServeurId(),serveur.getServeurName());
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return serveurList.size();
    }

    private void showUpdateDeleteDialog(String serveurId, String serveurName) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = inflater.inflate(R.layout.serveur_dialogue, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextServeur = (EditText) dialogView.findViewById(R.id.editTextServeur);
        final Button btnModifierServeur = (Button) dialogView.findViewById(R.id.btnModifierServeur);
        final Button btnDeleteServeur = (Button) dialogView.findViewById(R.id.btnDeleteServeur);

        dialogBuilder.setTitle(serveurName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        btnModifierServeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextServeur.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    updateServeur(serveurId,name);
                    b.dismiss();
                }
            }
        });
        btnDeleteServeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteServeur(serveurId);
                b.dismiss();
            }
        });
    }

    private boolean deleteServeur(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("serveurs").child(id);

        dR.removeValue();
        Toast.makeText(context, "serveur supprimé avec succée", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean updateServeur(String id, String name) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("serveurs").child(id);

        Serveur serveur = new Serveur(id, name);
        dR.setValue(serveur);
        Toast.makeText(context, "serveur modifié avec succée", Toast.LENGTH_LONG).show();
        return true;
    }
}
