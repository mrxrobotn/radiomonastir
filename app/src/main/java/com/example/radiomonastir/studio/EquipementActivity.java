package com.example.radiomonastir.studio;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.radiomonastir.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class EquipementActivity extends AppCompatActivity {

    Button buttonAddEquipement;
    EditText editTextEquipementName;
    SeekBar seekBarRating;
    TextView textViewRating, textViewStudio;
    ListView listViewEquipement;

    DatabaseReference databaseEquipements;

    List<Equipement> equipements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipement);

        Intent intent = getIntent();

        /*
         * this line is important
         * this time we are not getting the reference of a direct node
         * but inside the node track we are creating a new child with the artist id
         * and inside that node we will store all the tracks with unique ids
         * */
        databaseEquipements = FirebaseDatabase.getInstance().getReference("equipements").child(intent.getStringExtra(MainActivity.STUDIO_ID));

        buttonAddEquipement = (Button) findViewById(R.id.buttonAddEquipement);
        editTextEquipementName = (EditText) findViewById(R.id.editTextEquipementName);
        seekBarRating = (SeekBar) findViewById(R.id.seekBarRating);
        textViewRating = (TextView) findViewById(R.id.textViewRating);
        textViewStudio = (TextView) findViewById(R.id.textViewStudio);
        listViewEquipement = (ListView) findViewById(R.id.listViewEquipement);

        equipements = new ArrayList<>();

        textViewStudio.setText(intent.getStringExtra(MainActivity.STUDIO_NAME));

        seekBarRating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewRating.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        buttonAddEquipement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEquipement();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseEquipements.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                equipements.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Equipement equipement = postSnapshot.getValue(Equipement.class);
                    equipements.add(equipement);
                }
                EquipementList equipementListAdapter = new EquipementList(EquipementActivity.this, equipements);
                listViewEquipement.setAdapter(equipementListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveEquipement() {
        String equipementName = editTextEquipementName.getText().toString().trim();
        int rating = seekBarRating.getProgress();
        if (!TextUtils.isEmpty(equipementName)) {
            String id  = databaseEquipements.push().getKey();
            Equipement equipement = new Equipement(id, equipementName, rating);
            databaseEquipements.child(id).setValue(equipement);
            Toast.makeText(this, "Equipement sauvegarder", Toast.LENGTH_LONG).show();
            editTextEquipementName.setText("");
        } else {
            Toast.makeText(this, "Please enter track name", Toast.LENGTH_LONG).show();
        }
    }
}