package com.example.radiomonastir.studio;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.radiomonastir.R;

import java.util.List;

public class EquipementList extends ArrayAdapter<Equipement> {
    private Activity context;
    List<Equipement> equipements;

    public EquipementList(Activity context, List<Equipement> equipements) {
        super(context, R.layout.layout_studio_list, equipements);
        this.context = context;
        this.equipements = equipements;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_studio_list, null, true);

        TextView textViewNumStudio = (TextView) listViewItem.findViewById(R.id.textViewNumStudio);
        TextView textViewNomStudio = (TextView) listViewItem.findViewById(R.id.textViewNomStudio);

        Equipement equipement = equipements.get(position);
        textViewNumStudio.setText(equipement.getEquipementName());
        textViewNomStudio.setText(String.valueOf(equipement.getRating()));

        return listViewItem;
    }
}