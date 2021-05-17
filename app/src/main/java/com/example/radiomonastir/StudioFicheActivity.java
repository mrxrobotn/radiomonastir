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

import com.example.radiomonastir.Adapters.ReformerFicheAdapter;
import com.example.radiomonastir.Adapters.fiche_cellule_equipAdapter;
import com.example.radiomonastir.Adapters.fiche_studio_equipAdapter;
import com.example.radiomonastir.Models.FicheIncident;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudioFicheActivity extends AppCompatActivity {
    EditText editTextTextPersonName13,editTextDate3,editTextTextPersonName14,editTextTextPersonName15;
    Button button12;
    RecyclerView recyclerView6;
    List<FicheIncident> ficheStudioList =new ArrayList<>();

    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio_fiche);
        TextView tv1 = (TextView) findViewById(R.id.textView49);
        TextView tv2 = (TextView) findViewById(R.id.textView50);
        TextView tv3 = (TextView) findViewById(R.id.textView51);
        editTextTextPersonName13=(EditText) findViewById((R.id.editTextTextPersonName13));
        editTextDate3=(EditText) findViewById((R.id.editTextDate3));
        editTextTextPersonName14=(EditText) findViewById((R.id.editTextTextPersonName14));
        editTextTextPersonName15=(EditText) findViewById((R.id.editTextTextPersonName15));
        button12 = (Button) findViewById(R.id.button12);
        recyclerView6 = (RecyclerView) findViewById(R.id.recyclerView6);

        Bundle bundle=getIntent().getExtras();
        String s1=bundle.getString("nom");
        tv1.setText(s1);
        String s2=bundle.getString("type");
        tv2.setText(s2);
        String s3=bundle.getString("numserie");
        tv3.setText(s3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView6.setLayoutManager(linearLayoutManager);
        recyclerView6.setHasFixedSize(true);
        String id= getIntent().getStringExtra("id");
        String nom= getIntent().getStringExtra("nom");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef= database.getReference("equipements/"+id+"/fiches_incident");
        button12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nom = editTextTextPersonName13.getText().toString().trim();
                String date = editTextDate3.getText().toString().trim();
                String panne = editTextTextPersonName14.getText().toString().trim();
                String observation = editTextTextPersonName15.getText().toString().trim();
                if (!TextUtils.isEmpty(nom) && !TextUtils.isEmpty(date) && !TextUtils.isEmpty(panne) && !TextUtils.isEmpty(observation)) {
                    String id = myRef.push().getKey();
                    FicheIncident ficheStudio = new FicheIncident(id, nom, date, panne, observation);
                    myRef.child(id).setValue(ficheStudio);
                    editTextTextPersonName13.setText("");
                    editTextDate3.setText("");
                    editTextTextPersonName14.setText("");
                    editTextTextPersonName15.setText("");
                    Toast.makeText(StudioFicheActivity.this, "Fiche Ajouter", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(StudioFicheActivity.this, "Erreur d'ajout", Toast.LENGTH_LONG).show();
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
                    ficheStudioList.clear();
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        FicheIncident ficheIncident = postSnapshot.getValue(FicheIncident.class);
                       ficheStudioList.add(ficheIncident);
                    }
                    fiche_studio_equipAdapter fiche_studio_equipAdapter = new fiche_studio_equipAdapter(StudioFicheActivity.this, ficheStudioList);
                    recyclerView6.setAdapter(fiche_studio_equipAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(StudioFicheActivity.this, "failed", Toast.LENGTH_LONG);
                }
            });
        }



    }
