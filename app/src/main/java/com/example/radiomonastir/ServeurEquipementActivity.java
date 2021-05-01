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

import com.example.radiomonastir.Adapters.ServeurEquipementAdapter;
import com.example.radiomonastir.Models.Equipement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ServeurEquipementActivity extends AppCompatActivity {

    TextView textView21;
    EditText editTextTextPersonName2,editTextTextPersonName3;
    Button button5,button6;
    RecyclerView Rv_ServerEquip;

    List<Equipement> serv_equip_list =new ArrayList<>();
    String idserveur;

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serveur_equipement);

        idserveur = getIntent().getStringExtra("id");

        textView21 = (TextView) findViewById(R.id.textView21);
        editTextTextPersonName2 = (EditText) findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName3 = (EditText) findViewById(R.id.editTextTextPersonName3);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        Rv_ServerEquip = (RecyclerView) findViewById(R.id.Rv_ServerEquip);

        Bundle bundle=getIntent().getExtras();
        String NomServeur=bundle.getString("nom");
        textView21.setText(NomServeur);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        Rv_ServerEquip.setLayoutManager(linearLayoutManager);
        Rv_ServerEquip.setHasFixedSize(true);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef= database.getReference("equipements");

        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String type = editTextTextPersonName2.getText().toString().trim();
                String num_serie = editTextTextPersonName3.getText().toString().trim();
                if (!TextUtils.isEmpty(type) && !TextUtils.isEmpty(num_serie)){
                    String id = myRef.push().getKey();
                    Equipement serveurEquipement= new Equipement(id,"hjbkbk",type,num_serie,"serveur", idserveur);
                    myRef.child(id).setValue(serveurEquipement);
                    editTextTextPersonName2.setText("");
                    editTextTextPersonName3.setText("");
                    Toast.makeText(ServeurEquipementActivity.this,"Equipement Ajouter",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(ServeurEquipementActivity.this,"Erreur d'ajout",Toast.LENGTH_LONG).show();
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
                serv_equip_list.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Equipement serveurEquipement = postSnapshot.getValue(Equipement.class);
                    if(serveurEquipement.getEquipementParentId() != null && serveurEquipement.getEquipementParentId().equals(idserveur)){
                        serv_equip_list.add(serveurEquipement);

                    }
                }
                ServeurEquipementAdapter serveurEquipementAdapter = new ServeurEquipementAdapter(ServeurEquipementActivity.this, serv_equip_list,idserveur);
                Rv_ServerEquip.setAdapter(serveurEquipementAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ServeurEquipementActivity.this, "failed", Toast.LENGTH_LONG);
            }
        });
    }
}