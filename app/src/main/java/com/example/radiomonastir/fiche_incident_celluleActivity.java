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

import com.example.radiomonastir.Adapters.FicheIncidentAdapter;
import com.example.radiomonastir.Models.FicheIncident;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class fiche_incident_celluleActivity extends AppCompatActivity {
    EditText editTextTextPersonName10, editTextTextPersonName11, editTextTextPersonName12;
    Button button10;
    RecyclerView recyclerView5;

    List<FicheIncident> ficheCellList =new ArrayList<>();

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_incident_cellule);
        TextView tv1 = (TextView) findViewById(R.id.textView36);
        TextView tv2 = (TextView) findViewById(R.id.textView37);
        TextView tv3 = (TextView) findViewById(R.id.textView38);
        editTextTextPersonName10 = (EditText) findViewById(R.id.editTextTextPersonName10);
        editTextTextPersonName11 = (EditText) findViewById(R.id.editTextTextPersonName11);
        editTextTextPersonName12 = (EditText) findViewById(R.id.editTextTextPersonName12);
        button10 = (Button) findViewById(R.id.button10);
        recyclerView5 = (RecyclerView) findViewById(R.id.recyclerView5);

        Bundle bundle=getIntent().getExtras();
        String s1=bundle.getString("nom");
        tv1.setText(s1);
        String s2=bundle.getString("type");
        tv2.setText(s2);
        String s3=bundle.getString("numserie");
        tv3.setText(s3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView5.setLayoutManager(linearLayoutManager);
        recyclerView5.setHasFixedSize(true);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef= database.getReference("fiches_incident/Equipement/id");

        button10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String date = editTextTextPersonName10.getText().toString().trim();
                String panne = editTextTextPersonName11.getText().toString().trim();
                String observation = editTextTextPersonName12.getText().toString().trim();
                if (!TextUtils.isEmpty(date) && !TextUtils.isEmpty(panne) && !TextUtils.isEmpty(observation)){
                    String id = myRef.push().getKey();
                    FicheIncident ficheCell= new FicheIncident(id, date, panne, observation);
                    myRef.child(id).setValue(ficheCell);
                    editTextTextPersonName10.setText("");
                    editTextTextPersonName11.setText("");
                   editTextTextPersonName12.setText("");
                    Toast.makeText(fiche_incident_celluleActivity.this,"Fiche Ajouter",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(fiche_incident_celluleActivity.this,"Erreur d'ajout",Toast.LENGTH_LONG).show();
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
               ficheCellList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    FicheIncident ficheIncident = postSnapshot.getValue(FicheIncident.class);
                    ficheCellList.add(ficheIncident);
                }
                FicheIncidentAdapter ficheIncidentAdapter = new FicheIncidentAdapter(fiche_incident_celluleActivity.this, ficheCellList);
                recyclerView5.setAdapter(ficheIncidentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(fiche_incident_celluleActivity.this, "failed", Toast.LENGTH_LONG);
            }
        });
    }
}