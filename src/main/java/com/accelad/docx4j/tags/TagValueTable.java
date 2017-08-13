package com.accelad.docx4j.tags;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class TagValueTable implements TagValue {

    private final String title;
    private final TagValueTableRow headers;
    private final TableHeaderGroup[] headerGroups;
    private final List<TagValueTableRow> tableValues;

    public TagValueTable(String title, TagValueTableRow headers, List<TagValueTableRow> tableValues) {
        this(title, headers, null, tableValues);
    }

    public TagValueTable(String title, TagValueTableRow headers, TableHeaderGroup[] headerGroups,
                         List<TagValueTableRow> tableValues) {
        this.headerGroups = headerGroups;
        this.tableValues = tableValues;
        this.title = title;
        this.headers = headers;
    }

    public TagValueTableRow getHeaders() {
        return headers;
    }

    public List<TagValueTableRow> getRows() {
        return tableValues;
    }

    public int getColumnCount() {
        return headers.size();
    }

    public int getRowCount() {
        return tableValues.size();
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void accept(TagValueVisitor visitor) {
        visitor.visit(this);
    }

    public TableHeaderGroup[] getHeaderGroups() {
        return this.headerGroups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagValueTable that = (TagValueTable) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(headers, that.headers) &&
                Arrays.equals(headerGroups, that.headerGroups) &&
                Objects.equals(tableValues, that.tableValues);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, headers, headerGroups, tableValues);
    }

    @Override
    public String toString() {
        return "TagValueTable{" +
                "title='" + title + '\'' +
                ", headers=" + headers +
                ", headerGroups=" + Arrays.toString(headerGroups) +
                ", tableValues=" + tableValues +
                '}';
    }
}
