package com.accelad.docx4j.tags;

import java.util.Objects;

public class TagValueTableCell {
    private final String value;

    public TagValueTableCell(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagValueTableCell that = (TagValueTableCell) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
