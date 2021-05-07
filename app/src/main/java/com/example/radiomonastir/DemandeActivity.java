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

import com.example.radiomonastir.Adapters.DemandeAdapter;
import com.example.radiomonastir.Models.Demande;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DemandeActivity extends AppCompatActivity {
    EditText editTextTextMultiLine2;
    Button button11;
    RecyclerView Rv_demande;
    List<Demande> demandeList =new ArrayList<>();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande);

        editTextTextMultiLine2=(EditText)findViewById(R.id.editTextTextMultiLine2);
        button11=(Button)findViewById(R.id.button11);
        Rv_demande=(RecyclerView)findViewById(R.id.Rv_demande);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        Rv_demande.setLayoutManager(linearLayoutManager);
        Rv_demande.setHasFixedSize(true);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef= database.getReference("magasin/demandes");

        button11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String demande_desc = editTextTextMultiLine2.getText().toString().trim();
                if (!TextUtils.isEmpty(demande_desc)){
                    String id = myRef.push().getKey();
                    Demande demande= new Demande(id, demande_desc);
                    myRef.child(id).setValue(demande);
                    editTextTextMultiLine2.setText("");
                    Toast.makeText(DemandeActivity.this,"Panne Envoyer",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(DemandeActivity.this,"Erreur!",Toast.LENGTH_LONG).show();
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
                demandeList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Demande demande = postSnapshot.getValue(Demande.class);
                    demandeList.add(demande);
                }
                DemandeAdapter demandeAdapter = new DemandeAdapter(DemandeActivity.this, demandeList);
                Rv_demande.setAdapter(demandeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(DemandeActivity.this, "failed", Toast.LENGTH_LONG);
            }
        });
    }
}