package com.example.radiomonastir.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.radiomonastir.Models.Users;
import com.example.radiomonastir.R;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersViewHolder> {

    private Context context;
    private List<Users> usersList;

    public UsersAdapter (Context context,List<Users> users){
        this.context=context;
        this.usersList=users;

    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_gestion_technicien_list,viewGroup,false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder usersViewHolder, int i) {
        Users users = usersList.get(i);
        usersViewHolder.editTextTextPersonName.setText(users.getUserName());
        usersViewHolder.editTextTextEmailAddress2.setText(users.getUserEmail());
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
