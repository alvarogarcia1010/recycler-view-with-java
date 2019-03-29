package com.agarcia.recyclerviewwithjava;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agarcia.recyclerviewwithjava.Models.Pokemon;

import java.util.ArrayList;

public class PokeAdapter extends RecyclerView.Adapter<PokeAdapter.ViewHolder> {

    private ArrayList<Pokemon> dataset;
    private Context context;

    public PokeAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.poke_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon p = dataset.get(position);
        holder.nombreTextView.setText(p.getName());
        holder.typeTextView.setText(p.getUrl());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView typeTextView;
        private TextView nombreTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            typeTextView = (TextView) itemView.findViewById(R.id.tv_poke_type);
            nombreTextView = (TextView) itemView.findViewById(R.id.tv_poke_name);
        }
    }

}