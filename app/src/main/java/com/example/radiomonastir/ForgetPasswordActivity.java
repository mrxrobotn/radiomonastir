package com.example.radiomonastir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {
    TextView editTextTextEmailAddress3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

      editTextTextEmailAddress3=findViewById(R.id.editTextTextEmailAddress3);
      button4=findViewById(R.id.button4);
      button4.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String email=editTextTextEmailAddress3.getText().toString().trim();
              if (email.isEmpty()){
                  Toast.makeText(ForgetPasswordActivity.this,"Email introuvable",Toast.LENGTH_LONG).show();
              }
              else {
                  FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {
                          if (task.isSuccessful()){
                              Toast.makeText(ForgetPasswordActivity.this,"Email envoyer avec succée",Toast.LENGTH_LONG).show();
                              finish();
                          }
                          else {
                              Toast.makeText(ForgetPasswordActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                          }
                      }
                  });
              }
          }
      });

    }
}