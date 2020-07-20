package com.example.pokeapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Type {

    @SerializedName("type")
    @Expose
    private Type_name type;

    public Type_name getType() {
        return type;
    }

    public void setType(Type_name type) {
        this.type = type;
    }

}
