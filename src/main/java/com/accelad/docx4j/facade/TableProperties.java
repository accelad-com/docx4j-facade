package com.accelad.docx4j.facade;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.TblPr;

public class TableProperties implements WordItem {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    private final TblPr tblPr;

    public TableProperties() {
        tblPr = factory.createTblPr();
    }

    public TableProperties(TblPr tblPr) {
        this.tblPr = tblPr;
    }

    @Override
    public TblPr asDocx4JObject() {
        return tblPr;
    }

    public void setWidth(TableCellWidth width) {
        tblPr.setTblW(width.asDocx4JObject());
    }

    public void setBorders(TableBorders borders) {
        tblPr.setTblBorders(borders.asDocx4JObject());
    }
}
