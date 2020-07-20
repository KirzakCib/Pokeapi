package com.example.pokeapi;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Holder> {

    private List<DataList> dataBaseModels = new ArrayList<>();
    private Context context;
    private onItemClickListener listener;


    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public interface onItemClickListener{
        void onItemClick(String id);
    }

    public Adapter(Context context,List<DataList> dataBaseModels){
        this.context = context;
        this.dataBaseModels = dataBaseModels;

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_item_holder, parent, false);
        return new Holder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(dataBaseModels.get(position));
        holder.setItemListener(listener);
    }

    @Override
    public int getItemCount() {
        return dataBaseModels.size();
    }

}
