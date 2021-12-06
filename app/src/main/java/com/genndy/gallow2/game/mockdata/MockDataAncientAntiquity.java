package com.genndy.gallow2.game.mockdata;

import java.util.ArrayList;
import java.util.List;

public class MockDataAncientAntiquity {
    List<String> words;
    public MockDataAncientAntiquity(){
        words = new ArrayList<String>();
        words.add("Архимед");
        words.add("Платон");
        words.add("Спарта");
        words.add("Гладиус");
        words.add("Ксистон");
        words.add("Легионер");
        words.add("Галл");
        words.add("Рим");
        words.add("Греция");
        words.add("Македония");
        words.add("Египет");
        words.add("Колесница");
        words.add("Античность");
        words.add("Зевс");
        words.add("Юпитер");
        words.add("Цицерон");
        words.add("Крит");
        words.add("Афина");
        words.add("Ф");
    }

    public List<String> getWord(){
        return words;
    }
}