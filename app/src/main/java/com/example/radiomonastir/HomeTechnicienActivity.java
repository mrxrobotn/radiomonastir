package com.example.radiomonastir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class HomeTechnicienActivity extends AppCompatActivity {
    ImageButton imageButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_technicien);
        imageButton4  = (ImageButton)findViewById(R.id.imageButton4);

        Intent intent = new Intent(HomeTechnicienActivity.this, MaintenanceTechnicienActivity.class);
        startActivity(intent);

    }
}