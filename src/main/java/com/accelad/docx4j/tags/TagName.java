package com.accelad.docx4j.tags;

import java.util.Objects;

public class TagName {
    private final String name;

    public TagName(String name) {
        this.name = name;
    }

    public String asString() {
        return name;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof TagName)) {
            return false;
        }
        TagName castOther = (TagName) other;
        return Objects.equals(name, castOther.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

}
