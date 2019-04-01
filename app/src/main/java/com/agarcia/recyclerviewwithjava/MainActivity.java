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
    private RecyclerView mRecyclerView;
    private PokedexAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokemonsList = new ArrayList<>();

        //Configurando Recycler View
        mRecyclerView = findViewById(R.id.rv_pokemon_list);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mAdapter = new PokedexAdapter(pokemonsList, R.layout.poke_item, this);
        mRecyclerView.setAdapter(mAdapter);

        //Request
        Call<PokemonResponse> call = ApiAdapter.getApiService().getPokemons();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
        if(response.isSuccessful())
        {
            PokemonResponse pokemons = response.body();

            pokemonsList = pokemons.getResults();

            mAdapter.addPokemon(pokemonsList);

            Log.d(TAG, "Tamaño de la lista " + pokemonsList.size());
        }
    }

    @Override
    public void onFailure(Call<PokemonResponse> call, Throwable t) {
        Log.i(TAG, "On failure:" + t.getMessage());
    }
}
