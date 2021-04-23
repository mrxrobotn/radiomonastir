package com.example.radiomonastir.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.radiomonastir.Models.Studio;
import com.example.radiomonastir.R;
import com.example.radiomonastir.StudioEquipementActivity;

import java.util.List;

public class StudioList extends ArrayAdapter<Studio> {
    private Activity context;
    List<Studio> studios;

    public StudioList(Activity context, List<Studio> studios) {
        super(context, R.layout.layout_studio_list, studios);
        this.context = context;
        this.studios = studios;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_studio_list, null, true);

        TextView textViewNumber = (TextView) listViewItem.findViewById(R.id.textViewNumber);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);

        Studio studio = studios.get(position);
        textViewNumber.setText(studio.getStudioNumber());
        textViewName.setText(studio.getStudioName());

        return listViewItem;
    }
}