package com.agarcia.recyclerviewwithjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.agarcia.recyclerviewwithjava.Adapter.PokedexAdapter;
import com.agarcia.recyclerviewwithjava.Models.Pokemon;
import com.agarcia.recyclerviewwithjava.Response.ApiAdapter;
import com.agarcia.recyclerviewwithjava.Response.PokemonResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<PokemonResponse> {

    private static final String TAG = "Pokeapi";
    private ArrayList<Pokemon> pokemonsList;
    private RecyclerView recycler;
    private PokedexAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokemonsList = new ArrayList<>();

        recycler = findViewById(R.id.rv_pokemon_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recycler.setLayoutManager(linearLayoutManager);

        adapter = new PokedexAdapter(pokemonsList, R.layout.poke_item, this);
        recycler.setAdapter(adapter);
        //recycler.setHasFixedSize(true);

        Call<PokemonResponse> call = ApiAdapter.getApiService().getPokemons();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
        if(response.isSuccessful())
        {
            PokemonResponse pokemons = response.body();

            pokemonsList = pokemons.getResults();

            adapter.addPokemon(pokemonsList);

            Log.d(TAG, "Tamaño de la lista " + pokemonsList.size());
        }
    }

    @Override
    public void onFailure(Call<PokemonResponse> call, Throwable t) {
        Log.i(TAG, "On failure:" + t.getMessage());
    }
}
