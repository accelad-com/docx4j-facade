package com.accelad.docx4j.facade;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.TcPr;

public class TableCellProperties implements WordItem {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    private final TcPr tcPr;

    public TableCellProperties() {
        tcPr = factory.createTcPr();
    }

    public TableCellProperties(TcPr tcPr) {
        this.tcPr = tcPr;
    }

    @Override
    public TcPr asDocx4JObject() {
        return tcPr;
    }

    public void setWidth(TableCellWidth width) {
        tcPr.setTcW(width.asDocx4JObject());
    }

    public void setHorizontalMerge(HorizontalMerge merge) {
        tcPr.setHMerge(merge.asDocx4JObject());
    }
}
