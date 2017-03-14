package com.accelad.docx4j.facade;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.Tc;

public class TableCell implements WordItem {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    private final Tc tc;

    public TableCell() {
        tc = factory.createTc();
    }

    public TableCell(Tc tc) {
        this.tc = tc;
    }

    @Override
    public Object asDocx4JObject() {
        return tc;
    }

    public void addParagraph(Paragraph paragraph) {
        tc.getContent().add(paragraph.asDocx4JObject());
    }

    public void setProperties(TableCellProperties properties) {
        tc.setTcPr(properties.asDocx4JObject());
    }

    public static TableCellBuilder builder() {
        return new TableCellBuilder();
    }

    public static class TableCellBuilder {
        private String text = "";
        private Integer width;
        private HorizontalMerge merge;
        private Alignment alignment;

        public TableCellBuilder withText(String text) {
            this.text = text;
            return this;
        }

        public TableCellBuilder withAlignment(Alignment alignment) {
            this.alignment = alignment;
            return this;
        }

        public TableCellBuilder withWidth(Integer width) {
            this.width = width;
            return this;
        }

        public TableCellBuilder withMerge(HorizontalMerge merge) {
            this.merge = merge;
            return this;
        }


        public TableCell build() {
            TableCell cell = new TableCell();
            Paragraph paragraph = new Paragraph();
            Run run = new Run();
            run.addText(new Text(text));
            paragraph.addItem(run);
            if (alignment != null) {
                ParagraphProperties properties = new ParagraphProperties();
                properties.setAlignment(alignment);
                paragraph.setProperties(properties);
            }

            cell.addParagraph(paragraph);

            TableCellProperties properties = new TableCellProperties();
            if (width != null) {
                properties.setWidth(new TableCellWidth(width, "pct"));
            }

            if (merge != null) {
                properties.setHorizontalMerge(merge);
            }

            cell.setProperties(properties);

            return cell;
        }

    }

}
