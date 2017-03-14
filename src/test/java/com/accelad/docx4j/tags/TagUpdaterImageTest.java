package com.accelad.docx4j.tags;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.accelad.docx4j.facade.IndexedWordItems;
import com.accelad.docx4j.facade.Paragraph;
import com.accelad.docx4j.facade.Run;
import com.accelad.docx4j.facade.WordProcessor;
import com.accelad.docx4j.tags.ReportImage;
import com.accelad.docx4j.tags.Tag;
import com.accelad.docx4j.tags.TagName;
import com.accelad.docx4j.tags.TagUpdaterImage;
import com.accelad.docx4j.tags.TagValueImage;

public class TagUpdaterImageTest {

    private static final byte[] imageData = new byte[] {};
    private static final TagValueImage TAG_VALUE = new TagValueImage(new ReportImage(imageData));

    @Mock private WordProcessor processor;
    private Paragraph container = new Paragraph();
    private IndexedWordItems itemsToReplace = new IndexedWordItems();
    private Tag tag = new Tag(new TagName("TEST_TAG"), container, itemsToReplace);
    private Run expectedItem;

    @Before
    public void initBeforeTest() throws Exception {
        initMocks(this);
        expectedItem = new Run();
        when(processor.newImage(imageData)).thenReturn(expectedItem);
    }

    @Test
    public void should_replace_the_worditems_by_an_image_when_updating_the_tag() throws Exception {
        TagUpdaterImage updater = new TagUpdaterImage(TAG_VALUE, processor);

        updater.update(tag);

        verify(processor).replaceBy(tag.getParagraph(), tag.getItems(), expectedItem);
    }

    @Test
    public void should_return_true_when_updating_the_tag_was_done_successfully() throws Exception {
        TagUpdaterImage updater = new TagUpdaterImage(TAG_VALUE, processor);

        boolean result = updater.update(tag);

        assertThat(result, is(true));
    }

}
