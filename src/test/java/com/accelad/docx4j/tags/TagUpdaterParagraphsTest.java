package com.accelad.docx4j.tags;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Collections;

import org.docx4j.wml.P;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.accelad.docx4j.facade.IndexedWordItems;
import com.accelad.docx4j.facade.Paragraph;
import com.accelad.docx4j.facade.WordProcessor;
import com.accelad.docx4j.tags.Tag;
import com.accelad.docx4j.tags.TagName;
import com.accelad.docx4j.tags.TagUpdaterBlock;
import com.accelad.docx4j.tags.TagValue;
import com.accelad.docx4j.tags.TagValueBlock;
import com.accelad.docx4j.tags.TagValueString;
import com.accelad.docx4j.tags.TagValueTable;

public class TagUpdaterParagraphsTest {

    private static final TagValueTable TAG_VALUE_TABLE = new TagValueTable("Title", new String[] {"1", "2"}, new String[][] {{"1a", "2a"}});
    public static final TagValueString TAG_VALUE_STRING = new TagValueString("TEST");
    private static final TagValue[] TAG_VALUE_CONTENTS = new TagValue[] {TAG_VALUE_TABLE, TAG_VALUE_STRING};
    private static final TagValueBlock TAG_VALUE = new TagValueBlock(TAG_VALUE_CONTENTS);

    @Mock private WordProcessor processor;
    @Mock P p;
    private Paragraph container = new Paragraph();
    private IndexedWordItems itemsToReplace = new IndexedWordItems();
    private Tag tag = new Tag(new TagName("TEST_TAG"), container, itemsToReplace);
    private Paragraph expectedItem;

    @Before
    public void initBeforeTest() throws Exception {
        initMocks(this);

        expectedItem = mock(Paragraph.class);

        when(processor.newStyledParagraphOfText(anyString(), anyString())).thenReturn(expectedItem);
        when(processor.newStyledParagraphOfText(anyString(), anyString())).thenReturn(expectedItem);
        when(p.getParent()).thenReturn(Collections.EMPTY_LIST);
        when(expectedItem.asDocx4JObject()).thenReturn(p);
    }

    @Test
    public void should_create_two_paragraphs() throws Exception {
        TagUpdaterBlock updater = new TagUpdaterBlock(TAG_VALUE, processor);

        updater.update(tag);

        verify(processor, times(2)).add(anyInt(), eq(expectedItem), eq(container));
    }

    @Test
    public void should_return_true_when_updating_the_tag_was_done_successfully() throws Exception {
        TagUpdaterBlock updater = new TagUpdaterBlock(TAG_VALUE, processor);

        boolean result = updater.update(tag);

        assertThat(result, is(true));
    }
}
