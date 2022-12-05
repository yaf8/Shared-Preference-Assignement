package com.example.assigntry;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private ArrayList<StudentModal> StudentModalArrayList;
    private Context context;

    public StudentAdapter(ArrayList<StudentModal> StudentModalArrayList, Context context) {
        this.StudentModalArrayList = StudentModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
        StudentModal modal = StudentModalArrayList.get(position);
        holder.StudentID.setText(modal.getID());
        holder.StudentFullName.setText(modal.getFullName());
    }

    @Override
    public int getItemCount() {
        return StudentModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView StudentID, StudentFullName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            StudentID = itemView.findViewById(R.id.textID);
            StudentFullName = itemView.findViewById(R.id.textName);
        }
    }
}

