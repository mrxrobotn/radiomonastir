package com.example.radiomonastir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.radiomonastir.Adapters.DemandeAdapter;
import com.example.radiomonastir.Adapters.PanneAdapter;
import com.example.radiomonastir.Models.Demande;
import com.example.radiomonastir.Models.Panne;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MaintenanceAdminActivity extends AppCompatActivity {
    RecyclerView Rv_panne, Rv_demande;
    List<Panne> panneList =new ArrayList<>();
    List<Demande> demandeList =new ArrayList<>();
    DatabaseReference myRef1,myRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_admin);

        Rv_panne=(RecyclerView)findViewById(R.id.Rv_panne);
        Rv_demande=(RecyclerView)findViewById(R.id.Rv_demande);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        Rv_panne.setLayoutManager(linearLayoutManager);
        Rv_panne.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        Rv_demande.setLayoutManager(linearLayoutManager1);
        Rv_demande.setHasFixedSize(true);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef1= database.getReference("maintenance/pannes");
        myRef2= database.getReference("maintenance/demandes");
    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                panneList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Panne panne = postSnapshot.getValue(Panne.class);
                    panneList.add(panne);
                }
                PanneAdapter panneAdapter = new PanneAdapter(MaintenanceAdminActivity.this, panneList);
                Rv_panne.setAdapter(panneAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MaintenanceAdminActivity.this, "failed", Toast.LENGTH_LONG);
            }
        });
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                demandeList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Demande demande = postSnapshot.getValue(Demande.class);
                    demandeList.add(demande);
                }
                DemandeAdapter demandeAdapter = new DemandeAdapter(MaintenanceAdminActivity.this, demandeList);
                Rv_demande.setAdapter(demandeAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MaintenanceAdminActivity.this, "failed", Toast.LENGTH_LONG);
            }
        });
    }
}