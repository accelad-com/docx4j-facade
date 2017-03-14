package com.accelad.docx4j.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.accelad.docx4j.facade.WordProcessor;
import com.accelad.docx4j.tags.ReportImage;
import com.accelad.docx4j.tags.TagUpdaterFactory;
import com.accelad.docx4j.tags.TagUpdaterImage;
import com.accelad.docx4j.tags.TagUpdaterString;
import com.accelad.docx4j.tags.TagUpdaterTable;
import com.accelad.docx4j.tags.TagValue;
import com.accelad.docx4j.tags.TagValueImage;
import com.accelad.docx4j.tags.TagValueString;
import com.accelad.docx4j.tags.TagValueTable;
import com.accelad.docx4j.tags.TagValueUpdater;

public class TagUpdaterFactoryTest {
    
    @Mock WordProcessor processor;
     
    @Before
    public void initBeforeTest() throws Exception {
        initMocks(this);

    }

    @Test
    public void should_return_an_image_updater_when_a_tagValueImage_is_given() throws Exception {
        TagUpdaterFactory factory = new TagUpdaterFactory(processor);
    
        TagValue value = new TagValueImage(new ReportImage(new byte[] {}));
        TagValueUpdater updater = factory.getUpdater(value);
        assertThat(updater.getClass(), typeCompatibleWith(TagUpdaterImage.class));
    }

    @Test
    public void should_return_a_string_updater_when_a_tagValueString_is_given() throws Exception {
        TagUpdaterFactory factory = new TagUpdaterFactory(processor);

        TagValue value = new TagValueString("azerty");
        TagValueUpdater updater = factory.getUpdater(value);
        assertThat(updater.getClass(),
                typeCompatibleWith(TagUpdaterString.class));
    }

    @Test
    public void should_return_a_table_updater_when_a_tagValueTable_is_given()
            throws Exception {
        TagUpdaterFactory factory = new TagUpdaterFactory(processor);

        TagValue value = new TagValueTable("title", null, null);
        TagValueUpdater updater = factory.getUpdater(value);
        assertThat(updater.getClass(),
                typeCompatibleWith(TagUpdaterTable.class));
    }

}
