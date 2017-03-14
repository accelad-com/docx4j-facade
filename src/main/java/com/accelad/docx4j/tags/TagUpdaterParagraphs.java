package com.accelad.docx4j.tags;

import com.accelad.docx4j.facade.Paragraph;
import com.accelad.docx4j.facade.WordProcessor;
import com.accelad.docx4j.facade.WordStyle;

public class TagUpdaterParagraphs implements TagValueUpdater {
    private final TagValueParagraphs tagValue;
    private final WordProcessor processor;

    public TagUpdaterParagraphs(TagValueParagraphs tagValue, WordProcessor processor) {
        this.tagValue = tagValue;
        this.processor = processor;
    }

    @Override
    public boolean update(Tag tag) {
        Paragraph container = tag.getParagraph();
        int pos = processor.getParagraphPosition(container);

        container.removeAll(tag.getItems());

        for (int i = 0; i < tagValue.getTitles().length; i++) {
            int index = tagValue.getContents().length - 1 - i;
            String title = tagValue.getTitles()[index];
            String style = tagValue.getStyles()[index];
            TagValue content = tagValue.getContents()[index];

            Paragraph titleParagraph = processor.newStyledParagraphOfText(style, title);
            processor.add(pos, titleParagraph, container);

            Paragraph contentParagraph = processor.newStyledParagraphOfText(WordStyle.NORMAL, "");
            processor.add(pos + 1, contentParagraph, container);

            TagValueUpdater updater = new TagUpdaterFactory(processor).getUpdater(content);
            Tag fakeTag = new Tag(null, contentParagraph, contentParagraph.getIndexedContent());
            updater.update(fakeTag);
        }

        return SUCCESS;
    }
}
