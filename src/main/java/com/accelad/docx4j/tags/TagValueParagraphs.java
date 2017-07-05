package com.accelad.docx4j.tags;

import java.util.Arrays;

public class TagValueParagraphs implements TagValue {

    private final String[] titles;
    private final String[] styles;
    private final TagValue[] contents;

    public TagValueParagraphs(String[] titles, String[] styles, TagValue[] contents) {
        this.titles = titles;
        this.styles = styles;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return titles.toString();
    }

    @Override
    public void accept(TagValueVisitor visitor) {
        visitor.visit(this);
    }

    public String[] getTitles() {
        return titles;
    }

    public TagValue[] getContents() {
        return contents;
    }

    public String[] getStyles() {
        return styles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TagValueParagraphs that = (TagValueParagraphs) o;

        if (!Arrays.equals(titles, that.titles))
            return false;
        if (!Arrays.equals(styles, that.styles))
            return false;
        return Arrays.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(titles);
        result = 31 * result + Arrays.hashCode(styles);
        result = 31 * result + Arrays.hashCode(contents);
        return result;
    }
}
