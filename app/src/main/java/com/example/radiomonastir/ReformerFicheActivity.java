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
import com.example.radiomonastir.Models.FicheIncident;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;;

import java.util.ArrayList;
import java.util.List;

public class ReformerFicheActivity extends AppCompatActivity {
    EditText editTextTextPersonName16,editTextDate, textArea_Panne, textArea_Observation;
    Button button6;
    RecyclerView recyclerView4;

    List<FicheIncident> ficheIncidentList =new ArrayList<>();

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reformer_fiche);

        TextView tv1 = (TextView) findViewById(R.id.textView18);
        TextView tv2 = (TextView) findViewById(R.id.textView19);
        TextView tv3 = (TextView) findViewById(R.id.textView20);
        editTextTextPersonName16 = (EditText) findViewById(R.id.editTextTextPersonName16);
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        textArea_Panne = (EditText) findViewById(R.id.textArea_Panne);
        textArea_Observation = (EditText) findViewById(R.id.textArea_Observation);
        button6 = (Button) findViewById(R.id.button6);
        recyclerView4 = (RecyclerView) findViewById(R.id.recyclerView4);

        Bundle bundle=getIntent().getExtras();
        String s1=bundle.getString("nom");
        tv1.setText(s1);
        String s2=bundle.getString("type");
        tv2.setText(s2);
        String s3=bundle.getString("numserie");
        tv3.setText(s3);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView4.setLayoutManager(linearLayoutManager);
        recyclerView4.setHasFixedSize(true);
        String id= getIntent().getStringExtra("id");
        String nom= getIntent().getStringExtra("nom");


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef= database.getReference("equipements/"+id+"/fiches_incident");

        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nom = editTextTextPersonName16.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();
                String panne = textArea_Panne.getText().toString().trim();
                String observation = textArea_Observation.getText().toString().trim();
                if (!TextUtils.isEmpty(nom) && !TextUtils.isEmpty(date) && !TextUtils.isEmpty(panne) && !TextUtils.isEmpty(observation)){
                    String id = myRef.push().getKey();
                    FicheIncident ficheIncident= new FicheIncident(id, nom, date, panne, observation);
                    myRef.child(id).setValue(ficheIncident);
                    editTextTextPersonName16.setText("");
                    editTextDate.setText("");
                    textArea_Panne.setText("");
                    textArea_Observation.setText("");
                    Toast.makeText(ReformerFicheActivity.this,"Fiche Ajouter",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(ReformerFicheActivity.this,"Erreur d'ajout",Toast.LENGTH_LONG).show();
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
                ficheIncidentList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    FicheIncident ficheIncident = postSnapshot.getValue(FicheIncident.class);
                    ficheIncidentList.add(ficheIncident);
                }
                ReformerFicheAdapter reformerFicheAdapter = new ReformerFicheAdapter(ReformerFicheActivity.this, ficheIncidentList);
                recyclerView4.setAdapter(reformerFicheAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ReformerFicheActivity.this, "failed", Toast.LENGTH_LONG);
            }
        });
    }
}