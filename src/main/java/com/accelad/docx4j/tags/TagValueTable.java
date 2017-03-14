package com.accelad.docx4j.tags;

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
}
