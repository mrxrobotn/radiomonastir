package com.example.radiomonastir;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.Adapters.equipementStudioadapter;
import com.example.radiomonastir.Models.Equipement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class equipementStudioActivity extends AppCompatActivity {
    EditText StudioEquipementName;
    EditText StudioEquipementType;
    EditText StudioEquipementNS;
    Button btnAddStudioEquipement;
    RecyclerView Rv_Studio_Equipement;

    List<Equipement> studio_equipementlist =new ArrayList<>();
    String idstudio;
    DatabaseReference data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio_equipement);

        idstudio = getIntent().getStringExtra("id");
        TextView nomstudio = (TextView) findViewById(R.id.nomstudio);
        Bundle bundle=getIntent().getExtras();
        String s1=bundle.getString("nomstudio");
        nomstudio.setText(s1);

        StudioEquipementName=(EditText)findViewById(R.id.StudioEquipementName);
        StudioEquipementType=(EditText)findViewById(R.id. StudioEquipementType);
        StudioEquipementNS=(EditText)findViewById(R.id. StudioEquipementNS);
        btnAddStudioEquipement=(Button)findViewById(R.id.btnAddStudioEquipement);
        Rv_Studio_Equipement=(RecyclerView)findViewById(R.id.Rv_Studio_Equipement);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        Rv_Studio_Equipement.setLayoutManager(linearLayoutManager);
        Rv_Studio_Equipement.setHasFixedSize(true);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        data= database.getReference("equipements");

        btnAddStudioEquipement.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nom = StudioEquipementName.getText().toString().trim();
                String type = StudioEquipementType.getText().toString().trim();
                String numserie = StudioEquipementNS.getText().toString().trim();
                if (!TextUtils.isEmpty(nom)&& !TextUtils.isEmpty(type)&&!TextUtils.isEmpty(numserie)){
                    String id = data.push().getKey();
                    Equipement studio=  new Equipement(id,nom,type,numserie,"studios",idstudio);
                    data.child(id).setValue(studio);
                    StudioEquipementName.setText("");
                    StudioEquipementType.setText("");
                    StudioEquipementNS.setText("");
                    Toast.makeText(equipementStudioActivity.this,"Equipement Ajouter",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(equipementStudioActivity.this,"Erreur d'ajout",Toast.LENGTH_LONG).show();
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
                studio_equipementlist.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Equipement equipement = postSnapshot.getValue(Equipement.class);
                    if(equipement.getEquipementParentId() != null && equipement.getEquipementParentId().equals(idstudio)){
                        studio_equipementlist.add(equipement);
                    }
                }
                equipementStudioadapter studioadapter = new equipementStudioadapter(equipementStudioActivity.this, studio_equipementlist,idstudio);
                Rv_Studio_Equipement.setAdapter(studioadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(equipementStudioActivity.this, "failed", Toast.LENGTH_LONG);
            }
        });
    }


    }
