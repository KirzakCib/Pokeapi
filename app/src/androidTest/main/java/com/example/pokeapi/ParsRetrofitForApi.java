package com.example.pokeapi;

import android.app.AppComponentFactory;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_SHORT;

public class ParsRetrofitForApi {

    static final String BASE_URL = "https://pokeapi.co/api/v2/";
//    static List<DataList> dataList = new ArrayList<>();
//    SQLiteDatabase db;
//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        for(int i =0; i < outState.size(); i++) {
//            sharedPreferences = getSharedPreferences("key" + i, MODE_PRIVATE);
//            dataList.add(new DataList(sharedPreferences.getString("key"+i,""),"1"));
//        }
//        Log.e("d",dataList.toString());
//    }

    public static List<DataList> date(){
        List<DataList> list = new ArrayList<>();
//                openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        ParserJSON parserJSON = retrofit.create(ParserJSON.class);



        Call<Parser> call = parserJSON.PokemonParser();

        call.enqueue(new Callback<Parser>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Parser> call, Response<Parser> response) {
                if (response.isSuccessful()) {
                    Parser parser = response.body();


                    for (Pokemon pokemon : parser.getResults()) {
                        list.add(new DataList(pokemon.getName(),pokemon.getUrl()));
                    }
                    //Log.e("fasfsdfsdfsdfsd88",list.toString());
                }

            }

            @Override
            public void onFailure(Call<Parser> call, Throwable t) {
                Log.e("Error",t.toString());
            }

        });

       // Log.e("fasfsdfsdfsdfsd88",list.toString());
        return list;
    }

//    public static void make(Call<Parser> call) {
//        List<DataList> dataList = new ArrayList<>();
//        call.enqueue(new Callback<Parser>() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onResponse(Call<Parser> call, Response<Parser> response) {
//                if (response.isSuccessful()) {
//                    Parser parser = response.body();
//
//                    for (Pokemon pokemon : parser.getResults()) {
//                       // dataLists.add(new DataList(pokemon.getName()));
//                        dataList.add(0,new DataList(pokemon.getName(),pokemon.getUrl()));
//                        //Log.e("name",pokemon.getName()+"      " + pokemon.getUrl());
//                    }
//                    Log.e("fasfsdfsdfsdfsd88",dataList.toString());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Parser> call, Throwable t) {
//                Log.e("Error",t.toString());
//            }
//        });
//
//    }

}
