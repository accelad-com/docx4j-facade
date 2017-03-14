package com.accelad.docx4j.tags;

public class TagUpdater {

    private final TagValues values;
    private final TagUpdaterFactory factory;
    
    public TagUpdater(TagUpdaterFactory factory, TagValues values) {
        this.values = values;
        this.factory = factory;
    }

    public boolean update(Tag tag) {
        TagName tagName = tag.getTagName();
        return values.get(tagName)
                .map(factory::getUpdater)
                .map(updater -> updater.update(tag))
                .orElse(false);
    }


}