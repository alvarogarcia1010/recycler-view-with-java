package com.agarcia.recyclerviewwithjava.Response;

import com.agarcia.recyclerviewwithjava.Models.Pokemon;
import java.util.ArrayList;

public class PokemonResponse {
    private ArrayList<Pokemon> results;
    private Integer count;
    private String next;
    private String previous;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }
}
