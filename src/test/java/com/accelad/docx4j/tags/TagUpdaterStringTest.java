package com.accelad.docx4j.tags;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
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
import com.accelad.docx4j.tags.Tag;
import com.accelad.docx4j.tags.TagName;
import com.accelad.docx4j.tags.TagUpdaterString;
import com.accelad.docx4j.tags.TagValueString;

public class TagUpdaterStringTest {

    private static final TagValueString TAG_VALUE = new TagValueString("TEST_VALUE");
    @Mock private WordProcessor processor;
    private Paragraph container = new Paragraph();
    private IndexedWordItems itemsToReplace = new IndexedWordItems();
    private Tag tag = new Tag(new TagName("TEST_TAG"), container, itemsToReplace);
    private Run expectedItem;

    @Before
    public void initBeforeTest() throws Exception {
        initMocks(this);
        expectedItem = new Run();
        when(processor.newText(anyString())).thenReturn(expectedItem);
    }

    @Test
    public void should_replace_the_worditems_by_a_text_when_updating_the_tag() throws Exception {
        TagUpdaterString updater = new TagUpdaterString(TAG_VALUE, processor);

        updater.update(tag);

        verify(processor).replaceBy(tag.getParagraph(), tag.getItems(), expectedItem);
    }

    @Test
    public void should_return_true_when_updating_the_tag_was_done_successfully() throws Exception {
        TagUpdaterString updater = new TagUpdaterString(TAG_VALUE, processor);

        boolean result = updater.update(tag);

        assertThat(result, is(true));
    }
}
