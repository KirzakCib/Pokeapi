package com.example.pokeapi;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataBaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insert(DataBaseModel dataBaseModel);

    @Query("SELECT * FROM DataBaseModel WHERE name = :name")
    DataBaseModel getName(String name);

    @Query("SELECT * FROM DataBaseModel")
    List<DataBaseModel> getAllData();

    @Delete
    void delete(DataBaseModel dataBaseModel);

}
