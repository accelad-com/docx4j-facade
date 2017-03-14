package com.accelad.docx4j.facade;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.Tbl;

public class Table implements WordItem {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    private final Tbl tbl;

    public Table() {
        this.tbl = factory.createTbl();
    }

    public Table(Tbl tbl) {
        this.tbl = tbl;
    }

    @Override
    public Tbl asDocx4JObject() {
        return tbl;
    }

    public void addRow(TableRow row) {
        tbl.getContent().add(row.asDocx4JObject());
    }

    public void setProperties(TableProperties properties) {
        tbl.setTblPr(properties.asDocx4JObject());
    }

    public void setGrid(TableGrid grid) {
        tbl.setTblGrid(grid.asDocx4JObject());
    }


}
