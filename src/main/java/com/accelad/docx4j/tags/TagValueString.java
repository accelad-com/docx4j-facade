package com.accelad.docx4j.tags;

public class TagValueString implements TagValue {

    private final String value;

    public TagValueString(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public void accept(TagValueVisitor visitor) {
        visitor.visit(this);
    }

}
