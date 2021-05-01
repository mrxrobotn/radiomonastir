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

import com.example.radiomonastir.Adapters.MagasinAdapter;
import com.example.radiomonastir.Models.Equipement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MagasinActivity extends AppCompatActivity {
    EditText NomEquipement,TypeEquipement,NumSerie;
    Button btn_ajouter,btnEnvoyer;
    RecyclerView RecyclerViewMagasin;

    List<Equipement> magasinEquipementList =new ArrayList<>();



    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magasin);

        NomEquipement=(EditText)findViewById(R.id.NomEquipement);
        TypeEquipement=(EditText)findViewById(R.id.TypeEquipement);
        NumSerie=(EditText)findViewById(R.id.NumSerie);
        btn_ajouter=(Button)findViewById(R.id.btn_ajouter);
        btnEnvoyer=(Button)findViewById(R.id.btnEnvoyer);
        RecyclerViewMagasin=(RecyclerView)findViewById(R.id.RecyclerViewMagasin);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerViewMagasin.setLayoutManager(linearLayoutManager);
        RecyclerViewMagasin.setHasFixedSize(true);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef= database.getReference("equipements");

        btn_ajouter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nom = NomEquipement.getText().toString().trim();
                String type = TypeEquipement.getText().toString().trim();
                String num_serie = NumSerie.getText().toString().trim();
                if (!TextUtils.isEmpty(nom) && !TextUtils.isEmpty(type) && !TextUtils.isEmpty(num_serie)){
                    String id = myRef.push().getKey();
                    Equipement magasin= new Equipement(id, nom, type, num_serie,"magasin");
                    myRef.child(id).setValue(magasin);
                    NomEquipement.setText("");
                    TypeEquipement.setText("");
                    NumSerie.setText("");
                    Toast.makeText(MagasinActivity.this,"Equipement Ajouter",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MagasinActivity.this,"Erreur d'ajout",Toast.LENGTH_LONG).show();
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
                magasinEquipementList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Equipement equipement = postSnapshot.getValue(Equipement.class);
                    if(equipement.getEquipementPlace().equals("magasin")) {
                        magasinEquipementList.add(equipement);
                    }
                }
                MagasinAdapter magasinAdapter = new MagasinAdapter(MagasinActivity.this, magasinEquipementList);
                RecyclerViewMagasin.setAdapter(magasinAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MagasinActivity.this, "failed", Toast.LENGTH_LONG);
            }
        });
    }

}
