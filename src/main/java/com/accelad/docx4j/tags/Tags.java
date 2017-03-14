package com.accelad.docx4j.tags;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tags implements Iterable<Tag> {

    private final List<Tag> list;

    public Tags() {
        list = new ArrayList<>();
    }

    public Tags(List<Tag> tags) {
        list = new ArrayList<>(tags);
    }

    public void add(Tag tag) {
        list.add(tag);
    }

    public void addAll(Tags tags) {
        list.addAll(tags.list);
    }

    @Override
    public Iterator<Tag> iterator() {
        return list.iterator();
    }

}
