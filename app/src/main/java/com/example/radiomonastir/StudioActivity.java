package com.example.radiomonastir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.radiomonastir.Adapters.StudioAdapter;
import com.example.radiomonastir.Models.Studio;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudioActivity extends AppCompatActivity {


    EditText editTextNumber;
    Spinner spinnerName;
    Button buttonAddStudio;
    RecyclerView recyclerViewStudios;

    //a list to store all the studio from firebase database
    List<Studio> studios =new ArrayList<>();

    //our database reference object
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio);

        //getting views
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        spinnerName = (Spinner) findViewById(R.id.spinnerName);
        recyclerViewStudios = (RecyclerView) findViewById(R.id.recyclerViewStudios);
        buttonAddStudio = (Button) findViewById(R.id.buttonAddStudio);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewStudios.setLayoutManager(linearLayoutManager);
        recyclerViewStudios.setHasFixedSize(true);

        //getting the reference of studios node
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef= database.getReference("studios");


        //adding an onclicklistener to button
        buttonAddStudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addStudio()
                //the method is defined below
                //this method is actually performing the write operation
                addStudio();
            }
        });
    }


    /*
     * This method show the database data on the listView
     * */
    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous studio list
                studios.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting studio
                    Studio studio = postSnapshot.getValue(Studio.class);
                    //adding studio to the list
                    studios.add(studio);
                }

                //creating adapter
                StudioAdapter studioAdapter = new StudioAdapter(StudioActivity.this, studios);
                //attaching adapter to the listview
                recyclerViewStudios.setAdapter(studioAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    /*
     * This method is saving a new studio to the
     * Firebase Realtime Database
     * */
    private void addStudio() {
        //getting the values to save
        String number = editTextNumber.getText().toString().trim();
        String name = spinnerName.getSelectedItem().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(number)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Studio
            String id = myRef.push().getKey();

            //creating a studio Object
            Studio studio = new Studio(id, number, name);

            //Saving the studio
            myRef.child(id).setValue(studio);

            //setting edittext to blank again
            editTextNumber.setText("");

            //displaying a success toast
            Toast.makeText(this, "Studio ajouté", Toast.LENGTH_LONG).show();
        }
        else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Entrer un numero", Toast.LENGTH_LONG).show();
        }
    }
}
