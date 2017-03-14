package com.accelad.docx4j.tags;

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
}
