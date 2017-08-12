package com.accelad.docx4j.tags;

import com.accelad.docx4j.facade.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TagScannerTest {

    private static final String TAG_NAME = "tagName";
    private static final String TAG_NAME_WITH_DOT = "tag.name";
    private static final String TAG_NAME_WITH_PARENTHESIS = "tagnamewithparenthesis()";
    private static final String TAG_NAME_WITH_QUOTES = "tagname\"double\"and\'simple\'";
    private static final String TAG_NAME_WITH_NUMBER = "tagNameWithNumber0123456789";
    private static final String TAG_START = "${";
    private static final String TAG_END = "}";

    private TagScanner tagScanner;

    @Before
    public void initialize() {
        tagScanner = new TagScanner();
    }

    @Test
    public void should_create_tag_with_correct_tag_name_when_a_paragraph_contains_only_a_tag_name() {
        Paragraphs paragraphs = new Paragraphs();
        Paragraph aParagraph = createParagraphWithTag(TAG_START + TAG_NAME + TAG_END);
        paragraphs.add(aParagraph);

        Tags tags = tagScanner.scan(paragraphs);

        IndexedWordItems wordItemsList = new IndexedWordItems();
        Tag expectedTag = new Tag(new TagName(TAG_NAME), aParagraph, wordItemsList);
        assertThat(tags.contains(expectedTag), equalTo(true));
    }

    @Test
    public void should_recognize_a_different_tag_format_when_a_different_regex_is_given() {
        Paragraphs paragraphs = new Paragraphs();
        Paragraph aParagraph = createParagraphWithTag("%[" + TAG_NAME + "]");
        paragraphs.add(aParagraph);

        Tags tags = new TagScanner("((\\%\\[[^\\]]*\\]))").scan(paragraphs);

        Tag tag = tags.iterator().next();
        assertThat(tag.getTagName().asString(), is(TAG_NAME));
    }

    @Test
    public void should_create_tag_with_correct_tag_name_when_a_paragraph_contains_a_tag_name_with_dot() {
        Paragraphs paragraphs = new Paragraphs();
        Paragraph aParagraph = createParagraphWithTag(TAG_START + TAG_NAME_WITH_DOT + TAG_END);
        paragraphs.add(aParagraph);

        Tags tags = tagScanner.scan(paragraphs);

        IndexedWordItems wordItemsList = new IndexedWordItems();
        Tag expectedTag = new Tag(new TagName(TAG_NAME_WITH_DOT), aParagraph, wordItemsList);
        assertThat(tags.contains(expectedTag), equalTo(true));
    }

    @Test
    public void should_create_tag_with_correct_tag_name_when_a_paragraph_contains_a_tag_name_with_number() {
        Paragraphs paragraphs = new Paragraphs();
        Paragraph aParagraph = createParagraphWithTag(TAG_START + TAG_NAME_WITH_NUMBER + TAG_END);
        paragraphs.add(aParagraph);

        Tags tags = tagScanner.scan(paragraphs);

        IndexedWordItems wordItemsList = new IndexedWordItems();
        Tag expectedTag = new Tag(new TagName(TAG_NAME_WITH_NUMBER), aParagraph, wordItemsList);
        assertThat(tags.contains(expectedTag), equalTo(true));
    }

    @Test
    public void should_create_tag_with_correct_tag_name_when_a_paragraph_contains_a_tag_name_with_parenthesis() {
        Paragraphs paragraphs = new Paragraphs();
        Paragraph aParagraph = createParagraphWithTag(TAG_START + TAG_NAME_WITH_PARENTHESIS + TAG_END);
        paragraphs.add(aParagraph);

        Tags tags = tagScanner.scan(paragraphs);

        IndexedWordItems wordItemsList = new IndexedWordItems();
        Tag expectedTag = new Tag(new TagName(TAG_NAME_WITH_PARENTHESIS), aParagraph, wordItemsList);
        assertThat(tags.contains(expectedTag), equalTo(true));
    }

    @Test
    public void should_create_tag_with_correct_tag_name_when_a_paragraph_contains_a_tag_name_with_quote() {
        Paragraphs paragraphs = new Paragraphs();
        Paragraph aParagraph = createParagraphWithTag(TAG_START + TAG_NAME_WITH_QUOTES + TAG_END);
        paragraphs.add(aParagraph);

        Tags tags = tagScanner.scan(paragraphs);

        IndexedWordItems wordItemsList = new IndexedWordItems();
        Tag expectedTag = new Tag(new TagName(TAG_NAME_WITH_QUOTES), aParagraph, wordItemsList);

        assertThat(tags.contains(expectedTag), equalTo(true));
    }

    @Test
    public void should_return_empty_tags_when_no_paragraph_contains_tag_identifier() {
        Paragraphs paragraphs = new Paragraphs();
        Paragraph aParagraph = new Paragraph();
        paragraphs.add(aParagraph);

        Tags tags = tagScanner.scan(paragraphs);

        assertTrue(tags.isEmpty());
    }

    @Test
    public void should_create_tag_with_one_word_item_when_tag_identifier_is_a_word_item() {
        Paragraphs paragraphs = new Paragraphs();
        Paragraph aParagraph = new Paragraph();
        paragraphs.add(aParagraph);
        Run beginOfTheParagraphText = createText("A paragraph ");
        Run expectedItem = createText(TAG_START + TAG_NAME + TAG_END);
        Run endOfParagraphText = createText(" tagged");
        aParagraph.addItem(beginOfTheParagraphText);
        aParagraph.addItem(expectedItem);
        aParagraph.addItem(endOfParagraphText);

        Tags tags = tagScanner.scan(paragraphs);

        IndexedWordItems wordItemsList = new IndexedWordItems();
        Tag expectedTag = new Tag(new TagName(TAG_NAME), aParagraph, wordItemsList);

        assertThat(tags.contains(expectedTag), equalTo(true));
    }

    @Test
    public void should_create_tag_with_word_items_when_tag_identifier_is_composed_by_multiple_word_item_in_middle_of_paragraph() {
        Paragraphs paragraphs = new Paragraphs();
        Paragraph aParagraph = new Paragraph();
        paragraphs.add(aParagraph);
        Run beginOfTheParagraphText = createText("A paragraph ");
        Run tagStartText = createText(TAG_START);
        Run tagNameText = createText(TAG_NAME);
        Run tagEndText = createText(TAG_END);
        Run endOfParagraphText = createText(" tagged");
        aParagraph.addItem(beginOfTheParagraphText);
        aParagraph.addItem(tagStartText);
        aParagraph.addItem(tagNameText);
        aParagraph.addItem(tagEndText);
        aParagraph.addItem(endOfParagraphText);

        Tags tags = tagScanner.scan(paragraphs);

        IndexedWordItems wordItemsList = new IndexedWordItems();
        Tag expectedTag = new Tag(new TagName(TAG_NAME), aParagraph, wordItemsList);

        assertThat(tags.contains(expectedTag), equalTo(true));
    }

    @Test
    public void should_create_tag_when_tag_identifier_is_divided_in_two_parts() {
        String firstPartNameAsString = "FIRST PART";
        String secondPartNameAsString = "SECOND PART";

        Paragraphs paragraphs = new Paragraphs();
        Paragraph aParagraph = new Paragraph();
        paragraphs.add(aParagraph);
        Run beginOfParagraphText = createText("A paragraph ");
        Run firstPartOfTag = createText(TAG_START + firstPartNameAsString);
        Run secondPartOfTag = createText(secondPartNameAsString + TAG_END);
        Run endOfParagraphText = createText(" tagged");
        aParagraph.addItem(beginOfParagraphText);
        aParagraph.addItem(firstPartOfTag);
        aParagraph.addItem(secondPartOfTag);
        aParagraph.addItem(endOfParagraphText);

        Tags tags = tagScanner.scan(paragraphs);

        IndexedWordItems wordItemsList = new IndexedWordItems();
        Tag expectedTag = new Tag(new TagName(firstPartNameAsString + secondPartNameAsString), aParagraph, wordItemsList);

        assertThat(tags.contains(expectedTag), equalTo(true));
    }

    private Run createText(String text) {
        Run runItemWithText = new Run();
        runItemWithText.addText(new Text(text));
        return runItemWithText;
    }


    private Paragraph createParagraphWithTag(String text) {
        Paragraph paragraph = new Paragraph();
        Run expectedItem = createText(text);
        paragraph.addItem(expectedItem);
        return paragraph;
    }

}