package com.example.radiomonastir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MaintenanceTechnicienActivity extends AppCompatActivity {
    ImageView imageButton9, imageButton10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenance_technicien);

        imageButton9 = (ImageView) findViewById(R.id.imageButton9);
        imageButton10 = (ImageView) findViewById(R.id.imageButton10);

        imageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MaintenanceTechnicienActivity.this, PanneActivity.class);
                startActivity(intent);
            }
        });
        imageButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MaintenanceTechnicienActivity.this, DemandeActivity.class);
                startActivity(intent);
            }
        });
    }
}