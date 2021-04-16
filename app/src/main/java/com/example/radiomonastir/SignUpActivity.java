package com.example.radiomonastir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
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
        else if (champ_confirmpassword.getText().toString().equals(champ_password.getText().toString())){
            Toast.makeText(SignUpActivity.this,"mot de passe ne correspond pas",Toast.LENGTH_LONG).show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(champ_email.getText().toString(), champ_password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(SignUpActivity.this, HomeAdminActivity.class);
                                startActivity(intent);
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();

                            }
                            else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(SignUpActivity.this, "Authentication failed."+task.getException(),
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });

        }
    }

}