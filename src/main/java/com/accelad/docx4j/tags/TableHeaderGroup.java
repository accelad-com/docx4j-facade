package com.accelad.docx4j.tags;

public class TableHeaderGroup {
    private String id;
    private String title;
    private int rowspan = 1;

    public TableHeaderGroup(String id, String title, int rowspan) {
        this.id = id;
        this.title = title;
        this.rowspan = rowspan;
    }

    public static TableHeaderGroup createEmptyGroup(String id) {
        return new TableHeaderGroup(id, null, 1);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getRowspan() {
        return rowspan;
    }

    public void setRowspan(int rowspan) {
        this.rowspan = rowspan;
    }
}
