package com.accelad.docx4j.tags;

import java.util.*;
import java.util.stream.Collectors;

public class TagValueTableRow implements Iterable<TagValueTableCell>{
    private final List<TagValueTableCell> values;

    public TagValueTableRow(){
        this.values = new ArrayList<>();
    }

    public TagValueTableRow(String... values){
        this.values = Arrays.stream(values).map(TagValueTableCell::new).collect(Collectors.toList());
    }

    public TagValueTableRow(TagValueTableCell... cells){
        this.values = new ArrayList<>(Arrays.asList(cells));
    }

    public List<TagValueTableCell> getValues() {
        return values;
    }

    public int size() {
        return values.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagValueTableRow that = (TagValueTableRow) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return "TagValueTableRow{" +
                "values=" + values +
                '}';
    }

    @Override
    public Iterator<TagValueTableCell> iterator() {
        return values.iterator();
    }
}
