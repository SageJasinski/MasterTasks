package com.example.mastertask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Task_Adapter extends RecyclerView.Adapter<Task_Adapter.MyViewHolder> {
    Context context;
    List<Tasks> taskModel;

    public Task_Adapter(List<Tasks> taskModel, Context context){
        this.context = context;
        this.taskModel = taskModel;
    }

    public Task_Adapter( MainActivity mainActivity) {
    }

    @NonNull
    @Override
    public Task_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_items, parent, false);

        return new Task_Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Task_Adapter.MyViewHolder holder, int position) {
        String content = taskModel.get(position).getTitle();
        holder.taskTitle.setText(content);
        holder.taskDescription.setText(taskModel.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return taskModel.size();
    }

    public  static class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView taskTitle;
        TextView taskDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitle = itemView.findViewById(R.id.view_Task_title);
            taskDescription = itemView.findViewById(R.id.Recycler_description);
        }
    }
}
