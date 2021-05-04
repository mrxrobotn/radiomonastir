package com.example.radiomonastir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.radiomonastir.Adapters.UsersAdapter;
import com.example.radiomonastir.Models.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GestionTechnicienActivity extends AppCompatActivity {
    ImageButton imageButton5;
    RecyclerView recyclerView3;
    DatabaseReference myRef;
    List<Users> usersList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesion_technicien);
        imageButton5 = (ImageButton) findViewById(R.id.imageButton5);
        recyclerView3 = (RecyclerView) findViewById(R.id.recyclerView3);

        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionTechnicienActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView3.setLayoutManager(linearLayoutManager);
        recyclerView3.setHasFixedSize(true);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef= database.getReference("users");
    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Users users = postSnapshot.getValue(Users.class);
                    usersList.add(users);
                }
                UsersAdapter usersAdapter = new UsersAdapter(GestionTechnicienActivity.this, usersList);
                recyclerView3.setAdapter(usersAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GestionTechnicienActivity.this, "failed", Toast.LENGTH_LONG);
            }
        });
    }
}