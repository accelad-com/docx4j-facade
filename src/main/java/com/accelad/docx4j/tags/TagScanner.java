package com.accelad.docx4j.tags;

import com.accelad.docx4j.facade.IndexedWordItems;
import com.accelad.docx4j.facade.Paragraph;
import com.accelad.docx4j.facade.Paragraphs;
import com.accelad.docx4j.facade.ToStringConverter;
import com.accelad.docx4j.facade.WordItem;
import com.accelad.docx4j.facade.WordItems;

public class TagScanner {

    private static final String TAG_END = "}";
    private static final String TAG_START = "${";

    public Tags scan(Paragraphs paragraphs) {

        Tags tags = new Tags();
        for (Paragraph paragraph : paragraphs) {
            String txt = paragraph.asString();

            if (txt.startsWith(TAG_START)) {
                TagName tagName = createTagName(txt);
                IndexedWordItems content = paragraph.getIndexedContent();
                Tag tag = new Tag(tagName, paragraph, content);
                tags.add(tag);
            } else if (txt.contains(TAG_START)) {
                IndexedWordItems items = getWordItemForTag(paragraph);
                TagName tagName = getTagName(paragraph);
                tags.add(new Tag(tagName, paragraph, items));
            }
        }
        return tags;
    }

    private TagName createTagName(String txt) {
        txt = txt.substring(2, txt.length() - 1);
        TagName tagName = new TagName(txt);
        return tagName;
    }

    private TagName getTagName(Paragraph paragraph) {
        StringBuilder stringBuffer = new StringBuilder();
        boolean started = false;

        for (WordItem item : paragraph.getContent()) {
            String extractedText = ToStringConverter.toString(item);

            if (extractedText.equals(TAG_START)) {
                started = true;
            }

            if (started) {
                stringBuffer.append(extractedText);
            }

            if (started && extractedText.equals(TAG_END)) {
                started = false;
            }
        }
        return createTagName(stringBuffer.toString());
    }

    private IndexedWordItems getWordItemForTag(Paragraph paragraph) {
        boolean started = false;

        IndexedWordItems items = new IndexedWordItems();

        WordItems content = paragraph.getContent(); // the order matter !
        for (WordItem item : content) {
            Integer position = paragraph.getPosition(item);

            String txt = ToStringConverter.toString(item);
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
        return items;
    }
}
