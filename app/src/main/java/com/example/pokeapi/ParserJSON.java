package com.example.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ParserJSON {

    @GET("pokemon?limit=30")
    Call<Parser> PokemonParser(@Query("offset") int offset);

    @GET("{string}")
    Call<Parser_Info>  InfoParser(@Path("string") String infoPokemon);

}
