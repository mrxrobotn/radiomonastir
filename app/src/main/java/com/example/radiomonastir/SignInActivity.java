package com.example.radiomonastir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            String userEmail= mAuth.getCurrentUser().getEmail();
            if (userEmail.equals("charfi13@yahoo.fr")){
                startActivity(new Intent(SignInActivity.this, HomeAdminActivity.class));
                finish();
            }
            else {
                startActivity(new Intent(SignInActivity.this, HomeTechnicienActivity.class));
                finish();
            }
        }
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
                mAuth.signInWithEmailAndPassword(champ_email.getText().toString(), champ_password.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    if (champ_email.getText().toString().equals("charfi13@yahoo.fr"))
                                    {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent intent = new Intent(SignInActivity.this, HomeAdminActivity.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Intent intent = new Intent(SignInActivity.this, HomeTechnicienActivity.class);
                                        startActivity(intent);
                                    }
                                }
                                else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(SignInActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        }

    public void forgetpassword(View view) {
        findViewById(R.id.textViewForgetPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}