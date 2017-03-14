package com.accelad.docx4j.facade;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.TblBorders;

public class TableBorders implements WordItem {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    private final TblBorders tblBorders;

    public TableBorders() {
        tblBorders = factory.createTblBorders();
    }

    public TableBorders(TblBorders tblBorders) {
        this.tblBorders = tblBorders;
    }

    @Override
    public TblBorders asDocx4JObject() {
        return tblBorders;
    }

    public void setTop(Border value) {
        tblBorders.setTop(value.asDocx4JObject());
    }

    public void setLeft(Border value) {
        tblBorders.setLeft(value.asDocx4JObject());
    }

    public void setBottom(Border value) {
        tblBorders.setBottom(value.asDocx4JObject());
    }

    public void setRight(Border value) {
        tblBorders.setRight(value.asDocx4JObject());
    }

    public void setInsideH(Border value) {
        tblBorders.setInsideH(value.asDocx4JObject());
    }

    public void setInsideV(Border value) {
        tblBorders.setInsideV(value.asDocx4JObject());
    }

    public static TableBordersBuilder builder() {
        return new TableBordersBuilder();
    }

    public static class TableBordersBuilder {
        private Integer topSize;
        private Integer bottomSize;
        private Integer leftSize;
        private Integer rightSize;
        private Integer insideHSize;
        private Integer insideVSize;

        public TableBordersBuilder withTop(Integer size) {
            this.topSize = size;
            return this;
        }

        public TableBordersBuilder withBottom(Integer size) {
            this.bottomSize = size;
            return this;
        }

        public TableBordersBuilder withLeft(Integer size) {
            this.leftSize = size;
            return this;
        }

        public TableBordersBuilder withRight(Integer size) {
            this.rightSize = size;
            return this;
        }

        public TableBordersBuilder withInsideH(Integer size) {
            this.insideHSize = size;
            return this;
        }

        public TableBordersBuilder withInsideV(Integer size) {
            this.insideVSize = size;
            return this;
        }

        public TableBorders build() {
            TableBorders tableBorders = new TableBorders();
            if (topSize != null) {
                tableBorders.setTop(new Border(topSize));
            }
            if (bottomSize != null) {
                tableBorders.setBottom(new Border(bottomSize));
            }
            if (leftSize != null) {
                tableBorders.setLeft(new Border(leftSize));
            }
            if (rightSize != null) {
                tableBorders.setRight(new Border(rightSize));
            }
            if (insideHSize != null) {
                tableBorders.setInsideH(new Border(insideHSize));
            }
            if (insideVSize != null) {
                tableBorders.setInsideV(new Border(insideVSize));
            }
            return tableBorders;
        }
    }

}
