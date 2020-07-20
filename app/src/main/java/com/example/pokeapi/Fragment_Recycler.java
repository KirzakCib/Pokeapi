package com.example.pokeapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_Recycler extends Fragment {
    static final String BASE_URL = "https://pokeapi.co/api/v2/";
    List<DataList> dataLists =  new ArrayList<>();;
    RecyclerView recyclerView;
    Adapter adapter;
    private Adapter.onItemClickListener onItemClickListener;
    Integer count = 30;
    Button button;
    CheckBox check_attack;
    CheckBox check_defense;
    CheckBox check_hp;
    boolean attack = false;
    boolean defense = false;
    boolean hp = false;

    DataBaseHelper db ;
    DataBaseDao dataBaseDao ;
    DataBaseModel dataBaseModel;

    public static Fragment_Recycler newInstance() {
        return new Fragment_Recycler();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Adapter.onItemClickListener){
            onItemClickListener = (Adapter.onItemClickListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler);
        button = view.findViewById(R.id.pokemon_change);
        check_attack = view.findViewById(R.id.check_attack);
        check_defense = view.findViewById(R.id.check_defense);
        check_hp = view.findViewById(R.id.check_hp);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new Adapter(getActivity(), dataLists);
        recyclerView.setAdapter(adapter);

        adapter.setListener(onItemClickListener);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        ParserJSON parserJSON = retrofit.create(ParserJSON.class);

        Call<Parser> call = parserJSON.PokemonParser(0);

        call.enqueue(new Callback<Parser>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Parser> call, Response<Parser> response) {
                if (response.isSuccessful()) {
                    db = Room.databaseBuilder(getActivity(), DataBaseHelper.class, "database").allowMainThreadQueries().build();
                    dataBaseDao = db.getDataBaseDao();
                    dataBaseModel = new DataBaseModel();

                    Parser parser = response.body();

                    for (Pokemon pokemon : parser.getResults()) {
                        dataBaseModel.setName(pokemon.getName());
                        dataBaseModel.setUrl(pokemon.getUrl());
                        String dd = new String();
                        dd = pokemon.getName();
                        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create()).build();

                        ParserJSON parserJSON = retrofit.create(ParserJSON.class);
                        Call<Parser_Info> call_info_pokemon = parserJSON.InfoParser(pokemon.getUrl().substring(25));
                        //      try {

                        String finalDd = dd;
                        call_info_pokemon.enqueue(new Callback<Parser_Info>() {

                            @Override
                            public void onResponse(Call<Parser_Info> call, Response<Parser_Info> response) {
                                DataBaseHelper data = Room.databaseBuilder(getActivity(), DataBaseHelper.class, "database").allowMainThreadQueries().build();
                                DataBaseDaoInfoPokemon dao = db.getDataBaseInfoPokemonDao();
                                DataBaseInfoPokemon dataBaseInfoPokemon = new DataBaseInfoPokemon();
                                Parser_Info parser = response.body();
                                if (response.isSuccessful()) {
                                    dataBaseInfoPokemon.setHeight(parser.getHeight());
                                    dataBaseInfoPokemon.setWeight(parser.getWeight());
                                    for (Stat stat : parser.getStats()) {
                                        if (stat.getStat().getName().equals("hp"))
                                            dataBaseInfoPokemon.setHp(stat.getBaseStat());
                                        if (stat.getStat().getName().equals("attack"))
                                            dataBaseInfoPokemon.setAttack(stat.getBaseStat());
                                        if (stat.getStat().getName().equals("defense"))
                                            dataBaseInfoPokemon.setDefense(stat.getBaseStat());
                                        if (stat.getStat().getName().equals("special-attack"))
                                            dataBaseInfoPokemon.setSpecialAttack(stat.getBaseStat());
                                        if (stat.getStat().getName().equals("special-defense"))
                                            dataBaseInfoPokemon.setSpecialDefense(stat.getBaseStat());
                                        if (stat.getStat().getName().equals("speed"))
                                            dataBaseInfoPokemon.setSpeed(stat.getBaseStat());
                                    }

                                    List type_mass = new ArrayList();
                                    for (Type type : parser.getTypes()) {
                                        type_mass.add(type.getType().getName());
                                    }
                                    dataBaseInfoPokemon.setType(type_mass.toString());

                                    dataBaseInfoPokemon.setSprites(parser.getSprites().getFrontDefault());
                                   dataBaseInfoPokemon.setName(finalDd);
                                }
                                dataLists.add(new DataList(dataBaseInfoPokemon.getName(), dataBaseInfoPokemon.getSprites(), dataBaseInfoPokemon.getType(), dataBaseInfoPokemon.getHeight(), dataBaseInfoPokemon.getWeight(), dataBaseInfoPokemon.getHp(), dataBaseInfoPokemon.getSpeed(), dataBaseInfoPokemon.getSpecialDefense(), dataBaseInfoPokemon.getSpecialAttack(), dataBaseInfoPokemon.getDefense(), dataBaseInfoPokemon.getAttack()));
                                dao.insert(dataBaseInfoPokemon);
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<Parser_Info> call, Throwable t) {
                                Log.e("Error", t.toString());

                            }

                        });
                        dataBaseDao.insert(dataBaseModel);
                    }
                }

            }

            @Override
            public void onFailure(Call<Parser> call, Throwable t) {
                Log.e("Error", t.toString());

                Toast.makeText(getActivity(), "Ошибка загрузки, пожалуйста проверьте ваше подключение к сети..." + "\n" + "С уважением.", Toast.LENGTH_SHORT).show();
                DataBaseHelper dbd = Room.databaseBuilder(getContext(), DataBaseHelper.class, "database").allowMainThreadQueries().build();
                DataBaseDaoInfoPokemon data = dbd.getDataBaseInfoPokemonDao();
                for (int q = 0; q < data.getAllData().size(); q++) {
                    dataLists.add(new DataList(data.getAllData().get(q).getName(), data.getAllData().get(q).getSprites(), data.getAllData().get(q).getType(), data.getAllData().get(q).getHeight(), data.getAllData().get(q).getWeight(), data.getAllData().get(q).getHp(), data.getAllData().get(q).getSpeed(), data.getAllData().get(q).getSpecialDefense(), data.getAllData().get(q).getSpecialAttack(), data.getAllData().get(q).getDefense(), data.getAllData().get(q).getAttack()));
                    adapter.notifyDataSetChanged();
                }

            }

        });

        final LinearLayoutManager linearLayoutManager
                = (LinearLayoutManager) recyclerView.getLayoutManager();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                if ((linearLayoutManager.getItemCount() == (linearLayoutManager.findLastVisibleItemPosition() + 1)) && (count ==(linearLayoutManager.findLastVisibleItemPosition() + 1)) && count == 935) {
                    Toast.makeText(getContext(), "Загружены все данные...", Toast.LENGTH_SHORT).show();
                }
                    if ((linearLayoutManager.getItemCount() == (linearLayoutManager.findLastVisibleItemPosition() + 1)) && (count ==(linearLayoutManager.findLastVisibleItemPosition() + 1))) {
                    Toast.makeText(getContext(), "Загрузка данных...", Toast.LENGTH_SHORT).show();
                    count += 30;
                        check_attack.setChecked(false);
                        check_defense.setChecked(false);
                        check_hp.setChecked(false);

                    Call<Parser> call_plus = parserJSON.PokemonParser(linearLayoutManager.getItemCount());
                    call_plus.enqueue(new Callback<Parser>() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onResponse(Call<Parser> call, Response<Parser> response) {
                            if (response.isSuccessful()) {
                                db = Room.databaseBuilder(getActivity(), DataBaseHelper.class, "database").allowMainThreadQueries().build();
                                dataBaseDao = db.getDataBaseDao();
                                dataBaseModel = new DataBaseModel();

                                Parser parser = response.body();

                                for (Pokemon pokemon : parser.getResults()) {
                                    dataBaseModel.setName(pokemon.getName());
                                    dataBaseModel.setUrl(pokemon.getUrl());
                                    String dd = new String();
                                    dd = pokemon.getName();
                                    Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                                            .addConverterFactory(GsonConverterFactory.create()).build();

                                    ParserJSON parserJSON = retrofit.create(ParserJSON.class);
                                    Call<Parser_Info> call_info_pokemon = parserJSON.InfoParser(pokemon.getUrl().substring(25));


                                    String finalDd = dd;
                                    call_info_pokemon.enqueue(new Callback<Parser_Info>() {

                                        @Override
                                        public void onResponse(Call<Parser_Info> call, Response<Parser_Info> response) {
                                            DataBaseHelper data = Room.databaseBuilder(getActivity(), DataBaseHelper.class, "database").allowMainThreadQueries().build();
                                            DataBaseDaoInfoPokemon dao = db.getDataBaseInfoPokemonDao();
                                            DataBaseInfoPokemon dataBaseInfoPokemon = new DataBaseInfoPokemon();
                                            Parser_Info parser = response.body();
                                            if (response.isSuccessful()) {
                                                dataBaseInfoPokemon.setHeight(parser.getHeight());
                                                dataBaseInfoPokemon.setWeight(parser.getWeight());
                                                for (Stat stat : parser.getStats()) {
                                                    if (stat.getStat().getName().equals("hp"))
                                                        dataBaseInfoPokemon.setHp(stat.getBaseStat());
                                                    if (stat.getStat().getName().equals("attack"))
                                                        dataBaseInfoPokemon.setAttack(stat.getBaseStat());
                                                    if (stat.getStat().getName().equals("defense"))
                                                        dataBaseInfoPokemon.setDefense(stat.getBaseStat());
                                                    if (stat.getStat().getName().equals("special-attack"))
                                                        dataBaseInfoPokemon.setSpecialAttack(stat.getBaseStat());
                                                    if (stat.getStat().getName().equals("special-defense"))
                                                        dataBaseInfoPokemon.setSpecialDefense(stat.getBaseStat());
                                                    if (stat.getStat().getName().equals("speed"))
                                                        dataBaseInfoPokemon.setSpeed(stat.getBaseStat());
                                                }

                                                List type_mass = new ArrayList();
                                                for (Type type : parser.getTypes()) {
                                                    type_mass.add(type.getType().getName());
                                                }
                                                dataBaseInfoPokemon.setType(type_mass.toString());

                                                dataBaseInfoPokemon.setSprites(parser.getSprites().getFrontDefault());
                                               dataBaseInfoPokemon.setName(finalDd);

                                            }
                                            dataLists.add(new DataList(dataBaseInfoPokemon.getName(), dataBaseInfoPokemon.getSprites(), dataBaseInfoPokemon.getType(), dataBaseInfoPokemon.getHeight(), dataBaseInfoPokemon.getWeight(), dataBaseInfoPokemon.getHp(), dataBaseInfoPokemon.getSpeed(), dataBaseInfoPokemon.getSpecialDefense(), dataBaseInfoPokemon.getSpecialAttack(), dataBaseInfoPokemon.getDefense(), dataBaseInfoPokemon.getAttack()));
                                            dao.insert(dataBaseInfoPokemon);
                                            adapter.notifyDataSetChanged();
                                        }

                                        @Override
                                        public void onFailure(Call<Parser_Info> call, Throwable t) {
                                            Log.e("Error", t.toString());
                                        }

                                    });
                                    dataBaseDao.insert(dataBaseModel);
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<Parser> call, Throwable t) {
                            Log.e("Error", t.toString());

                            Toast.makeText(getActivity(), "Ошибка загрузки, пожалуйста проверьте ваше подключение к сети..." + "\n" + "С уважением.", Toast.LENGTH_SHORT).show();
                            DataBaseHelper dbd = Room.databaseBuilder(getContext(), DataBaseHelper.class, "database").allowMainThreadQueries().build();
                            DataBaseDaoInfoPokemon data = dbd.getDataBaseInfoPokemonDao();
                            for (int q = 0; q < data.getAllData().size(); q++) {
                                dataLists.add(new DataList(data.getAllData().get(q).getName(), data.getAllData().get(q).getSprites(), data.getAllData().get(q).getType(), data.getAllData().get(q).getHeight(), data.getAllData().get(q).getWeight(), data.getAllData().get(q).getHp(), data.getAllData().get(q).getSpeed(), data.getAllData().get(q).getSpecialDefense(), data.getAllData().get(q).getSpecialAttack(), data.getAllData().get(q).getDefense(), data.getAllData().get(q).getAttack()));
                                adapter.notifyDataSetChanged();
                            }

                        }

                    });
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check_attack.setChecked(false);
                check_defense.setChecked(false);
                check_hp.setChecked(false);

                Random random = new Random();
                int i = random.nextInt(934);
                count = 30;
                dataLists.clear();
                Call<Parser> call_change = parserJSON.PokemonParser(i);
                call_change.enqueue(new Callback<Parser>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(Call<Parser> call, Response<Parser> response) {
                        if (response.isSuccessful()) {
                            db = Room.databaseBuilder(getActivity(), DataBaseHelper.class, "database").allowMainThreadQueries().build();
                            dataBaseDao = db.getDataBaseDao();
                            dataBaseModel = new DataBaseModel();

                            Parser parser = response.body();

                            for (Pokemon pokemon : parser.getResults()) {
                                dataBaseModel.setName(pokemon.getName());
                                dataBaseModel.setUrl(pokemon.getUrl());
                                String dd = new String();
                                dd = pokemon.getName();
                                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                                        .addConverterFactory(GsonConverterFactory.create()).build();

                                ParserJSON parserJSON = retrofit.create(ParserJSON.class);
                                Call<Parser_Info> call_info_pokemon = parserJSON.InfoParser(pokemon.getUrl().substring(25));


                                String finalDd = dd;
                                call_info_pokemon.enqueue(new Callback<Parser_Info>() {
                                    @Override
                                    public void onResponse(Call<Parser_Info> call, Response<Parser_Info> response) {
                                        DataBaseHelper data = Room.databaseBuilder(getActivity(), DataBaseHelper.class, "database").allowMainThreadQueries().build();
                                        DataBaseDaoInfoPokemon dao = db.getDataBaseInfoPokemonDao();
                                        DataBaseInfoPokemon dataBaseInfoPokemon = new DataBaseInfoPokemon();
                                        Parser_Info parser = response.body();
                                        if (response.isSuccessful()) {
                                            dataBaseInfoPokemon.setHeight(parser.getHeight());
                                            dataBaseInfoPokemon.setWeight(parser.getWeight());
                                            for (Stat stat : parser.getStats()) {
                                                if (stat.getStat().getName().equals("hp"))
                                                    dataBaseInfoPokemon.setHp(stat.getBaseStat());
                                                if (stat.getStat().getName().equals("attack"))
                                                    dataBaseInfoPokemon.setAttack(stat.getBaseStat());
                                                if (stat.getStat().getName().equals("defense"))
                                                    dataBaseInfoPokemon.setDefense(stat.getBaseStat());
                                                if (stat.getStat().getName().equals("special-attack"))
                                                    dataBaseInfoPokemon.setSpecialAttack(stat.getBaseStat());
                                                if (stat.getStat().getName().equals("special-defense"))
                                                    dataBaseInfoPokemon.setSpecialDefense(stat.getBaseStat());
                                                if (stat.getStat().getName().equals("speed"))
                                                    dataBaseInfoPokemon.setSpeed(stat.getBaseStat());
                                            }

                                            List type_mass = new ArrayList();
                                            for (Type type : parser.getTypes()) {
                                                type_mass.add(type.getType().getName());
                                            }
                                            dataBaseInfoPokemon.setType(type_mass.toString());
                                            dataBaseInfoPokemon.setSprites(parser.getSprites().getFrontDefault());
                                            dataBaseInfoPokemon.setName(finalDd);
                                        }
                                        dataLists.add(new DataList(dataBaseInfoPokemon.getName(), dataBaseInfoPokemon.getSprites(), dataBaseInfoPokemon.getType(), dataBaseInfoPokemon.getHeight(), dataBaseInfoPokemon.getWeight(), dataBaseInfoPokemon.getHp(), dataBaseInfoPokemon.getSpeed(), dataBaseInfoPokemon.getSpecialDefense(), dataBaseInfoPokemon.getSpecialAttack(), dataBaseInfoPokemon.getDefense(), dataBaseInfoPokemon.getAttack()));
                                        dao.insert(dataBaseInfoPokemon);
                                        adapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onFailure(Call<Parser_Info> call, Throwable t) {
                                        Log.e("Error", t.toString());

                                    }

                                });
                                dataBaseDao.insert(dataBaseModel);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<Parser> call, Throwable t) {
                        Log.e("Error", t.toString());

                        Toast.makeText(getActivity(), "Ошибка загрузки, пожалуйста проверьте ваше подключение к сети..." + "\n" + "С уважением.", Toast.LENGTH_SHORT).show();
                        DataBaseHelper dbd = Room.databaseBuilder(getContext(), DataBaseHelper.class, "database").allowMainThreadQueries().build();
                        DataBaseDaoInfoPokemon data = dbd.getDataBaseInfoPokemonDao();
                        for (int q = 0; q < data.getAllData().size(); q++) {
                            dataLists.add(new DataList(data.getAllData().get(q).getName(), data.getAllData().get(q).getSprites(), data.getAllData().get(q).getType(), data.getAllData().get(q).getHeight(), data.getAllData().get(q).getWeight(), data.getAllData().get(q).getHp(), data.getAllData().get(q).getSpeed(), data.getAllData().get(q).getSpecialDefense(), data.getAllData().get(q).getSpecialAttack(), data.getAllData().get(q).getDefense(), data.getAllData().get(q).getAttack()));
                            adapter.notifyDataSetChanged();
                        }

                    }


                });
            }
        });

        DataBaseHelper data = Room.databaseBuilder(getActivity(), DataBaseHelper.class, "database").allowMainThreadQueries().build();
        DataBaseDaoInfoPokemon da = data.getDataBaseInfoPokemonDao();

        check_attack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    count = da.getAllData().size();
                if (isChecked) {
                    attack = true;
                    if(defense == false && hp == false){
                        dataLists.clear();
                        for (int q = 0; q < da.getAllDataAttach().size(); q++) {
                            dataLists.add(new DataList(da.getAllDataAttach().get(q).getName(), da.getAllDataAttach().get(q).getSprites(), da.getAllDataAttach().get(q).getType(), da.getAllDataAttach().get(q).getHeight(), da.getAllDataAttach().get(q).getWeight(), da.getAllDataAttach().get(q).getHp(), da.getAllDataAttach().get(q).getSpeed(), da.getAllDataAttach().get(q).getSpecialDefense(), da.getAllDataAttach().get(q).getSpecialAttack(), da.getAllDataAttach().get(q).getDefense(), da.getAllDataAttach().get(q).getAttack()));
                            adapter.notifyDataSetChanged();
                        }
                    }else if(defense == true && hp == false){
                        dataLists.clear();
                        for (int q = 0; q < da.getAllDataAttackDefense().size(); q++) {
                            dataLists.add(new DataList(da.getAllDataAttackDefense().get(q).getName(), da.getAllDataAttackDefense().get(q).getSprites(), da.getAllDataAttackDefense().get(q).getType(), da.getAllDataAttackDefense().get(q).getHeight(), da.getAllDataAttackDefense().get(q).getWeight(), da.getAllDataAttackDefense().get(q).getHp(), da.getAllDataAttackDefense().get(q).getSpeed(), da.getAllDataAttackDefense().get(q).getSpecialDefense(), da.getAllDataAttackDefense().get(q).getSpecialAttack(), da.getAllDataAttackDefense().get(q).getDefense(), da.getAllDataAttackDefense().get(q).getAttack()));
                            adapter.notifyDataSetChanged();
                        }
                    }else if(defense == false && hp == true){
                        dataLists.clear();
                        for (int q = 0; q < da.getAllDataAttackHp().size(); q++) {
                            dataLists.add(new DataList(da.getAllDataAttackHp().get(q).getName(), da.getAllDataAttackHp().get(q).getSprites(), da.getAllDataAttackHp().get(q).getType(), da.getAllDataAttackHp().get(q).getHeight(), da.getAllDataAttackHp().get(q).getWeight(), da.getAllDataAttackHp().get(q).getHp(), da.getAllDataAttackHp().get(q).getSpeed(), da.getAllDataAttackHp().get(q).getSpecialDefense(), da.getAllDataAttackHp().get(q).getSpecialAttack(), da.getAllDataAttackHp().get(q).getDefense(), da.getAllDataAttackHp().get(q).getAttack()));
                            adapter.notifyDataSetChanged();
                        }
                    } else{
                        dataLists.clear();
                        for (int q = 0; q < da.getAllDataAttachDefenseHp().size(); q++) {
                            dataLists.add(new DataList(da.getAllDataAttachDefenseHp().get(q).getName(), da.getAllDataAttachDefenseHp().get(q).getSprites(), da.getAllDataAttachDefenseHp().get(q).getType(), da.getAllDataAttachDefenseHp().get(q).getHeight(), da.getAllDataAttachDefenseHp().get(q).getWeight(), da.getAllDataAttachDefenseHp().get(q).getHp(), da.getAllDataAttachDefenseHp().get(q).getSpeed(), da.getAllDataAttachDefenseHp().get(q).getSpecialDefense(), da.getAllDataAttachDefenseHp().get(q).getSpecialAttack(), da.getAllDataAttachDefenseHp().get(q).getDefense(), da.getAllDataAttachDefenseHp().get(q).getAttack()));
                            adapter.notifyDataSetChanged();
                        }
                    }

                } else {
                    attack = false;
                    if(defense == true && hp == false){
                        dataLists.clear();
                        for (int q = 0; q < da.getAllDataDefense().size(); q++) {
                            dataLists.add(new DataList(da.getAllDataDefense().get(q).getName(), da.getAllDataDefense().get(q).getSprites(), da.getAllDataDefense().get(q).getType(), da.getAllDataDefense().get(q).getHeight(), da.getAllDataDefense().get(q).getWeight(), da.getAllDataDefense().get(q).getHp(), da.getAllDataDefense().get(q).getSpeed(), da.getAllDataDefense().get(q).getSpecialDefense(), da.getAllDataDefense().get(q).getSpecialAttack(), da.getAllDataDefense().get(q).getDefense(), da.getAllDataDefense().get(q).getAttack()));
                            adapter.notifyDataSetChanged();
                        }
                    }else if(defense == false && hp == true){
                        dataLists.clear();
                        for (int q = 0; q < da.getAllDataHp().size(); q++) {
                            dataLists.add(new DataList(da.getAllDataHp().get(q).getName(), da.getAllDataHp().get(q).getSprites(), da.getAllDataHp().get(q).getType(), da.getAllDataHp().get(q).getHeight(), da.getAllDataHp().get(q).getWeight(), da.getAllDataHp().get(q).getHp(), da.getAllDataHp().get(q).getSpeed(), da.getAllDataHp().get(q).getSpecialDefense(), da.getAllDataHp().get(q).getSpecialAttack(), da.getAllDataHp().get(q).getDefense(), da.getAllDataHp().get(q).getAttack()));
                            adapter.notifyDataSetChanged();
                        }
                    }else if(defense == true && hp == true){
                        dataLists.clear();
                        for (int q = 0; q < da.getAllDataDefenseHp().size(); q++) {
                            dataLists.add(new DataList(da.getAllDataDefenseHp().get(q).getName(), da.getAllDataDefenseHp().get(q).getSprites(), da.getAllDataDefenseHp().get(q).getType(), da.getAllDataDefenseHp().get(q).getHeight(), da.getAllDataDefenseHp().get(q).getWeight(), da.getAllDataDefenseHp().get(q).getHp(), da.getAllDataDefenseHp().get(q).getSpeed(), da.getAllDataDefenseHp().get(q).getSpecialDefense(), da.getAllDataDefenseHp().get(q).getSpecialAttack(), da.getAllDataDefenseHp().get(q).getDefense(), da.getAllDataDefenseHp().get(q).getAttack()));
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }

        });

        check_defense.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                count = da.getAllData().size();
                    if (isChecked) {
                        defense = true;

                        if(attack == false && hp == false){
                            dataLists.clear();
                            for (int q = 0; q < da.getAllDataDefense().size(); q++) {
                                dataLists.add(new DataList(da.getAllDataDefense().get(q).getName(), da.getAllDataDefense().get(q).getSprites(), da.getAllDataDefense().get(q).getType(), da.getAllDataDefense().get(q).getHeight(), da.getAllDataDefense().get(q).getWeight(), da.getAllDataDefense().get(q).getHp(), da.getAllDataDefense().get(q).getSpeed(), da.getAllDataDefense().get(q).getSpecialDefense(), da.getAllDataDefense().get(q).getSpecialAttack(), da.getAllDataDefense().get(q).getDefense(), da.getAllDataDefense().get(q).getAttack()));
                                adapter.notifyDataSetChanged();
                            }
                        }else if(attack == true && hp == false){
                            dataLists.clear();
                            for (int q = 0; q < da.getAllDataAttackDefense().size(); q++) {
                                dataLists.add(new DataList(da.getAllDataAttackDefense().get(q).getName(), da.getAllDataAttackDefense().get(q).getSprites(), da.getAllDataAttackDefense().get(q).getType(), da.getAllDataAttackDefense().get(q).getHeight(), da.getAllDataAttackDefense().get(q).getWeight(), da.getAllDataAttackDefense().get(q).getHp(), da.getAllDataAttackDefense().get(q).getSpeed(), da.getAllDataAttackDefense().get(q).getSpecialDefense(), da.getAllDataAttackDefense().get(q).getSpecialAttack(), da.getAllDataAttackDefense().get(q).getDefense(), da.getAllDataAttackDefense().get(q).getAttack()));
                                adapter.notifyDataSetChanged();
                            }
                        }else if(attack == false && hp == true){
                            dataLists.clear();
                            for (int q = 0; q < da.getAllDataDefenseHp().size(); q++) {
                                dataLists.add(new DataList(da.getAllDataDefenseHp().get(q).getName(), da.getAllDataDefenseHp().get(q).getSprites(), da.getAllDataDefenseHp().get(q).getType(), da.getAllDataDefenseHp().get(q).getHeight(), da.getAllDataDefenseHp().get(q).getWeight(), da.getAllDataDefenseHp().get(q).getHp(), da.getAllDataDefenseHp().get(q).getSpeed(), da.getAllDataDefenseHp().get(q).getSpecialDefense(), da.getAllDataDefenseHp().get(q).getSpecialAttack(), da.getAllDataDefenseHp().get(q).getDefense(), da.getAllDataDefenseHp().get(q).getAttack()));
                                adapter.notifyDataSetChanged();
                            }
                        } else{
                            dataLists.clear();
                            for (int q = 0; q < da.getAllDataAttachDefenseHp().size(); q++) {
                                dataLists.add(new DataList(da.getAllDataAttachDefenseHp().get(q).getName(), da.getAllDataAttachDefenseHp().get(q).getSprites(), da.getAllDataAttachDefenseHp().get(q).getType(), da.getAllDataAttachDefenseHp().get(q).getHeight(), da.getAllDataAttachDefenseHp().get(q).getWeight(), da.getAllDataAttachDefenseHp().get(q).getHp(), da.getAllDataAttachDefenseHp().get(q).getSpeed(), da.getAllDataAttachDefenseHp().get(q).getSpecialDefense(), da.getAllDataAttachDefenseHp().get(q).getSpecialAttack(), da.getAllDataAttachDefenseHp().get(q).getDefense(), da.getAllDataAttachDefenseHp().get(q).getAttack()));
                                adapter.notifyDataSetChanged();
                            }
                        }

                    } else {
                        defense = false;

                        if(attack == true && hp == false){
                            dataLists.clear();
                            for (int q = 0; q < da.getAllDataAttach().size(); q++) {
                                dataLists.add(new DataList(da.getAllDataAttach().get(q).getName(), da.getAllDataAttach().get(q).getSprites(), da.getAllDataAttach().get(q).getType(), da.getAllDataAttach().get(q).getHeight(), da.getAllDataAttach().get(q).getWeight(), da.getAllDataAttach().get(q).getHp(), da.getAllDataAttach().get(q).getSpeed(), da.getAllDataAttach().get(q).getSpecialDefense(), da.getAllDataAttach().get(q).getSpecialAttack(), da.getAllDataAttach().get(q).getDefense(), da.getAllDataAttach().get(q).getAttack()));
                                adapter.notifyDataSetChanged();
                            }
                        }else if(attack == false && hp == true){
                            dataLists.clear();
                            for (int q = 0; q < da.getAllDataHp().size(); q++) {
                                dataLists.add(new DataList(da.getAllDataHp().get(q).getName(), da.getAllDataHp().get(q).getSprites(), da.getAllDataHp().get(q).getType(), da.getAllDataHp().get(q).getHeight(), da.getAllDataHp().get(q).getWeight(), da.getAllDataHp().get(q).getHp(), da.getAllDataHp().get(q).getSpeed(), da.getAllDataHp().get(q).getSpecialDefense(), da.getAllDataHp().get(q).getSpecialAttack(), da.getAllDataHp().get(q).getDefense(), da.getAllDataHp().get(q).getAttack()));
                                adapter.notifyDataSetChanged();
                            }
                        }else if(attack == true && hp == true){
                            dataLists.clear();
                            for (int q = 0; q < da.getAllDataAttackHp().size(); q++) {
                                dataLists.add(new DataList(da.getAllDataAttackHp().get(q).getName(), da.getAllDataAttackHp().get(q).getSprites(), da.getAllDataAttackHp().get(q).getType(), da.getAllDataAttackHp().get(q).getHeight(), da.getAllDataAttackHp().get(q).getWeight(), da.getAllDataAttackHp().get(q).getHp(), da.getAllDataAttackHp().get(q).getSpeed(), da.getAllDataAttackHp().get(q).getSpecialDefense(), da.getAllDataAttackHp().get(q).getSpecialAttack(), da.getAllDataAttackHp().get(q).getDefense(), da.getAllDataAttackHp().get(q).getAttack()));
                                adapter.notifyDataSetChanged();
                            }
                        }

                    }
            }
        });

        check_hp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                count = da.getAllData().size();
                if (isChecked) {
                    hp = true;

                    if(attack == false && defense == false){
                        dataLists.clear();
                        for (int q = 0; q < da.getAllDataHp().size(); q++) {
                            dataLists.add(new DataList(da.getAllDataHp().get(q).getName(), da.getAllDataHp().get(q).getSprites(), da.getAllDataHp().get(q).getType(), da.getAllDataHp().get(q).getHeight(), da.getAllDataHp().get(q).getWeight(), da.getAllDataHp().get(q).getHp(), da.getAllDataHp().get(q).getSpeed(), da.getAllDataHp().get(q).getSpecialDefense(), da.getAllDataHp().get(q).getSpecialAttack(), da.getAllDataHp().get(q).getDefense(), da.getAllDataHp().get(q).getAttack()));
                            adapter.notifyDataSetChanged();
                        }
                    }else if(attack == true && defense == false){
                        dataLists.clear();
                        for (int q = 0; q < da.getAllDataAttackHp().size(); q++) {
                            dataLists.add(new DataList(da.getAllDataAttackHp().get(q).getName(), da.getAllDataAttackHp().get(q).getSprites(), da.getAllDataAttackHp().get(q).getType(), da.getAllDataAttackHp().get(q).getHeight(), da.getAllDataAttackHp().get(q).getWeight(), da.getAllDataAttackHp().get(q).getHp(), da.getAllDataAttackHp().get(q).getSpeed(), da.getAllDataAttackHp().get(q).getSpecialDefense(), da.getAllDataAttackHp().get(q).getSpecialAttack(), da.getAllDataAttackHp().get(q).getDefense(), da.getAllDataAttackHp().get(q).getAttack()));
                            adapter.notifyDataSetChanged();
                        }
                    }else if(attack == false && defense == true){
                        dataLists.clear();
                        for (int q = 0; q < da.getAllDataDefenseHp().size(); q++) {
                            dataLists.add(new DataList(da.getAllDataDefenseHp().get(q).getName(), da.getAllDataDefenseHp().get(q).getSprites(), da.getAllDataDefenseHp().get(q).getType(), da.getAllDataDefenseHp().get(q).getHeight(), da.getAllDataDefenseHp().get(q).getWeight(), da.getAllDataDefenseHp().get(q).getHp(), da.getAllDataDefenseHp().get(q).getSpeed(), da.getAllDataDefenseHp().get(q).getSpecialDefense(), da.getAllDataDefenseHp().get(q).getSpecialAttack(), da.getAllDataDefenseHp().get(q).getDefense(), da.getAllDataDefenseHp().get(q).getAttack()));
                            adapter.notifyDataSetChanged();
                        }
                    } else{
                        dataLists.clear();
                        for (int q = 0; q < da.getAllDataAttachDefenseHp().size(); q++) {
                            dataLists.add(new DataList(da.getAllDataAttachDefenseHp().get(q).getName(), da.getAllDataAttachDefenseHp().get(q).getSprites(), da.getAllDataAttachDefenseHp().get(q).getType(), da.getAllDataAttachDefenseHp().get(q).getHeight(), da.getAllDataAttachDefenseHp().get(q).getWeight(), da.getAllDataAttachDefenseHp().get(q).getHp(), da.getAllDataAttachDefenseHp().get(q).getSpeed(), da.getAllDataAttachDefenseHp().get(q).getSpecialDefense(), da.getAllDataAttachDefenseHp().get(q).getSpecialAttack(), da.getAllDataAttachDefenseHp().get(q).getDefense(), da.getAllDataAttachDefenseHp().get(q).getAttack()));
                            adapter.notifyDataSetChanged();
                        }
                    }

                } else {
                    hp = false;

                    if(attack == true && defense == false){
                        dataLists.clear();
                        for (int q = 0; q < da.getAllDataAttach().size(); q++) {
                            dataLists.add(new DataList(da.getAllDataAttach().get(q).getName(), da.getAllDataAttach().get(q).getSprites(), da.getAllDataAttach().get(q).getType(), da.getAllDataAttach().get(q).getHeight(), da.getAllDataAttach().get(q).getWeight(), da.getAllDataAttach().get(q).getHp(), da.getAllDataAttach().get(q).getSpeed(), da.getAllDataAttach().get(q).getSpecialDefense(), da.getAllDataAttach().get(q).getSpecialAttack(), da.getAllDataAttach().get(q).getDefense(), da.getAllDataAttach().get(q).getAttack()));
                            adapter.notifyDataSetChanged();
                        }
                    }else if(attack == false && defense == true){
                        dataLists.clear();
                        for (int q = 0; q < da.getAllDataDefense().size(); q++) {
                            dataLists.add(new DataList(da.getAllDataDefense().get(q).getName(), da.getAllDataDefense().get(q).getSprites(), da.getAllDataDefense().get(q).getType(), da.getAllDataDefense().get(q).getHeight(), da.getAllDataDefense().get(q).getWeight(), da.getAllDataDefense().get(q).getHp(), da.getAllDataDefense().get(q).getSpeed(), da.getAllDataDefense().get(q).getSpecialDefense(), da.getAllDataDefense().get(q).getSpecialAttack(), da.getAllDataDefense().get(q).getDefense(), da.getAllDataDefense().get(q).getAttack()));
                            adapter.notifyDataSetChanged();
                        }
                    }else if(attack == true && defense == true){
                        dataLists.clear();
                        for (int q = 0; q < da.getAllDataAttackDefense().size(); q++) {
                            dataLists.add(new DataList(da.getAllDataAttackDefense().get(q).getName(), da.getAllDataAttackDefense().get(q).getSprites(), da.getAllDataAttackDefense().get(q).getType(), da.getAllDataAttackDefense().get(q).getHeight(), da.getAllDataAttackDefense().get(q).getWeight(), da.getAllDataAttackDefense().get(q).getHp(), da.getAllDataAttackDefense().get(q).getSpeed(), da.getAllDataAttackDefense().get(q).getSpecialDefense(), da.getAllDataAttackDefense().get(q).getSpecialAttack(), da.getAllDataAttackDefense().get(q).getDefense(), da.getAllDataAttackDefense().get(q).getAttack()));
                            adapter.notifyDataSetChanged();
                        }
                    }

                }
            }
        });

    }


    @Override
    public void onDetach() {
        super.onDetach();
        onItemClickListener = null;
    }


}
