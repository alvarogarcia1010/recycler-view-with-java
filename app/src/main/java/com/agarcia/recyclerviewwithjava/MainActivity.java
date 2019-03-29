package com.agarcia.recyclerviewwithjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.agarcia.recyclerviewwithjava.Models.Pokemon;
import com.agarcia.recyclerviewwithjava.Models.PokemonList;
import com.agarcia.recyclerviewwithjava.Pokeapi.PokeapiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Pokeapi";
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        getDatos();
    }

    private void getDatos(){
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonList> pokeList = service.getPokeList();
        pokeList.enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                if(response.isSuccessful())
                {
                    PokemonList list = response.body();
                    ArrayList<Pokemon> pokemons = list.getResult();
                }
                else
                {
                    Log.i(TAG, "On response: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                Log.i(TAG, "On failure:" + t.getMessage());
            }
        });
    }
}
