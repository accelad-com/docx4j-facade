package com.accelad.docx4j.tags;

import java.util.Arrays;

public class TagValueBlock implements TagValue {

    private final TagValue[] contents;

    public TagValueBlock(TagValue[] contents) {
        this.contents = contents;
    }

    @Override
    public void accept(TagValueVisitor visitor) {
        visitor.visit(this);
    }

    public TagValue[] getContents() {
        return contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TagValueBlock that = (TagValueBlock) o;

        return Arrays.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(contents);
    }
}
