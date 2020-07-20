package com.example.pokeapi;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class Holder extends RecyclerView.ViewHolder {

    private TextView text_name;
    private TextView text_hp;
    private TextView text_attack;
    private TextView text_defense;
    private ImageView imageView;
    private Context context;
    private String id;

    public Holder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        text_name = itemView.findViewById(R.id.text_name);
        text_attack = itemView.findViewById(R.id.text_attack);
        text_hp = itemView.findViewById(R.id.text_hp);
        text_defense = itemView.findViewById(R.id.text_defense);
        imageView = itemView.findViewById(R.id.image);
    }


public void bind(DataList dataList) {

    text_name.setText(dataList.getName());
    text_hp.setText("hp: " + dataList.getHp());
    text_attack.setText("attack: " + dataList.getAttack());
    text_defense.setText("defense: " + dataList.getDefense());
    Picasso.with(context).load(dataList.getSprites()).error(R.drawable.pokemon).into(imageView);
    id = dataList.getName();
}

    public void setItemListener(Adapter.onItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(id);
            }
        });
    }
}
