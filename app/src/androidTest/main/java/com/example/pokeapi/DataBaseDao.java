package com.example.pokeapi;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataBaseDao {

    @Insert
    void  insert(DataBaseModel dataBaseModel);

    @Query("SELECT * FROM DataBaseModel")
    List<DataBaseModel> getAllData();

}
