package com.accelad.docx4j.tags;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TagValueTableTest {

    @Test
    public void should_display_table_content_with_sub_arrays() {
        String[] header = { "header1", "header2" };
        String[][] values = { { "01", "02" }, { "A", "B" } };

        TagValueTable tagValueTable = new TagValueTable("title", header, values);

        assertEquals(
                "TagValueTable{title='title', headers=[header1, header2], headerGroups=null, tableValues=[01, 02], [A, B]}",
                tagValueTable.toString());

    }

}