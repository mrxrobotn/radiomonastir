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
import android.widget.TextView;
import android.widget.Toast;

import com.example.radiomonastir.Adapters.fiche_serveur_equipAdapter;
import com.example.radiomonastir.Models.FicheIncident;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ServeurFicheActivity extends AppCompatActivity {
    EditText editTextTextPersonName17,editTextDate4,editTextTextMultiLine3,editTextTextMultiLine4;
    Button button13;
    RecyclerView recyclerView7;

    List<FicheIncident> ficheServeurList =new ArrayList<>();

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serveur_fiche);
        TextView tv1 = (TextView) findViewById(R.id.textView66);
        TextView tv2 = (TextView) findViewById(R.id.textView67);
        TextView tv3 = (TextView) findViewById(R.id.textView68);
        editTextTextPersonName17 = (EditText) findViewById(R.id.editTextTextPersonName17);
        editTextDate4 = (EditText) findViewById(R.id.editTextDate4);
        editTextTextMultiLine3 = (EditText) findViewById(R.id.editTextTextMultiLine3);
        editTextTextMultiLine4 = (EditText) findViewById(R.id.editTextTextMultiLine4);
        button13 = (Button) findViewById(R.id.button13);
        recyclerView7 = (RecyclerView) findViewById(R.id.recyclerView7);

        Bundle bundle = getIntent().getExtras();
        String s1 = bundle.getString("nom");
        tv1.setText(s1);
        String s2 = bundle.getString("type");
        tv2.setText(s2);
        String s3 = bundle.getString("numserie");
        tv3.setText(s3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView7.setLayoutManager(linearLayoutManager);
        recyclerView7.setHasFixedSize(true);
        String id = getIntent().getStringExtra("id");
        String nom = getIntent().getStringExtra("nom");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("equipements/" + id + "/fiches_incident");
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = editTextTextPersonName17.getText().toString().trim();
                String date = editTextDate4.getText().toString().trim();
                String panne = editTextTextMultiLine3.getText().toString().trim();
                String observation = editTextTextMultiLine4.getText().toString().trim();
                if (!TextUtils.isEmpty(nom) && !TextUtils.isEmpty(date) && !TextUtils.isEmpty(panne) && !TextUtils.isEmpty(observation)) {
                    String id = myRef.push().getKey();
                    FicheIncident ficheServeur = new FicheIncident(id, nom, date, panne, observation);
                    myRef.child(id).setValue(ficheServeur);
                    editTextTextPersonName17.setText("");
                    editTextDate4.setText("");
                    editTextTextMultiLine3.setText("");
                    editTextTextMultiLine4.setText("");
                    Toast.makeText(ServeurFicheActivity.this, "Fiche Ajouter", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ServeurFicheActivity.this, "Erreur d'ajout", Toast.LENGTH_LONG).show();
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
                    ficheServeurList.clear();
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        FicheIncident ficheIncident = postSnapshot.getValue(FicheIncident.class);
                        ficheServeurList.add(ficheIncident);
                    }
                    fiche_serveur_equipAdapter fiche_serveur_equipAdapter = new fiche_serveur_equipAdapter(ServeurFicheActivity.this, ficheServeurList);
                    recyclerView7.setAdapter(fiche_serveur_equipAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ServeurFicheActivity.this, "failed", Toast.LENGTH_LONG);
                }
            });
        }
    }
