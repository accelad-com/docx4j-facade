package com.accelad.docx4j.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Paragraphs implements Iterable<Paragraph> {
    private final List<Paragraph> list;

    public Paragraphs() {
        list = new ArrayList<>();
    }

    public Paragraphs(List<Paragraph> paragraphs) {
        list = new ArrayList<>(paragraphs);
    }

    public boolean add(Paragraph e) {
        return list.add(e);
    }

    @Override
    public Iterator<Paragraph> iterator() {
        return list.iterator();
    }

    public void addAll(Collection<Paragraph> paragraphs) {
        this.list.addAll(paragraphs);
    }

    public void addAll(Paragraphs paragraphs) {
        this.list.addAll(paragraphs.list);
    }

}
