package com.accelad.docx4j.facade;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IndexedWordItems implements WordItems {

    private final Map<WordItem, Integer> indexes = new HashMap<>();

    public Integer getPosition(WordItem key) {
        return indexes.get(key);
    }

    public Integer put(WordItem key, Integer value) {
        return indexes.put(key, value);
    }

    @Override
    public Iterator<WordItem> iterator() {
        return indexes.keySet().iterator();
    }

    @Override
    public String toString() {
        return indexes.toString();
    }

}
