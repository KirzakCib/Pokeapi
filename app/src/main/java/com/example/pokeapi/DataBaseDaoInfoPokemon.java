package com.example.pokeapi;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataBaseDaoInfoPokemon {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insert(DataBaseInfoPokemon dataBaseInfoPokemon);

    @Query("SELECT * FROM DataBaseInfoPokemon WHERE name = :name")
    DataBaseInfoPokemon getName(String name);

    @Query("SELECT * FROM DataBaseInfoPokemon")
    List<DataBaseInfoPokemon> getAllData();

    @Query("SELECT * FROM DataBaseInfoPokemon ORDER BY attack DESC")
    List<DataBaseInfoPokemon> getAllDataAttach();

    @Query("SELECT * FROM DataBaseInfoPokemon ORDER BY defense DESC")
    List<DataBaseInfoPokemon> getAllDataDefense();

    @Query("SELECT * FROM DataBaseInfoPokemon ORDER BY hp DESC")
    List<DataBaseInfoPokemon> getAllDataHp();

    @Query("SELECT * FROM DataBaseInfoPokemon ORDER BY attack + defense DESC")
    List<DataBaseInfoPokemon> getAllDataAttackDefense();

    @Query("SELECT * FROM DataBaseInfoPokemon ORDER BY attack + hp DESC")
    List<DataBaseInfoPokemon> getAllDataAttackHp();

    @Query("SELECT * FROM DataBaseInfoPokemon ORDER BY defense + hp DESC")
    List<DataBaseInfoPokemon> getAllDataDefenseHp();

    @Query("SELECT * FROM DataBaseInfoPokemon ORDER BY attack + defense + hp DESC")
    List<DataBaseInfoPokemon> getAllDataAttachDefenseHp();

}
