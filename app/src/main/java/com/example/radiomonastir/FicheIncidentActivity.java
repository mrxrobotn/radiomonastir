package com.example.radiomonastir;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FicheIncidentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiche_incident);

        TextView tv1 = (TextView) findViewById(R.id.textView18);
        TextView tv2 = (TextView) findViewById(R.id.textView19);
        TextView tv3 = (TextView) findViewById(R.id.textView20);

        Bundle bundle=getIntent().getExtras();
        String s1=bundle.getString("nom");
        tv1.setText(s1);
        String s2=bundle.getString("type");
        tv2.setText(s2);
        String s3=bundle.getString("numserie");
        tv3.setText(s3);

    }
}