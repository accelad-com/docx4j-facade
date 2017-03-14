package com.accelad.docx4j.tags;

public interface TagValueUpdater {

    public static final boolean SUCCESS = true;
    public static final boolean FAILED = false;

    public boolean update(Tag tag);

}
