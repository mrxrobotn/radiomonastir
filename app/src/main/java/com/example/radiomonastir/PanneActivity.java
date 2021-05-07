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

import com.example.radiomonastir.Adapters.PanneAdapter;
import com.example.radiomonastir.Models.Panne;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PanneActivity extends AppCompatActivity {
    EditText editTextTextMultiLine;
    Button button10;
    RecyclerView Rv_panne;
    List<Panne> panneList =new ArrayList<>();
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panne);

        editTextTextMultiLine=(EditText)findViewById(R.id.editTextTextMultiLine);
        button10=(Button)findViewById(R.id.button10);
        Rv_panne=(RecyclerView)findViewById(R.id.Rv_panne);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        Rv_panne.setLayoutManager(linearLayoutManager);
        Rv_panne.setHasFixedSize(true);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef= database.getReference("pannes");

        button10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String panne_desc = editTextTextMultiLine.getText().toString().trim();
                if (!TextUtils.isEmpty(panne_desc)){
                    String id = myRef.push().getKey();
                    Panne panne= new Panne(id, panne_desc);
                    myRef.child(id).setValue(panne);
                    editTextTextMultiLine.setText("");
                    Toast.makeText(PanneActivity.this,"Panne Envoyer",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(PanneActivity.this,"Erreur!",Toast.LENGTH_LONG).show();
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
                panneList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Panne panne = postSnapshot.getValue(Panne.class);
                    panneList.add(panne);
                }
                PanneAdapter panneAdapter = new PanneAdapter(PanneActivity.this, panneList);
                Rv_panne.setAdapter(panneAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(PanneActivity.this, "failed", Toast.LENGTH_LONG);
            }
        });
    }
}