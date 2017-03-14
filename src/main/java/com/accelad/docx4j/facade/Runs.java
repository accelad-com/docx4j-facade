package com.accelad.docx4j.facade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Runs implements Iterable<Run> {

    private final List<Run> list;

    public Runs(List<Run> runs) {
        this.list = new ArrayList<>(runs);
    }

    public Runs() {
        this.list = new ArrayList<>();
    }

    public boolean add(Run e) {
        return list.add(e);
    }

    @Override
    public Iterator<Run> iterator() {
        return list.iterator();
    }

}
