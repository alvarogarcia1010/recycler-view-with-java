package com.agarcia.recyclerviewwithjava.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("pokemon")
    Call<PokemonResponse> getPokemons();
}
