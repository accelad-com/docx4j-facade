package com.accelad.docx4j.tags;

import com.accelad.docx4j.facade.Paragraph;
import com.accelad.docx4j.facade.WordProcessor;
import com.accelad.docx4j.facade.WordStyle;

public class TagUpdaterBlock implements TagValueUpdater {
    private final TagValueBlock tagValue;
    private final WordProcessor processor;

    public TagUpdaterBlock(TagValueBlock tagValue, WordProcessor processor) {
        this.tagValue = tagValue;
        this.processor = processor;
    }

    @Override
    public boolean update(Tag tag) {
        Paragraph container = tag.getParagraph();
        int pos = processor.getParagraphPosition(container);

        container.removeAll(tag.getItems());

        for (int i = 0; i < tagValue.getContents().length; i++) {
            Paragraph contentParagraph = processor.newStyledParagraphOfText(WordStyle.NORMAL, "");
            processor.add(pos, contentParagraph, container);

            TagValue content = tagValue.getContents()[tagValue.getContents().length -1 - i];
            TagValueUpdater updater = new TagUpdaterFactory(processor).getUpdater(content);
            Tag fakeTag = new Tag(null, contentParagraph, contentParagraph.getIndexedContent());
            updater.update(fakeTag);
        }

        return SUCCESS;
    }
}
