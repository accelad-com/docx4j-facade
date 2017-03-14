package com.accelad.docx4j.facade;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.TblGrid;

public class TableGrid implements WordItem {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    private final TblGrid tblGrid;

    public TableGrid() {
        tblGrid = factory.createTblGrid();
    }

    public TableGrid(TblGrid tblGrid) {
        this.tblGrid = tblGrid;
    }

    @Override
    public TblGrid asDocx4JObject() {
        return tblGrid;
    }

    public void addGridColumn(TableGridColumn column) {
        tblGrid.getGridCol().add(column.asDocx4JObject());
    }

}
