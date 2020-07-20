package com.example.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ParserJSON {

    @GET("pokemon")
    Call<Parser> PokemonParser();
}
