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

    private List<DataBaseModel> dataBaseModels = new ArrayList<>();
    private Context context;
    //private List<DataList> dataLists = new ArrayList<>();
    List<DataList> dataLists;

    public Adapter(Context context,List<DataBaseModel> dataBaseModels){
        this.context = context;
        this.dataBaseModels = dataBaseModels;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_item_holder, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(dataBaseModels.get(position));
    }

    @Override
    public int getItemCount() {
        return dataBaseModels.size();
    }

    public  void addDate (List<DataList> dataLists){
        //dataLists.addAll(dataLists);//data
        this.dataBaseModels = dataBaseModels;
        notifyDataSetChanged();
    }
}
