package com.example.radiomonastir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.radiomonastir.Adapters.reformer_equipementAdapter;
import com.example.radiomonastir.Models.Cellule;
import com.example.radiomonastir.Models.Equipement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class ReformerActivity extends AppCompatActivity {
    EditText editTextTextPersonName7, editTextTextPersonName8, editTextTextPersonName9;
    Button button7;
    RecyclerView recyclerviewreformer;

    List<Equipement> eqiupement_reformerlist = new ArrayList<>();
    String idreformer;

    DatabaseReference data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reformer);
        idreformer = getIntent().getStringExtra("id");

        editTextTextPersonName7 = (EditText) findViewById(R.id.editTextTextPersonName7);
        editTextTextPersonName8 = (EditText) findViewById(R.id.editTextTextPersonName8);
        editTextTextPersonName9 = (EditText) findViewById(R.id.editTextTextPersonName9);
        button7 = (Button) findViewById(R.id.button7);
        recyclerviewreformer = (RecyclerView) findViewById(R.id.recyclerviewreformer);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerviewreformer.setLayoutManager(linearLayoutManager);
        recyclerviewreformer.setHasFixedSize(true);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        data = database.getReference("equipements");


        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = editTextTextPersonName7.getText().toString().trim();
                String type = editTextTextPersonName8.getText().toString().trim();
                String num_serie = editTextTextPersonName9.getText().toString().trim();
                if (!TextUtils.isEmpty(nom) && !TextUtils.isEmpty(type) && !TextUtils.isEmpty(num_serie)) {
                    String id = data.push().getKey();
                    Equipement reformer = new Equipement(id, nom, type, num_serie, "reformer",idreformer);
                    data.child(id).setValue(reformer);
                    editTextTextPersonName7.setText("");
                    editTextTextPersonName8.setText("");
                    editTextTextPersonName9.setText("");
                    Toast.makeText(ReformerActivity.this, "Equipement Ajouter", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ReformerActivity.this, "Erreur d'ajout", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                eqiupement_reformerlist.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Equipement equipement = postSnapshot.getValue(Equipement.class);
                    if (equipement.getEquipementParentId() != null &&equipement.getEquipementParentId().equals(idreformer)) {
                        eqiupement_reformerlist.add(equipement);
                    }
                }
                reformer_equipementAdapter reformer_equipementAdapter = new reformer_equipementAdapter(ReformerActivity.this, eqiupement_reformerlist);
                recyclerviewreformer.setAdapter(reformer_equipementAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ReformerActivity.this, "failed", Toast.LENGTH_LONG);
            }
        });
    }
}


