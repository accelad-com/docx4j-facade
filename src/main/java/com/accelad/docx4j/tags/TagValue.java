package com.accelad.docx4j.tags;

public interface TagValue {

    public void accept(TagValueVisitor visitor);

}
