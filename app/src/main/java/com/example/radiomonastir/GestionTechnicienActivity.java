package com.example.radiomonastir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class GestionTechnicienActivity extends AppCompatActivity {
    ImageView imageView15;
    RecyclerView recyclerView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesion_technicien);
        imageView15 = (ImageView) findViewById(R.id.imageView15);
        recyclerView3 = (RecyclerView) findViewById(R.id.recyclerView3);

        imageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GestionTechnicienActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

}