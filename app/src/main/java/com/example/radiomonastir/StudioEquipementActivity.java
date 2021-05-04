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

import com.example.radiomonastir.Adapters.StudioEquipementAdapter;
import com.example.radiomonastir.Models.Equipement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudioEquipementActivity extends AppCompatActivity {
    TextView textView24;
    EditText StudioEquipementName, StudioEquipementType, StudioEquipementSN;
    Button btnAddStudioEquipement, btnSendStudioEquipement;
    RecyclerView Rv_Studio_Equipement;
    List<Equipement> studioEquipementList =new ArrayList<>();
    String idstudio;

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio_equipement);

        textView24 = (TextView) findViewById(R.id.textView24);
        StudioEquipementName = (EditText) findViewById(R.id.StudioEquipementName);
        StudioEquipementType = (EditText) findViewById(R.id.StudioEquipementType);
        StudioEquipementName = (EditText) findViewById(R.id.StudioEquipementSN);
        btnAddStudioEquipement = (Button) findViewById(R.id.btnAddStudioEquipement);
        Rv_Studio_Equipement = (RecyclerView) findViewById(R.id.Rv_Studio_Equipement);

        Bundle bundle=getIntent().getExtras();
        String s=bundle.getString("number");
        textView24.setText(s);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        Rv_Studio_Equipement.setLayoutManager(linearLayoutManager);
        Rv_Studio_Equipement.setHasFixedSize(true);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef= database.getReference("equipements");

        btnAddStudioEquipement.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nom = StudioEquipementName.getText().toString().trim();
                String type = StudioEquipementType.getText().toString().trim();
                String num_serie = StudioEquipementSN.getText().toString().trim();
                if (!TextUtils.isEmpty(nom) && !TextUtils.isEmpty(type) && !TextUtils.isEmpty(num_serie)){
                    String id = myRef.push().getKey();
                   Equipement studio_equip= new Equipement(id,nom,type,num_serie,"studio_equip",idstudio);
                    myRef.child(id).setValue(studio_equip);
                    StudioEquipementName.setText("");
                    StudioEquipementType.setText("");
                    StudioEquipementSN.setText("");
                    Toast.makeText(StudioEquipementActivity.this,"Equipement Ajouter",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(StudioEquipementActivity.this,"Erreur d'ajout",Toast.LENGTH_LONG).show();
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
                studioEquipementList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                   Equipement equipement = postSnapshot.getValue(Equipement.class);
                    studioEquipementList.add(equipement);
                    if(equipement.getEquipementParentId() != null && equipement.getEquipementParentId().equals( idstudio)){
                        studioEquipementList.add(equipement);
                    }
                }
                StudioEquipementAdapter studioEquipementAdapter = new StudioEquipementAdapter(StudioEquipementActivity.this, studioEquipementList,idstudio);
                Rv_Studio_Equipement.setAdapter(studioEquipementAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(StudioEquipementActivity.this, "failed", Toast.LENGTH_LONG);
            }
        });
    }
}