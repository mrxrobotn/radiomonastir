package com.example.radiomonastir.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.Models.Studio;
import com.example.radiomonastir.R;
import com.example.radiomonastir.equipementStudioActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class StudioAdapter extends RecyclerView.Adapter<StudioViewHolder> {
    private Context context;
    private List<Studio> studios;
    public StudioAdapter (Context context,List<Studio> studios){
        this.context=context;
        this.studios=studios;
    }

    @NonNull
    @Override
    public StudioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_studio_list,viewGroup,false);
        return new StudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudioViewHolder studioViewHolder, final  int i) {
        Studio studio = studios.get(i);
        studioViewHolder.textViewNumber.setText(studio.getStudioNumber());
        studioViewHolder.textViewName.setText(studio.getStudioName());
        studioViewHolder.listeitem.setOnClickListener((view) -> {
            Intent intent = new Intent(context, equipementStudioActivity.class);
            intent.putExtra("id", studio.getStudioId());
            intent.putExtra("number", studio.getStudioNumber());
            context.startActivity(intent);
        });
        studioViewHolder.listeitem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showUpdateDeleteDialog(studio.getStudioId(),studio.getStudioNumber());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return studios.size();
    }



    private void showUpdateDeleteDialog(String studioId, String studioNumber) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        Toast.makeText(context, "Studio modifié avec succée", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteStudio(String id) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("studios").child(id);

        //removing artist
        dR.removeValue();
        Toast.makeText(context, "Studio supprimé avec succée", Toast.LENGTH_LONG).show();
        return true;
    }
}
