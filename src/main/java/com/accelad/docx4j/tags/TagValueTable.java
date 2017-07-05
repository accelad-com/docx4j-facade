package com.accelad.docx4j.tags;

import java.util.Arrays;

public class TagValueTable implements TagValue {

    private final String title;
    private final String[] headers;
    private final TableHeaderGroup[] headerGroups;
    private final String[][] tableValues;

    public TagValueTable(String title, String[] headers, String[][] tableValues) {
        this(title, headers, null, tableValues);
    }

    public TagValueTable(String title, String[] headers, TableHeaderGroup[] headerGroups, String[][] tableValues) {
        this.headerGroups = headerGroups;
        this.tableValues = tableValues;
        this.title = title;
        this.headers = headers;
    }

    public String[] getHeaders() {
        return headers;
    }

    public String[][] getValue() {
        return tableValues;
    }

    public int getColumnCount() {
        return headers.length;
    }

    public int getRowCount() {
        return tableValues.length;
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TagValueTable that = (TagValueTable) o;

        if (title != null ? !title.equals(that.title) : that.title != null)
            return false;
        if (!Arrays.equals(headers, that.headers))
            return false;
        if (!Arrays.equals(headerGroups, that.headerGroups))
            return false;
        return Arrays.deepEquals(tableValues, that.tableValues);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(headers);
        result = 31 * result + Arrays.hashCode(headerGroups);
        result = 31 * result + Arrays.deepHashCode(tableValues);
        return result;
    }
}
