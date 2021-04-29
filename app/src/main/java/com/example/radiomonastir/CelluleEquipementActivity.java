package com.example.radiomonastir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.radiomonastir.Adapters.CelluleAdapter;
import com.example.radiomonastir.Adapters.equipement_celluleadapter;
import com.example.radiomonastir.Models.Cellule;
import com.example.radiomonastir.Models.equipement_cellule;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CelluleEquipementActivity extends AppCompatActivity {
    EditText editText;
    EditText editText2;
    EditText editText3;
    TextView textView14;
    Button button5;
    Button btnEnvoyer;
    RecyclerView recyclerView2;

    List<equipement_cellule> cellule_equipementlist =new ArrayList<>();



    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cellule_equipement);

        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        textView14=(TextView) findViewById(R.id.textView14);
        button5=(Button)findViewById(R.id.button5);
        btnEnvoyer=(Button)findViewById(R.id.btnEnvoyer);
        recyclerView2=(RecyclerView)findViewById(R.id.recyclerView2);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(linearLayoutManager);
        recyclerView2.setHasFixedSize(true);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef= database.getReference("equipement");

        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nom = editText.getText().toString().trim();
                String type = editText2.getText().toString().trim();
                String numserie = editText3.getText().toString().trim();
                if (!TextUtils.isEmpty(nom)&& !TextUtils.isEmpty(type)&&!TextUtils.isEmpty(numserie)){
                    String id = myRef.push().getKey();
                    equipement_cellule cellule= new equipement_cellule(id,nom,type,numserie);
                    myRef.child(id).setValue(cellule);
                    editText.setText("");
                    editText2.setText("");
                    editText3.setText("");
                    Toast.makeText(CelluleEquipementActivity.this,"Equipement Ajouter",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(CelluleEquipementActivity.this,"Erreur d'ajout",Toast.LENGTH_LONG).show();
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
                cellule_equipementlist.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    equipement_cellule cellule = postSnapshot.getValue(equipement_cellule.class);
                    cellule_equipementlist.add(cellule);
                }
                equipement_celluleadapter celluleAdapter = new equipement_celluleadapter(CelluleEquipementActivity.this, cellule_equipementlist);
                recyclerView2.setAdapter(celluleAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CelluleEquipementActivity.this, "failed", Toast.LENGTH_LONG);
            }
        });
    }

}