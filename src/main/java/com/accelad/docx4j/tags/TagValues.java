package com.accelad.docx4j.tags;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TagValues {

    private Map<TagName, TagValue> values = new HashMap<>();

    public Optional<TagValue> get(TagName key) {
        return Optional.ofNullable(values.get(key));
    }

    public void put(TagName key, TagValue value) {
        values.put(key, value);
    }

}
