package com.accelad.docx4j.tags;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TagValueTableTest {

    @Test
    public void should_display_table_content_with_sub_arrays() {
        TagValueTableRow header = new TagValueTableRow( "header1", "header2" );
        List<TagValueTableRow> values = Arrays.asList(new TagValueTableRow( "01", "02" ), new TagValueTableRow( "A", "B" ));

        TagValueTable tagValueTable = new TagValueTable("title", header, values);

        assertEquals(
                "TagValueTable{title='title', headers=TagValueTableRow{values=[header1, header2]}, headerGroups=null, tableValues=[TagValueTableRow{values=[01, 02]}, TagValueTableRow{values=[A, B]}]}",
                tagValueTable.toString());

    }

}