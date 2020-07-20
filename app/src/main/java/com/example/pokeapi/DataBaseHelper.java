package com.example.pokeapi;

import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = { DataBaseModel.class,DataBaseInfoPokemon.class}, version = 1,exportSchema = false)
public abstract class DataBaseHelper extends RoomDatabase {

    public abstract DataBaseDao getDataBaseDao();
    public abstract DataBaseDaoInfoPokemon getDataBaseInfoPokemonDao();

}
