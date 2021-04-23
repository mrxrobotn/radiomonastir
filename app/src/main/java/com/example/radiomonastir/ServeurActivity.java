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

import com.example.radiomonastir.Adapters.ServeurAdapter;
import com.example.radiomonastir.Models.Serveur;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ServeurActivity extends AppCompatActivity {

    EditText NomServeur;
    Button btnAjoutServeur;
    RecyclerView RecyclerView;

    List<Serveur> serveurList =new ArrayList<>();

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serveur);

        NomServeur=(EditText)findViewById(R.id.NomServeur);
        btnAjoutServeur=(Button)findViewById(R.id.btnAjoutServeur);
        RecyclerView=(RecyclerView)findViewById(R.id.RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.setHasFixedSize(true);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef= database.getReference("serveurs");

        btnAjoutServeur.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nom = NomServeur.getText().toString().trim();
                if (!TextUtils.isEmpty(nom)){
                    String id = myRef.push().getKey();
                    Serveur serveur= new Serveur(id,nom);
                    myRef.child(id).setValue(serveur);
                    NomServeur.setText("");
                    Toast.makeText(ServeurActivity.this,"Serveur Ajouter",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(ServeurActivity.this,"Erreur d'ajout",Toast.LENGTH_LONG).show();
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
                serveurList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Serveur serveur = postSnapshot.getValue(Serveur.class);
                    serveurList.add(serveur);
                }
                ServeurAdapter serveurAdapter = new ServeurAdapter(ServeurActivity.this, serveurList);
                RecyclerView.setAdapter(serveurAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ServeurActivity.this, "failed", Toast.LENGTH_LONG);
            }
        });
    }

}