package com.example.radiomonastir.studio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.radiomonastir.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class StudioActivity extends AppCompatActivity {

    EditText editTextNumber;
    Spinner spinnerName;
    Button buttonAddStudio;
    ListView listViewStudios;

    //a list to store all the artist from firebase database
    List<Studio> studios;

    //our database reference object
    DatabaseReference databaseStudios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio);

        //getting the reference of artists node
        databaseStudios = FirebaseDatabase.getInstance().getReference("studios");

        //getting views
        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        spinnerName = (Spinner) findViewById(R.id.spinnerName);
        listViewStudios = (ListView) findViewById(R.id.listViewStudios);
        buttonAddStudio = (Button) findViewById(R.id.buttonAddStudio);

        //list to store artists
        studios = new ArrayList<>();

        //adding an onclicklistener to button
        buttonAddStudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addArtist()
                //the method is defined below
                //this method is actually performing the write operation
                addStudio();
            }
        });

    }

    private void addStudio() {
        //getting the values to save
        String number = editTextNumber.getText().toString().trim();
        String name = spinnerName.getSelectedItem().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(number)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseStudios.push().getKey();

            //creating an Artist Object
            Studio studio = new Studio(number, name);

            //Saving the Artist
            databaseStudios.child(id).setValue(studio);

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
