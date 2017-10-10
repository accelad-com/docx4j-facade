package com.accelad.docx4j.tags;

import java.util.Objects;

public class TagValueTableCell {
    private final String value;
    private final int widthInPercent;
    private final String type;


    public TagValueTableCell(String value, int widthInPercent, String type) {
        this.value = value;
        this.widthInPercent = widthInPercent;
        this.type = type;
    }

    @Override
    public String toString() {
        return value;
    }

    public int getWidthInPercent() {
        return widthInPercent;
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

    public String getType() {
        return type;
    }
}
