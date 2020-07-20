package com.example.pokeapi;

import androidx.annotation.NonNull;

public class DataList {

    private String name;
    private String sprites;
    private String type;
    private Integer height;
    private Integer weight;
    private Integer hp;
    private Integer specialAttack;
    private Integer specialDefense;
    private Integer speed;
    private Integer defense;
    private Integer attack;


    public DataList(String name, String sprites,String type,Integer height,Integer weight,Integer hp,Integer speed,Integer specialDefense, Integer specialAttack, Integer defense,Integer attack) {
        this.name = name;
        this.sprites = sprites;
        this.specialAttack = specialAttack;
        this.defense = defense;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.attack = attack;
        this.hp = hp;
        this.height =height;
        this.weight =weight;
        this.type =type;
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

    public void setName( String name) {
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
