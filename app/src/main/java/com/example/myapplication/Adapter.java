package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<ModelList> list;
    Context context;

    public Adapter(List<ModelList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        ModelList list1 = list.get(position);
        holder.txt1.setText(list1.getUserId());
        holder.txt2.setText(list1.getId());
        holder.txt3.setText(list1.getBody());
        holder.txt4.setText(list1.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt1,txt2,txt3,txt4;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.textView);
            txt2=itemView.findViewById(R.id.textView2);
            txt3=itemView.findViewById(R.id.textView3);
            txt4=itemView.findViewById(R.id.textView4);

        }
    }
}
