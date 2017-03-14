package com.accelad.docx4j.facade;

import java.math.BigInteger;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.TblGridCol;

public class TableGridColumn implements WordItem {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    private final TblGridCol tblGridCol;

    public TableGridColumn(int value) {
        tblGridCol = factory.createTblGridCol();
        tblGridCol.setW(BigInteger.valueOf(value));
    }

    public TableGridColumn(TblGridCol tblGridCol) {
        this.tblGridCol = tblGridCol;
    }

    @Override
    public TblGridCol asDocx4JObject() {
        return tblGridCol;
    }

}
