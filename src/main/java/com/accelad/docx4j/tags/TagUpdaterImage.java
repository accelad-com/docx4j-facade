package com.accelad.docx4j.tags;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accelad.docx4j.facade.Run;
import com.accelad.docx4j.facade.WordItemContainer;
import com.accelad.docx4j.facade.WordItems;
import com.accelad.docx4j.facade.WordProcessor;
import com.accelad.docx4j.facade.WordProcessorException;

public class TagUpdaterImage implements TagValueUpdater {

    private static final Logger LOGGER = LoggerFactory.getLogger(TagUpdaterImage.class);

    private final TagValueImage tagValueImage;
    private final WordProcessor processor;

    public TagUpdaterImage(TagValueImage image, WordProcessor processor) {
        this.tagValueImage = image;
        this.processor = processor;
    }

    @Override
    public boolean update(Tag tag) {
        WordItemContainer container = tag.getParagraph();
        ReportImage image = tagValueImage.getImage();

        try {
            Run newImage = processor.newImage(image.asBytes());
            WordItems items = tag.getItems();
            processor.replaceBy(container, items, newImage);
        } catch (WordProcessorException e) {
            LOGGER.warn("Impossible to update image in report", e);
            return FAILED;
        }

        return SUCCESS;
    }
}
