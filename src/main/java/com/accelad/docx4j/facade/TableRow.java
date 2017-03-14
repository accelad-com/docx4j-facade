package com.accelad.docx4j.facade;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.Tr;

public class TableRow implements WordItem {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    private final Tr tr;

    public TableRow() {
        this.tr = factory.createTr();
    }

    public TableRow(Tr tr) {
        this.tr = tr;
    }

    @Override
    public Object asDocx4JObject() {
        return tr;
    }

    public void addCell(TableCell cell) {
        tr.getContent().add(cell.asDocx4JObject());
    }
}
