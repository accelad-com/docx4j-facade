package com.accelad.docx4j.tags;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.accelad.docx4j.facade.IndexedWordItems;
import com.accelad.docx4j.facade.Paragraph;
import com.accelad.docx4j.facade.Paragraphs;
import com.accelad.docx4j.facade.ToStringConverter;
import com.accelad.docx4j.facade.WordItem;
import com.accelad.docx4j.facade.WordItems;

public class TagScanner {

    private static final String TAG_START = "${";
    private static final String TAG_END = "}";

    private static final String REGEX = "(\\$\\{[^}]*\\})";
    private final Pattern PATTERN;

    public TagScanner() {
        PATTERN = Pattern.compile(REGEX);
    }

    public TagScanner(String regex) {
        PATTERN = Pattern.compile(regex);
    }

    public Tags scan(Paragraphs paragraphs) {
        Tags tags = new Tags();
        for (Paragraph paragraph : paragraphs) {
            String paragraphAsString = paragraph.asString();
            Matcher match = PATTERN.matcher(paragraphAsString);
            if (match.matches()) {
                TagName tagName = createTagName(paragraphAsString);
                IndexedWordItems content = paragraph.getIndexedContent();
                Tag tag = new Tag(tagName, paragraph, content);
                tags.add(tag);
            } else if (match.find()) {
                String tagFound = match.group(0);
                TagName tagName = createTagName(tagFound);
                IndexedWordItems items = getWordItemForTag(paragraph);
                tags.add(new Tag(tagName, paragraph, items));
            }
        }
        return tags;
    }

    private TagName createTagName(String txt) {
        String tag = txt.substring(TAG_START.length(), txt.length() - TAG_END.length());
        return new TagName(tag);
    }

    private IndexedWordItems getWordItemForTag(Paragraph paragraph) {
        boolean started = false;
        IndexedWordItems items = new IndexedWordItems();
        WordItems content = paragraph.getContent();
        // the order of conditions matter !
        for (WordItem item : content) {
            Integer position = paragraph.getPosition(item);
            String txt = ToStringConverter.toString(item);
            Matcher match = PATTERN.matcher(txt);
            if (match.matches()) {
                items.put(item, position);
            } else {
                if (txt.equals(TAG_START)) {
                    started = true;
                }
                if (started) {
                    items.put(item, position);
                }
                if (started && txt.equals(TAG_END)) {
                    started = false;
                }
            }
        }
        return items;
    }
}
