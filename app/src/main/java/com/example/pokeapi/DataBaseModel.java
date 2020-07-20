package com.example.pokeapi;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DataBaseModel {

    //@PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "url")
    private String url;

//    @ColumnInfo(name = "height")
//    private Integer height;
//
//    @ColumnInfo(name = "weight")
//    private Integer weight;
//
//    @ColumnInfo(name = "type")
//    private String type;
//
//    @ColumnInfo(name = "hp")
//    private Integer hp;
//
//    @ColumnInfo(name = "defense")
//    private Integer defense;
//
//    @ColumnInfo(name = "attack")
//    private Integer attack;

    public DataBaseModel(){

    }

//    public DataBaseModel(String name,String url){
//        this.name = name;
//        this.url = url;
//    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

//    public Integer getHeight() {
//        return height;
//    }
//
//    public void setHeight(Integer height) {
//        this.height = height;
//    }
//
//    public Integer getWeight() {
//        return weight;
//    }
//
//    public void setWeight(Integer weight) {
//        this.weight = weight;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public Integer getDefense() {
//        return defense;
//    }
//
//    public void setDefense(Integer defense) {
//        this.defense = defense;
//    }
//
//    public Integer getAttack() {
//        return attack;
//    }
//
//    public void setAttack(Integer attack) {
//        this.attack = attack;
//    }
//
//    public Integer getHp() {
//        return hp;
//    }
//
//    public void setHp(Integer hp) {
//        this.hp = hp;
//    }
}
