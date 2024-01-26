package com.example.quotaku;

public class Quote {
    String key, quote, character, anime;

    //important to read data from firebase realtime database
    public Quote() {

    }

    public Quote(String key,String quote, String character, String anime) {
        this.key = key;
        this.quote = quote;
        this.character = character;
        this.anime = anime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getAnime() {
        return anime;
    }

    public void setAnime(String anime) {
        this.anime = anime;
    }
}
