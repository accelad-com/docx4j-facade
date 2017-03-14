package com.accelad.docx4j.tags;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.accelad.docx4j.facade.IndexedWordItems;
import com.accelad.docx4j.facade.Paragraph;
import com.accelad.docx4j.facade.WordItem;
import com.accelad.docx4j.facade.WordItems;

public class Tag {

    private final TagName tagName;

    private final Paragraph paragraph;
    private IndexedWordItems indexedWordItems;

    public Tag(TagName tagName, Paragraph paragraph, WordItem item, int position) {
        this.tagName = tagName;
        this.paragraph = paragraph;
        this.indexedWordItems = new IndexedWordItems();
        this.indexedWordItems.put(item, position);
    }

    public Tag(TagName tagName, Paragraph paragraph, IndexedWordItems items) {
        this.tagName = tagName;
        this.paragraph = paragraph;
        this.indexedWordItems = items;
    }

    public Paragraph getParagraph() {
        return paragraph;
    }

    public TagName getTagName() {
        return tagName;
    }

    public WordItems getItems() {
        return indexedWordItems;
    }

    public int getPosition(WordItem item) {
        return indexedWordItems.getPosition(item);
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Tag)) {
            return false;
        }
        Tag castOther = (Tag) other;
        return new EqualsBuilder().append(tagName, castOther.tagName).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(tagName).toHashCode();
    }

}
