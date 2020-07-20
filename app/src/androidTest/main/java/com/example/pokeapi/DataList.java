package com.example.pokeapi;

public class DataList {

    private String name;
    private String picture;


    public DataList(String name, String picture) {
        this.name = name;
        this.picture = picture;
    }


    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
