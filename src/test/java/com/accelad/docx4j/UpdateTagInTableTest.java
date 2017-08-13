package com.accelad.docx4j;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.accelad.docx4j.tags.*;
import org.apache.commons.io.IOUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.junit.Before;
import org.junit.Test;

import com.accelad.docx4j.facade.Paragraphs;
import com.accelad.docx4j.facade.WordProcessor;
import com.accelad.docx4j.facade.WordProcessorDocx4J;

public class UpdateTagInTableTest {

    private WordprocessingMLPackage wordprocessingMLPackageWithTable;
    private Tag tag;

    @Before
    public void readFileAndGetTags() throws Docx4JException {
        InputStream resourceAsStream = this.getClass()
                .getResourceAsStream("/docx/test_with_tag_in_table.docx");
        wordprocessingMLPackageWithTable = WordprocessingMLPackage.load(resourceAsStream);

        WordProcessor processor = new WordProcessorDocx4J(wordprocessingMLPackageWithTable);
        Paragraphs paragraphs = processor.getParagraphs();

        tag = new TagScanner().scan(paragraphs).iterator().next();
    }

    @Test
    public void should_update_tag_in_a_table_with_a_string() {
        TagValue key = new TagValueString("plip");
        TagUpdater updater = getTagUpdaterForTagValue(wordprocessingMLPackageWithTable, key);

        boolean isUpdated = updater.update(tag);

        assertTrue(isUpdated);
    }

    @Test
    public void should_update_tag_in_a_table_with_an_image() throws IOException {
        TagValue key = new TagValueImage(getCircuitImage());
        TagUpdater updater = getTagUpdaterForTagValue(wordprocessingMLPackageWithTable, key);

        boolean isUpdated = updater.update(tag);

        assertTrue(isUpdated);
    }

    private ReportImage getCircuitImage() throws IOException {
        byte[] bytes = IOUtils.toByteArray(
                this.getClass().getResourceAsStream("/images/canard.png"));
        return new ReportImage(bytes);
    }

    @Test
    public void should_update_tag_in_a_table_with_a_table() {
        TagValueTableRow headers = new TagValueTableRow("header1", "header2" );
        List<TagValueTableRow> values = Arrays.asList( new TagValueTableRow( "values11", "values12" ),
                new TagValueTableRow( "values21", "values22" ) );
        TagValue key = new TagValueTable("the title", headers, values);
        TagUpdater updater = getTagUpdaterForTagValue(wordprocessingMLPackageWithTable, key);

        boolean isUpdated = updater.update(tag);

        assertTrue(isUpdated);
    }

    private TagUpdater getTagUpdaterForTagValue(WordprocessingMLPackage wordprocessingMLPackage,
            TagValue key) {
        TagUpdaterFactory factory = new TagUpdaterFactory(
                new WordProcessorDocx4J(wordprocessingMLPackage));
        TagValues values = new TagValues();
        values.put(new TagName("theTagInATable"), key);
        return new TagUpdater(factory, values);
    }
}
