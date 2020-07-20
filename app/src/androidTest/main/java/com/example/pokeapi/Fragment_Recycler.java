package com.example.pokeapi;

import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
    private DataBaseHelper db;

    //private RecyclerView recyclerView;
    //private final Adapter adapter = new Adapter();

    public static Fragment_Recycler newInstance() {
        return new Fragment_Recycler();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DataBaseHelper dataBaseHelper = CreateDataBase.getInstance().getDataBaseInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new Adapter(getActivity(),dataBaseHelper.getDataBaseDao().getAllData());
        recyclerView.setAdapter(adapter);
       // db = CreateDataBase.getInstance().getDatabaseInstance();
        DataBaseModel dataBaseModel = new DataBaseModel();
        dataBaseModel.setName("dasdadassa");
        dataBaseHelper.getDataBaseDao().insert(dataBaseModel);
       // Log.e("fasfsdfsdfsdfsd88",dataLists.toString());
//        //adapter.addDate(ParsRetrofitForApi.date());
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create()).build();
//
//        ParserJSON parserJSON = retrofit.create(ParserJSON.class);
//
//
//        Call<Parser> call = parserJSON.PokemonParser();
//
//        call.enqueue(new Callback<Parser>() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onResponse(Call<Parser> call, Response<Parser> response) {
//                if (response.isSuccessful()) {
//                    Parser parser = response.body();
//
//                    for (Pokemon pokemon : parser.getResults()) {
////                        list.add(new DataList(pokemon.getName(),pokemon.getUrl()));
//                        dataLists.add(new DataList(pokemon.getName(),pokemon.getUrl()));
//                    }
//                    //Log.e("fasfsdfsdfsdfsd88",dataLists.toString());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Parser> call, Throwable t) {
//                Log.e("Error",t.toString());
//            }
//
//        });
    }
}
