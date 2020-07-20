package com.example.pokeapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Parser_Info {

    @SerializedName("height")
    @Expose
    private Integer height;

    @SerializedName("weight")
    @Expose
    private Integer weight;

    @SerializedName("sprites")
    @Expose
    private Sprites sprites;

    @SerializedName("stats")
    @Expose
    private List<Stat> stats = null;

    @SerializedName("types")
    @Expose
    private List<Type> types = null;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}
