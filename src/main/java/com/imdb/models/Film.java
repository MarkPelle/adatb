package com.imdb.models;

public class Film {
    public String eredetiCím;
    public String magyarCím;
    public String műfaj;

    public Film(String cím_eredeti, String cím_hu, String műfaj) {
        this.eredetiCím = cím_eredeti;
        this.magyarCím = cím_hu;
        this.műfaj = műfaj;
    }
}
