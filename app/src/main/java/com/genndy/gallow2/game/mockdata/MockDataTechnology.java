package com.genndy.gallow2.game.mockdata;

import java.util.ArrayList;
import java.util.List;

public class MockDataTechnology {
    List<String> words;
    public MockDataTechnology(){
        words = new ArrayList<String>();
        words.add("транзистор");
        words.add("смартфон");
        words.add("компьютер");
        words.add("интернет");
        words.add("радиоволны");
        words.add("аккумулятор");
        words.add("механика");
        words.add("информация");
        words.add("двигатель");
        words.add("химия");
        words.add("электричество");
        words.add("механизм");
        words.add("проект");
        words.add("технология");
        words.add("чертёж");
        words.add("генератор");
        words.add("сервопривод");
        words.add("колесо");
        words.add("энергия");
        words.add("транзистор");
        words.add("идея");
    }

    public List<String> getWord(){
        return words;
    }
}
