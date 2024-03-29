package com.example.radiomonastir;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.radiomonastir.Adapters.CelluleAdapter;
import com.example.radiomonastir.Models.Cellule;
import com.example.radiomonastir.Models.Equipement;
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
    String ch = "";//studios  magasin  serveur  cellule
    String idParent = "";
    String id,nom,type,numserie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoyer);
        spinnerServeur=findViewById(R.id.spinner3);
        spinnerCellule=findViewById(R.id.spinner4);
        spinnerStudio=findViewById(R.id.spinner);
        checkBox=findViewById(R.id.checkBox);
        checkBox2=findViewById(R.id.checkBox2);
        button3=findViewById(R.id.button3);
        button7=findViewById(R.id.button7);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRefCell= database.getReference("cellules");

        myRefStudio= database.getReference("studios");
        myRefserveur= database.getReference("serveurs");

        nom=getIntent().getStringExtra("nom");
        type=getIntent().getStringExtra("type");
        numserie=getIntent().getStringExtra("numserie");
        id=getIntent().getStringExtra("id");



        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    ch = "magasin";

                }
                else if (checkBox2.isChecked()){
                    ch = "reformer";
                }
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef;
                myRef= database.getReference("equipements");
                Equipement equipement=  new Equipement(id,nom,type,numserie,ch,idParent);
                myRef.child(id).setValue(equipement);
               Toast.makeText(ActivityEnvoyer.this, "equipement déplacer avec succée", Toast.LENGTH_SHORT).show();

            }
        });

            myRefCell.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    celluleList.clear();
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        Cellule cellule = postSnapshot.getValue(Cellule.class);
                        celluleList.add(cellule);

                    }


                    //spinner
                    //Creating the ArrayAdapter instance having the country list
                    ArrayAdapter<Cellule> aa = new ArrayAdapter<Cellule>(ActivityEnvoyer.this,android.R.layout.simple_spinner_item,celluleList);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Setting the ArrayAdapter data on the Spinner
                    spinnerCellule.setAdapter(aa);

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
                    //Creating the ArrayAdapter instance having the country list
                    ArrayAdapter<Studio> aa = new ArrayAdapter<Studio>(ActivityEnvoyer.this,android.R.layout.simple_spinner_item,studioList);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Setting the ArrayAdapter data on the Spinner
                    spinnerStudio.setAdapter(aa);

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
                    ArrayAdapter<Serveur> aa = new ArrayAdapter<Serveur>(ActivityEnvoyer.this,android.R.layout.simple_spinner_item,serveurList);
                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    // Setting the ArrayAdapter data on the Spinner
                    spinnerServeur.setAdapter(aa);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ActivityEnvoyer.this, "failed", Toast.LENGTH_LONG);
                }
            });
        spinnerCellule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ch  = "cellule";
                idParent  = celluleList.get(i).getCelluleId();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerStudio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ch  = "studios";
                idParent  = studioList.get(i).getStudioId();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerServeur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ch  = "serveur";
                idParent  = serveurList.get(i).getServeurId();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


}
