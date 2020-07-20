package com.example.pokeapi;

import android.app.Application;

import androidx.room.Room;


public class CreateDataBase extends Application {

    private static CreateDataBase instance;
    private DataBaseHelper db;

    public static CreateDataBase getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        db = Room.databaseBuilder(getApplicationContext(), DataBaseHelper.class, "database")
                .allowMainThreadQueries()
                .build();
    }


    public DataBaseHelper getDataBaseInstance() {
        return db;
    }

}
