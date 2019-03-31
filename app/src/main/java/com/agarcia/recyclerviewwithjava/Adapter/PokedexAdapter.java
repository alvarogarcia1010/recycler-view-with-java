package com.agarcia.recyclerviewwithjava.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agarcia.recyclerviewwithjava.Models.Pokemon;
import com.agarcia.recyclerviewwithjava.R;

import java.util.ArrayList;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.ViewHolder> {

    private ArrayList<Pokemon> pokemons;
    private int resource;
    private Activity activity;

    public PokedexAdapter(ArrayList<Pokemon> pokemons, int resource, Activity activity) {
        this.pokemons = pokemons;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon currentPokemon = pokemons.get(position);

        holder.name.setText(currentPokemon.getName());
        holder.url.setText(currentPokemon.getUrl());

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void addPokemon(ArrayList<Pokemon> listaPokemon) {
        pokemons.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView name;
        private TextView url;
        private ImageView picture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name    = (TextView) itemView.findViewById(R.id.tv_poke_name);
            url     = (TextView) itemView.findViewById(R.id.tv_poke_type);
            picture = (ImageView) itemView.findViewById(R.id.poke_icon);
        }
    }
}
