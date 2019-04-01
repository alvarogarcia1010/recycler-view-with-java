package com.agarcia.recyclerviewwithjava.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agarcia.recyclerviewwithjava.DetailsActivity;
import com.agarcia.recyclerviewwithjava.Models.Pokemon;
import com.agarcia.recyclerviewwithjava.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexAdapter.ViewHolder> implements View.OnClickListener {

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

        Glide.with(activity)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+ this.getNumber(currentPokemon.getUrl())+".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.picture);

        holder.mCardView.setOnClickListener(this);

    }

    @Override
    public int getItemCount()
    {
        return pokemons.size();
    }

    public void addPokemon(ArrayList<Pokemon> listaPokemon)
    {
        pokemons.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.card_view)
        {
            Intent intent = new Intent(activity, DetailsActivity.class);
            activity.startActivity(intent);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView name;
        private TextView url;
        private ImageView picture;
        private CardView mCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name    = (TextView) itemView.findViewById(R.id.tv_poke_name);
            url     = (TextView) itemView.findViewById(R.id.tv_poke_type);
            picture = (ImageView) itemView.findViewById(R.id.poke_icon);
            mCardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    public int getNumber(String url)
    {
        String[] urlPart = url.split("/");
        return Integer.parseInt(urlPart[urlPart.length-1]);
    }
}
