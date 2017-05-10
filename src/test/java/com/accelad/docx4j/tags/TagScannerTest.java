package com.accelad.docx4j.tags;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.accelad.docx4j.facade.ListWordItems;
import com.accelad.docx4j.facade.Paragraph;
import com.accelad.docx4j.facade.Paragraphs;
import com.accelad.docx4j.facade.Run;
import com.accelad.docx4j.facade.Text;
import com.accelad.docx4j.facade.WordItem;
import com.accelad.docx4j.facade.WordItems;

public class TagScannerTest {

    private static final String TAG_NAME = "tagName";
    private static final String A_PARAGRAPH_WITH_TAG_NAME = "A paragraph ${" + TAG_NAME + "} tagged";
    private static final String TAG_NAME_WITH_DOT = "tag.name";
    private static final String TAG_NAME_WITH_PARENTHESIS = "tagnamewithparenthesis()";
    private static final String TAG_NAME_WITH_QUOTES = "tagname\"double\"and\'simple\'";
    private static final String TAG_NAME_WITH_NUMBER = "tagNameWithNumber0123456789";

    @Mock private Paragraph aParagraph;

    private TagScanner tagScanner;

    @Before
    public void initialize() {
        tagScanner = new TagScanner();

        initMocks(this);
        ListWordItems listWordItems = new ListWordItems();
        when(aParagraph.getContent()).thenReturn(listWordItems);
    }

    @Test
    public void should_create_tag_with_correct_tag_name_when_a_paragraph_contains_a_tag_name() {
        Paragraphs paragraphs = new Paragraphs();
        when(aParagraph.asString()).thenReturn(A_PARAGRAPH_WITH_TAG_NAME);
        paragraphs.add(aParagraph);

        Tags tags = tagScanner.scan(paragraphs);

        Tag tag = tags.iterator().next();
        assertThat(tag.getTagName().asString(), is(TAG_NAME));
    }

    @Test
    public void should_create_tag_with_correct_tag_name_when_a_paragraph_contains_only_a_tag_name() {
        Paragraphs paragraphs = new Paragraphs();
        when(aParagraph.asString()).thenReturn("${" + TAG_NAME + "}");
        paragraphs.add(aParagraph);

        Tags tags = tagScanner.scan(paragraphs);

        Tag tag = tags.iterator().next();
        assertThat(tag.getTagName().asString(), is(TAG_NAME));
    }

    @Test
    public void should_create_tag_with_correct_tag_name_when_a_paragraph_contains_a_tag_name_with_dot() {
        Paragraphs paragraphs = new Paragraphs();
        when(aParagraph.asString()).thenReturn("${" + TAG_NAME_WITH_DOT + "}");
        paragraphs.add(aParagraph);

        Tags tags = tagScanner.scan(paragraphs);

        Tag tag = tags.iterator().next();
        assertThat(tag.getTagName().asString(), is(TAG_NAME_WITH_DOT));
    }


    @Test
    public void should_create_tag_with_correct_tag_name_when_a_paragraph_contains_a_tag_name_with_number() {
        Paragraphs paragraphs = new Paragraphs();
        when(aParagraph.asString()).thenReturn("${" + TAG_NAME_WITH_NUMBER + "}");
        paragraphs.add(aParagraph);

        Tags tags = tagScanner.scan(paragraphs);

        Tag tag = tags.iterator().next();
        assertThat(tag.getTagName().asString(), is(TAG_NAME_WITH_NUMBER));
    }

    @Test
    public void should_create_tag_with_correct_tag_name_when_a_paragraph_contains_a_tag_name_with_parenthesis() {
        Paragraphs paragraphs = new Paragraphs();
        when(aParagraph.asString()).thenReturn("${" + TAG_NAME_WITH_PARENTHESIS + "}");
        paragraphs.add(aParagraph);

        Tags tags = tagScanner.scan(paragraphs);

        Tag tag = tags.iterator().next();
        assertThat(tag.getTagName().asString(), is(TAG_NAME_WITH_PARENTHESIS));
    }

    @Test
    public void should_create_tag_with_correct_tag_name_when_a_paragraph_contains_a_tag_name_with_quote() {
        Paragraphs paragraphs = new Paragraphs();
        when(aParagraph.asString()).thenReturn("${" + TAG_NAME_WITH_QUOTES + "}");
        paragraphs.add(aParagraph);

        Tags tags = tagScanner.scan(paragraphs);

        Tag tag = tags.iterator().next();
        assertThat(tag.getTagName().asString(), is(TAG_NAME_WITH_QUOTES));
    }

    @Test
    public void should_return_empty_tags_when_no_paragraph_contains_tag_identifier() {
        Paragraphs paragraphs = new Paragraphs();
        when(aParagraph.asString()).thenReturn("No tag in paragraph");
        paragraphs.add(aParagraph);

        Tags tags = tagScanner.scan(paragraphs);

        assertFalse(tags.iterator().hasNext());
    }

    @Test
    public void should_create_tag_with_one_word_item_when_tag_identifier_is_a_word_item() {
        Paragraphs paragraphs = new Paragraphs();
        when(aParagraph.asString()).thenReturn(A_PARAGRAPH_WITH_TAG_NAME);
        paragraphs.add(aParagraph);
        ListWordItems wordItems = new ListWordItems();
        Run wordItem1 = new Run();
        wordItem1.addText(new Text("A paragraph "));
        Run expectedItem = new Run();
        expectedItem.addText(new Text("${" + TAG_NAME + "}"));
        Run wordItem3 = new Run();
        wordItem3.addText(new Text(" tagged"));
        wordItems.add(wordItem1);
        wordItems.add(expectedItem);
        wordItems.add(wordItem3);
        when(aParagraph.getContent()).thenReturn(wordItems);

        Tags tags = tagScanner.scan(paragraphs);

        Tag tag = tags.iterator().next();
        WordItems items = tag.getItems();
        Iterator<WordItem> iterator = items.iterator();
        assertThat(iterator.next(), is(expectedItem));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void should_create_tag_with_word_items_when_tag_identifier_is_composed_by_multiple_word_item() {
        Paragraphs paragraphs = new Paragraphs();
        when(aParagraph.asString()).thenReturn(A_PARAGRAPH_WITH_TAG_NAME);
        paragraphs.add(aParagraph);
        ListWordItems wordItems = new ListWordItems();
        Run wordItem1 = new Run();
        wordItem1.addText(new Text("A paragraph "));
        Run wordItem2 = new Run();
        wordItem2.addText(new Text("${"));
        Run wordItem3 = new Run();
        wordItem3.addText(new Text(TAG_NAME));
        Run wordItem4 = new Run();
        wordItem4.addText(new Text("}"));
        Run wordItem5 = new Run();
        wordItem5.addText(new Text(" tagged"));
        wordItems.add(wordItem1);
        wordItems.add(wordItem2);
        wordItems.add(wordItem3);
        wordItems.add(wordItem4);
        wordItems.add(wordItem5);
        when(aParagraph.getContent()).thenReturn(wordItems);

        Tags tags = tagScanner.scan(paragraphs);

        Tag tag = tags.iterator().next();
        List<WordItem> wordItemsActual = getWordItems(tag.getItems());
        assertThat(wordItemsActual, hasSize(3));
        assertThat(wordItemsActual, hasItems(wordItem2, wordItem3, wordItem4));
    }

    private List<WordItem> getWordItems(WordItems items) {
        return StreamSupport.stream(items.spliterator(), false).collect(Collectors.toList());
    }
}