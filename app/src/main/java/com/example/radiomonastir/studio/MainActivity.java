package com.example.radiomonastir.studio;

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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.radiomonastir.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String STUDIO_NAME = "radiomonastir-55b1f-default-rtdb.firebaseio.com.studioname";
    public static final String STUDIO_ID = "radiomonastir-55b1f-default-rtdb.firebaseio.com.studioid";


    EditText editTextNumStudio;
    Spinner spinnerName;
    Button buttonAddStudio;
    ListView listViewStudios;

    //a list to store all the studios from firebase database
    List<Studio> studios;

    //our database reference object
    DatabaseReference databaseStudios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_studio);

        //getting the reference of studios node
        databaseStudios = FirebaseDatabase.getInstance().getReference("studios");

        //getting views
        editTextNumStudio = (EditText) findViewById(R.id.editTextEquipementName);
        spinnerName = (Spinner) findViewById(R.id.spinnerName);
        listViewStudios = (ListView) findViewById(R.id.listViewStudios);

        buttonAddStudio = (Button) findViewById(R.id.buttonAddStudio);

        //list to store studios
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

        //attaching listener to listview
        listViewStudios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected artist
                Studio studio = studios.get(i);

                //creating an intent
                Intent intent = new Intent(getApplicationContext(), EquipementActivity.class);

                //putting artist name and id to intent
                intent.putExtra(STUDIO_ID, studio.getStudioId());
                intent.putExtra(STUDIO_NAME, studio.getStudioName());

                //starting the activity with intent
                startActivity(intent);
            }
        });


        listViewStudios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Studio studio = studios.get(i);
                showUpdateDeleteDialog(studio.getStudioId(), studio.getStudioNumber());
                return true;
            }
        });


    }

    private void showUpdateDeleteDialog(final String studiosId, String studioNumber) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialogue, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextNumStudio = (EditText) dialogView.findViewById(R.id.editTextEquipementName);
        final Spinner spinnerName = (Spinner) dialogView.findViewById(R.id.spinnerName);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateStudio);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteStudio);

        dialogBuilder.setTitle(studioNumber);
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numstudio = editTextNumStudio.getText().toString().trim();
                String nomstudio = spinnerName.getSelectedItem().toString();
                if (!TextUtils.isEmpty(numstudio)) {
                    updateStudio(studiosId, numstudio, nomstudio);
                    b.dismiss();
                }
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteStudio(studiosId);
                b.dismiss();
            }
        });
    }

    private boolean updateStudio(String id, String numstudio, String nomstudio) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("studios").child(id);

        //updating artist
        Studio studio = new Studio(id, numstudio, nomstudio);
        dR.setValue(studio);
        Toast.makeText(getApplicationContext(), "Studio Modifié", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteStudio(String id) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("studios").child(id);

        //removing artist
        dR.removeValue();

        //getting the tracks reference for the specified artist
        //DatabaseReference drTracks = FirebaseDatabase.getInstance().getReference("tracks").child(id);

        //removing all tracks
       // drTracks.removeValue();
        Toast.makeText(getApplicationContext(), "Studio Supprimé", Toast.LENGTH_LONG).show();

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseStudios.addValueEventListener(new ValueEventListener() {
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
                StudioList studioAdapter = new StudioList(MainActivity.this, studios);
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
        String num_studio = editTextNumStudio.getText().toString().trim();
        String nom_studio = spinnerName.getSelectedItem().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(num_studio)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseStudios.push().getKey();

            //creating an Artist Object
            Studio studio = new Studio(id, num_studio, nom_studio);

            //Saving the Artist
            databaseStudios.child(id).setValue(studio);

            //setting edittext to blank again
            editTextNumStudio.setText("");

            //displaying a success toast
            Toast.makeText(this, "Studio Ajouté", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Entrer un numero", Toast.LENGTH_LONG).show();
        }
    }
}