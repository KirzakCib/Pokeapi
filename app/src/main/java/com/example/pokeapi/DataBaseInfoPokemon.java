package com.example.pokeapi;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DataBaseInfoPokemon {

    @PrimaryKey
    @ColumnInfo(name = "name")
    @NonNull
    private String name;

    @ColumnInfo(name = "sprites")
    private String sprites;

    @ColumnInfo(name = "height")
    private Integer height;

    @ColumnInfo(name = "weight")
    private Integer weight;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "hp")
    private Integer hp;

    @ColumnInfo(name = "defense")
    private Integer defense;

    @ColumnInfo(name = "attack")
    private Integer attack;

    @ColumnInfo(name = "special-attack")
    private Integer specialAttack;

    @ColumnInfo(name = "special-defense")
    private Integer specialDefense;

    @ColumnInfo(name = "speed")
    private Integer speed;

    public DataBaseInfoPokemon(){

    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDefense() {
        return defense;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getSprites() {
        return sprites;
    }

    public void setSprites(String sprites) {
        this.sprites = sprites;
    }

    public Integer getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(Integer specialAttack) {
        this.specialAttack = specialAttack;
    }

    public Integer getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(Integer specialDefense) {
        this.specialDefense = specialDefense;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
