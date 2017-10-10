package com.accelad.docx4j.tags;

import com.accelad.docx4j.facade.WordProcessor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.typeCompatibleWith;
import static org.mockito.MockitoAnnotations.initMocks;

public class TagUpdaterFactoryTest {
    private static int columnWidth = 1000;
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

        TagValue value = new TagValueTable("title", null, null, columnWidth);
        TagValueUpdater updater = factory.getUpdater(value);
        assertThat(updater.getClass(),
                typeCompatibleWith(TagUpdaterTable.class));
    }

}
