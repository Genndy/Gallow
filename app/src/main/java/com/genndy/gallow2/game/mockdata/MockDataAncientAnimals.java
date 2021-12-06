package com.genndy.gallow2.game.mockdata;

import java.util.ArrayList;
import java.util.List;

public class MockDataAncientAnimals {
    List<String> words;
    public MockDataAncientAnimals(){
        words = new ArrayList<String>();
        words.add("динозавр");
        words.add("трилобит");
        words.add("диплодок");
        words.add("тиранозавр");
        words.add("ископаемое");
        words.add("птеродактиль");
        words.add("мамонт");
        words.add("саблезуб");
        words.add("дронт");
        words.add("платибелодон");
        words.add("пакицет");
        words.add("мегалодон");
        words.add("мегалоцерос");
        words.add("титанобоа");
        words.add("Арсинотерий");
        words.add("Астрапотерии");
        words.add("Титаноидесы");
        words.add("Смилодон");
        words.add("Стилинодон");

    }

    public List<String> getWord(){
        return words;
    }
}
