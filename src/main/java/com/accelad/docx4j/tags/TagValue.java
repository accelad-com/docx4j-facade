package com.accelad.docx4j.tags;

public interface TagValue {
    void accept(TagValueVisitor visitor);
}
