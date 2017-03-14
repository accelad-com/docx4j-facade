package com.accelad.docx4j.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.accelad.docx4j.tags.Tag;
import com.accelad.docx4j.tags.TagName;
import com.accelad.docx4j.tags.TagUpdater;
import com.accelad.docx4j.tags.TagUpdaterFactory;
import com.accelad.docx4j.tags.TagValue;
import com.accelad.docx4j.tags.TagValueUpdater;
import com.accelad.docx4j.tags.TagValues;

public class TagUpdaterTest {

    private static final boolean UPDATE_IMPOSSIBLE = false;
    private static final boolean NOT_UPDATED = false;
    private static final boolean UPDATED = true;
    private static final boolean CAN_UPDATE = true;
    private static final TagName TAGNAME = new TagName("Test_Name");
    @Mock TagUpdaterFactory factory;
    @Mock Tag tag;
    @Mock TagValue correspondingValue;
    @Mock TagValueUpdater valueUpdater;


    @Before
    public void initBeforeTest() throws Exception {
        initMocks(this);
        when(tag.getTagName()).thenReturn(TAGNAME);
        when(factory.getUpdater(correspondingValue)).thenReturn(valueUpdater);
    }

    @Test
    public void should_return_updated_when_the_given_tag_has_a_corresponding_value_and_the_valueUpdater_can_update()
            throws Exception {
        when(valueUpdater.update(tag)).thenReturn(CAN_UPDATE);

        TagValues values = new TagValues();
        values.put(TAGNAME, correspondingValue);
        TagUpdater updater = new TagUpdater(factory, values);

        boolean result = updater.update(tag);
        assertThat(result, is(UPDATED));
    }

    @Test
    public void should_return_not_updated_when_the_given_tag_does_not_have_a_matching_value()
            throws Exception {
        TagValues values = new TagValues();

        TagUpdater updater = new TagUpdater(factory, values);

        boolean result = updater.update(tag);
        assertThat(result, is(NOT_UPDATED));
    }

    @Test
    public void should_return_not_updated_when_the_given_tag_has_a_corresponding_value_but_the_valueUpdater_can_not_perform_the_update()
            throws Exception {
        when(valueUpdater.update(tag)).thenReturn(UPDATE_IMPOSSIBLE);

        TagValues values = new TagValues();
        values.put(TAGNAME, correspondingValue);
        TagUpdater updater = new TagUpdater(factory, values);

        boolean result = updater.update(tag);
        assertThat(result, is(NOT_UPDATED));
    }

}
