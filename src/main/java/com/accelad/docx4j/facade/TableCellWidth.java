package com.accelad.docx4j.facade;

import java.math.BigInteger;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.TblWidth;

public class TableCellWidth implements WordItem {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    private final TblWidth tblWidth;

    public TableCellWidth(BigInteger value) {
        tblWidth = factory.createTblWidth();
        tblWidth.setW(value);
    }

    public TableCellWidth(int value, String type) {
        tblWidth = factory.createTblWidth();
        tblWidth.setW(BigInteger.valueOf(value));
        tblWidth.setType(type);
    }

    public TableCellWidth(TblWidth tblWidth) {
        this.tblWidth = tblWidth;
    }

    @Override
    public TblWidth asDocx4JObject() {
        return tblWidth;
    }
}
