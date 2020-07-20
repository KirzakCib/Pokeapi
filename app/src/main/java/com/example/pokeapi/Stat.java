package com.example.pokeapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stat {

    @SerializedName("base_stat")
    @Expose
    private Integer baseStat;

    @SerializedName("stat")
    @Expose
    private Stat_name stat;

    public Integer getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(Integer baseStat) {
        this.baseStat = baseStat;
    }


    public Stat_name getStat() {
        return stat;
    }

    public void setStat(Stat_name stat) {
        this.stat = stat;
    }

}
