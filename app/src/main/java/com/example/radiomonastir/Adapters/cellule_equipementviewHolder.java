package com.example.radiomonastir.Adapters;

import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.radiomonastir.R;

public class cellule_equipementviewHolder extends RecyclerView.ViewHolder {
   public TextView textView21;
   public TextView textView22;
   public TextView textView23;
   public ConstraintLayout constraintequipementcellule;
   public cellule_equipementviewHolder(View itemView){
       super(itemView);
       textView21=(TextView)itemView.findViewById(R.id.textView21);
       textView22=(TextView)itemView.findViewById(R.id.textView22);
       textView23=(TextView)itemView.findViewById(R.id.textView23);
       constraintequipementcellule=(ConstraintLayout)itemView.findViewById(R.id.constraintequipementcellule);

   }


}
