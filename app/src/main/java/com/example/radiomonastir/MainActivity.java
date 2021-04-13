package com.example.radiomonastir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void identification(View view) {
        EditText champ_email = findViewById(R.id.editTextTextEmailAddress);
        EditText champ_password = findViewById(R.id.editTextTextPassword);

        if (champ_email.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this,"Inserer votre email",Toast.LENGTH_LONG).show();
        }
        else if (champ_password.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this,"Inserer votre mot de passe",Toast.LENGTH_LONG).show();
        }
        else {
            findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}