package com.example.radiomonastir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void identification(View view) {
        EditText champ_email = findViewById(R.id.editTextTextEmailAddress);
        EditText champ_password = findViewById(R.id.editTextTextPassword);

        if (champ_email.getText().toString().isEmpty()){
            Toast.makeText(SignInActivity.this,"Inserer votre email",Toast.LENGTH_LONG).show();
        }
        else if (champ_password.getText().toString().isEmpty()){
            Toast.makeText(SignInActivity.this,"Inserer votre mot de passe",Toast.LENGTH_LONG).show();
        }
        else {
            findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}