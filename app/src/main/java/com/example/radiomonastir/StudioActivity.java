package com.example.radiomonastir;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.radiomonastir.Models.Studio;
import com.example.radiomonastir.Adapters.StudioList;
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
    ListView listViewStudios;

    //a list to store all the artist from firebase database
    List<Studio> studios;

    //our database reference object
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio);

        //getting the reference of artists node
        myRef = FirebaseDatabase.getInstance().getReference("studios");

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

        //adding an onitemlongclicklistener to listView items
        listViewStudios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Studio studio = studios.get(i);
                showUpdateDeleteDialog(studio.getStudioId(), studio.getStudioNumber());
                return true;
            }
        });
    }


    /*--------------------------------------------methods-----------------------------------------------*/

    private void showUpdateDeleteDialog(final String studioId, String studioNumber) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.studio_dialogue, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextNumber = (EditText) dialogView.findViewById(R.id.editTextNumber);
        final Spinner spinnerName = (Spinner) dialogView.findViewById(R.id.spinnerName);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateStudio);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteStudio);

        dialogBuilder.setTitle(studioNumber);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = editTextNumber.getText().toString().trim();
                String name = spinnerName.getSelectedItem().toString();
                if (!TextUtils.isEmpty(number)) {
                    updateStudio(studioId, number, name);
                    b.dismiss();
                }
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteStudio(studioId);
                b.dismiss();
            }
        });
    }

    private boolean updateStudio(String id, String number, String name) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("studios").child(id);

        //updating artist
        Studio studio = new Studio(id, number, name);
        dR.setValue(studio);
        Toast.makeText(getApplicationContext(), "Studio modifié avec succée", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteStudio(String id) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("studios").child(id);

        //removing artist
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Studio supprimé avec succée", Toast.LENGTH_LONG).show();
        return true;
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

                //clearing the previous artist list
                studios.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Studio studio = postSnapshot.getValue(Studio.class);
                    //adding artist to the list
                    studios.add(studio);
                }

                //creating adapter
                StudioList studioAdapter = new StudioList(StudioActivity.this, studios);
                //attaching adapter to the listview
                listViewStudios.setAdapter(studioAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    /*
     * This method is saving a new artist to the
     * Firebase Realtime Database
     * */
    private void addStudio() {
        //getting the values to save
        String number = editTextNumber.getText().toString().trim();
        String name = spinnerName.getSelectedItem().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(number)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = myRef.push().getKey();

            //creating an Artist Object
            Studio studio = new Studio(id, number, name);

            //Saving the Artist
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
