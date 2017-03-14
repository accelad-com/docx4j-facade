package com.accelad.docx4j.tags;

import com.accelad.docx4j.facade.Run;
import com.accelad.docx4j.facade.WordItemContainer;
import com.accelad.docx4j.facade.WordProcessor;

public class TagUpdaterString implements TagValueUpdater {
    private final TagValueString tagValue;
    private final WordProcessor processor;

    public TagUpdaterString(TagValueString tagValue, WordProcessor processor) {
        this.tagValue = tagValue;
        this.processor = processor;
    }

    @Override
    public boolean update(Tag tag) {
        WordItemContainer container = tag.getParagraph();
        String stringValue = tagValue.toString();
        Run newText = processor.newText(stringValue);
        processor.replaceBy(container, tag.getItems(), newText);
        return SUCCESS;
    }
}
