package com.accelad.docx4j.tags;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.accelad.docx4j.facade.IndexedWordItems;
import com.accelad.docx4j.facade.Paragraph;
import com.accelad.docx4j.facade.Table;
import com.accelad.docx4j.tags.Tag;
import com.accelad.docx4j.tags.TagName;
import com.accelad.docx4j.tags.TagUpdaterTable;
import com.accelad.docx4j.tags.TagValueTable;

public class TagUpdaterTableTest {
    private static final TagValueTable TAG_VALUE = new TagValueTable("My title", new String[] {}, new String[][] {});

    @Mock private Paragraph container;
    private IndexedWordItems itemsToReplace = new IndexedWordItems();
    private Tag tag;

    @Before
    public void initBeforeTest() throws Exception {
        initMocks(this);
        tag = new Tag(new TagName("TEST_TAG"), container, itemsToReplace);
    }

    @Test
    public void should_remove_all_worditems_of_tag_when_updating_the_tag() throws Exception {
        TagUpdaterTable updater = new TagUpdaterTable(TAG_VALUE);

        updater.update(tag);

        verify(container).removeAll(tag.getItems());
    }

    @Test
    public void should_add_a_table_in_parent_container_when_updating_the_tag() throws Exception {
        TagUpdaterTable updater = new TagUpdaterTable(TAG_VALUE);

        updater.update(tag);

        verify(container).addItemInTheParentContainer(any(Table.class));
    }

    @Test
    public void should_return_true_when_updating_the_tag_is_completed() throws Exception {
        TagUpdaterTable updater = new TagUpdaterTable(TAG_VALUE);

        boolean result = updater.update(tag);

        assertThat(result, is(true));
    }
}
