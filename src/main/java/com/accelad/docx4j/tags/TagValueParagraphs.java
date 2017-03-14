package com.accelad.docx4j.tags;

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
}
