package com.example.radiomonastir;



import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.Adapters.CelluleAdapter;
import com.example.radiomonastir.Models.Cellule;
import com.example.radiomonastir.Models.Studio;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CelluleActivity extends AppCompatActivity {
    EditText editTextTextPersonName;
    Button btnAjouter;
    RecyclerView recyclerView;

    List<Cellule> celluleList =new ArrayList<>();



    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cellule);

        editTextTextPersonName=(EditText)findViewById(R.id.editTextTextPersonName);
        btnAjouter=(Button)findViewById(R.id.btnAjouter);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef= database.getReference("cellules");

        btnAjouter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nom = editTextTextPersonName.getText().toString().trim();
                if (!TextUtils.isEmpty(nom)){
                    String id = myRef.push().getKey();
                    Cellule cellule= new Cellule(id,nom);
                    myRef.child(id).setValue(cellule);
                    editTextTextPersonName.setText("");
                    Toast.makeText(CelluleActivity.this,"Cellule Ajouter",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(CelluleActivity.this,"Erreur d'ajout",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                celluleList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Cellule cellule = postSnapshot.getValue(Cellule.class);
                    celluleList.add(cellule);
                }
                CelluleAdapter celluleAdapter = new CelluleAdapter(CelluleActivity.this, celluleList);
                recyclerView.setAdapter(celluleAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CelluleActivity.this, "failed", Toast.LENGTH_LONG);
            }
        });
    }


    private void showUpdateDeleteDialog(String celluleId, String celluleName) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.cellule_dialogue, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextTextPersonName = (EditText) dialogView.findViewById(R.id.editTextTextPersonName);
        final Button buttonUpdateCellule = (Button) dialogView.findViewById(R.id.buttonUpdateCellule);
        final Button buttonDeleteCellule = (Button) dialogView.findViewById(R.id.buttonDeleteCellule);

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
        Toast.makeText(getApplicationContext(), "Cellule supprimé avec succée", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean updateCellule(String id, String name) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("cellules").child(id);

        Cellule cellule = new Cellule(id, name);
        dR.setValue(cellule);
        Toast.makeText(getApplicationContext(), "Cellule modifié avec succée", Toast.LENGTH_LONG).show();
        return true;
    }


}
