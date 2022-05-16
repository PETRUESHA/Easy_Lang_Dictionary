package com.example.easy_lang_dictionary;

import java.util.ArrayList;
import java.util.Map;

public class Word_list {
    private String name;
    private ArrayList<String> words;
    private ArrayList<String> translates;

    public Word_list() {}

    public Word_list(String n) {
        this.name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public ArrayList<String> getTranslates() {
        return translates;
    }

    public void setTranslates(ArrayList<String> translates) { this.translates = translates; }

    public void updateWords(String word) {
        words.add(word);
    }

    public void updateTranslates(String translate) {
        translates.add(translate);
    }
}
