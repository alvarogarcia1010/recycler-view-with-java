package com.agarcia.recyclerviewwithjava.Pokeapi;

import com.agarcia.recyclerviewwithjava.Models.PokemonList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeapiService {

    @GET("pokemon")
    Call<PokemonList> getPokeList();
}

