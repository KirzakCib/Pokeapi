package com.example.pokeapi;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.squareup.picasso.Picasso;


public class FragmentPokemonInfo extends Fragment {

    TextView info_height;
    TextView info_weight;
    TextView info_type;
    ImageView info_image;
    TextView info_attack;
    TextView info_defense;
    TextView info_hp;
    TextView info_specialDefense;
    TextView info_specialAttack;
    TextView info_speed;
    String name;

    public String setId(String name){
        this.name = name;
        return null;
    }

    public interface OnClose {
        void onClose();
    }

    OnClose onClose;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onClose = (OnClose) activity;
    }

    public static FragmentPokemonInfo newInstance() {
        return new FragmentPokemonInfo();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_info, container, false);
        DataBaseHelper db = Room.databaseBuilder(getContext(),DataBaseHelper.class,"database").allowMainThreadQueries().build();
        DataBaseDao dataBaseDao = db.getDataBaseDao();
        DataBaseDaoInfoPokemon data = db.getDataBaseInfoPokemonDao();
        info_image = view.findViewById(R.id.info_image);
        info_height = view.findViewById(R.id.info_height);
        info_weight = view.findViewById(R.id.info_weight);
        info_type = view.findViewById(R.id.info_type);
        info_attack = view.findViewById(R.id.info_attack);
        info_defense = view.findViewById(R.id.info_defense);
        info_hp = view.findViewById(R.id.info_hp);
        info_specialAttack = view.findViewById(R.id.info_specialAttack);
        info_specialDefense = view.findViewById(R.id.info_specialDefense);
        info_speed = view.findViewById(R.id.info_speed);
        Integer id = null;
        for(int q = 0; q < data.getAllData().size(); q++){
            if(data.getAllData().get(q).getName().equals(name)){
                id = q;
            }
        }
            String[] ff = new String[data.getAllData().get(id).getType().length()];
        ff = data.getAllData().get(id).getType().replace("[","").replace("]","").split("\\,\\s");
                for(int y =0; y < ff.length; y++){
                    info_type.append(ff[y]+"\n");
                }
        Picasso.with(getActivity()).load(data.getAllData().get(id).getSprites()).error(R.drawable.pokemon).into(info_image);
        info_height.setText(data.getAllData().get(id).getHeight().toString());
        info_weight.setText(data.getAllData().get(id).getWeight().toString());
        info_attack.setText(data.getAllData().get(id).getAttack().toString());
        info_defense.setText(data.getAllData().get(id).getDefense().toString());
        info_hp.setText(data.getAllData().get(id).getHp().toString());
        info_specialAttack.setText(data.getAllData().get(id).getSpecialAttack().toString());
        info_specialDefense.setText(data.getAllData().get(id).getSpecialDefense().toString());
        info_speed.setText(data.getAllData().get(id).getSpeed().toString());
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onClose.onClose();
    }
}
