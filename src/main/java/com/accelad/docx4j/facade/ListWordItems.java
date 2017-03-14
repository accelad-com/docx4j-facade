package com.accelad.docx4j.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ListWordItems implements WordItems {
    private List<WordItem> items;

    public ListWordItems(Collection<WordItem> items) {
        this.items = new ArrayList<>(items);
    }

    public ListWordItems() {
        this.items = new ArrayList<>();
    }

    public boolean add(WordItem e) {
        return items.add(e);
    }

    @Override
    public Iterator<WordItem> iterator() {
        return items.iterator();
    }

    @Override
    public String toString() {
        return items.toString();
    }

}
