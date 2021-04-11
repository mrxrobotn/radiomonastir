package com.example.radiomonastir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void ajoutemembre(View view) {
        EditText champ_nom = findViewById(R.id.editTextTextPersonName);
        EditText champ_email = findViewById(R.id.editTextTextEmailAddress2);
        EditText champ_password = findViewById(R.id.editTextTextPassword2);
        EditText champ_confirmpassword = findViewById(R.id.editTextTextPassword3);

        if(champ_nom.getText().toString().isEmpty()){
            Toast.makeText(SignUpActivity.this,"Ecrire Votre nom et prénom",Toast.LENGTH_LONG).show();
        }
        else if (champ_email.getText().toString().isEmpty()){
            Toast.makeText(SignUpActivity.this,"Le champ email est vide",Toast.LENGTH_LONG).show();
        }
        else if (champ_password.getText().toString().isEmpty()){
            Toast.makeText(SignUpActivity.this,"Entrer un mot de passe",Toast.LENGTH_LONG).show();
        }
        else if (champ_confirmpassword.getText().toString().isEmpty()){
            Toast.makeText(SignUpActivity.this,"Champ confirmer est obligatoire",Toast.LENGTH_LONG).show();
        }
        else {
            findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SignUpActivity.this,ListActivity.class);
                    startActivity(intent);
                }
            });
            Toast.makeText(SignUpActivity.this,"Inscription Complète",Toast.LENGTH_LONG).show();
        }
    }

}