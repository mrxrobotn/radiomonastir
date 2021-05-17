package com.example.radiomonastir;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.radiomonastir.Adapters.CelluleAdapter;
import com.example.radiomonastir.Models.Cellule;
import com.example.radiomonastir.Models.Serveur;
import com.example.radiomonastir.Models.Studio;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityEnvoyer  extends AppCompatActivity {
    List<Studio> studioList =new ArrayList<>();
    List<Serveur> serveurList =new ArrayList<>();
    List<Cellule> celluleList =new ArrayList<>();
    Spinner spinnerServeur,spinnerCellule,spinnerStudio;
    CheckBox checkBox, checkBox2;
    Button button3, button7;
    DatabaseReference myRefCell,myRefStudio,myRefserveur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoyer);
        spinnerServeur=findViewById(R.id.spinner3);
        spinnerCellule=findViewById(R.id.spinner4);
        checkBox=findViewById(R.id.checkBox);
        checkBox2=findViewById(R.id.checkBox2);
        button3=findViewById(R.id.button3);
        button7=findViewById(R.id.button7);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRefCell= database.getReference("cellules");

        myRefStudio= database.getReference("studio");
        myRefserveur= database.getReference("serveur");


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){

                }
            }
        });


    }



    @Override
    protected void onStart() {
        super.onStart();

        myRefCell.addValueEventListener(new ValueEventListener() {
            List<String> celluleName = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                celluleList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Cellule cellule = postSnapshot.getValue(Cellule.class);
                    celluleList.add(cellule);
                    celluleName.add( cellule.getCelluleName());

                }


                //spinner
                //Creating the ArrayAdapter instance having the country list
               // ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,celluleName);
               // aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //Setting the ArrayAdapter data on the Spinner
               // spinnerCellule.setAdapter(aa);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityEnvoyer.this, "failed", Toast.LENGTH_LONG);
            }
        });
        myRefStudio.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studioList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Studio studio = postSnapshot.getValue(Studio.class);
                    studioList.add(studio);
                }

                //spinner

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityEnvoyer.this, "failed", Toast.LENGTH_LONG);
            }
        });
        myRefserveur.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                serveurList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Serveur serveur = postSnapshot.getValue(Serveur.class);
                    serveurList.add(serveur);
                }

                //spinner

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ActivityEnvoyer.this, "failed", Toast.LENGTH_LONG);
            }
        });
    }

}
