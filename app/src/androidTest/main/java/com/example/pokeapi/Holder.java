package com.example.pokeapi;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {

    private TextView text_name;

    public Holder(@NonNull View itemView) {
        super(itemView);

        text_name = itemView.findViewById(R.id.text_name);

    }

    public void bind(DataBaseModel dataList) {

        text_name.setText(dataList.getName());

    }
}
