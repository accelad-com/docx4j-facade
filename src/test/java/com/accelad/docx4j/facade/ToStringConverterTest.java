package com.accelad.docx4j.facade;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.docx4j.wml.R;
import org.docx4j.wml.Text;
import org.junit.Test;

import com.accelad.docx4j.facade.ToStringConverter;
import com.accelad.docx4j.facade.WordItem;

public class ToStringConverterTest {

    private static final String EXPECTED_TEXT = "testValue";

    @Test
    public void should_return_the_text_content_of_a_docx4J_object_when_a_docxObject_containing_some_text_is_given()
            throws Exception {
        WordItem item = createWordItemWithText();

        String result = ToStringConverter.toString(item);

        assertThat(result, is(EXPECTED_TEXT));
    }

    private WordItem createWordItemWithText() {
        WordItem item = mock(WordItem.class);
        Text textDocxObject = new Text();
        textDocxObject.setValue(EXPECTED_TEXT);
        when(item.asDocx4JObject()).thenReturn(textDocxObject);
        return item;
    }

    @Test
    public void should_return_an_empty_string_when_a_docxObject_containing_no_text_is_given()
            throws Exception {
        WordItem item = createWordItemWithoutText();

        String result = ToStringConverter.toString(item);

        assertThat(result, isEmptyString());
    }

    private WordItem createWordItemWithoutText() {
        WordItem item = mock(WordItem.class);
        R textDocxObject = new R();
        when(item.asDocx4JObject()).thenReturn(textDocxObject);
        return item;
    }

}
