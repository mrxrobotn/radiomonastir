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
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
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

            mAuth.createUserWithEmailAndPassword(champ_email.getText().toString(), champ_password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(SignInActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



            findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SignInActivity.this, HomeAdminActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

}