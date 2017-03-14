package com.accelad.docx4j.tags;

import org.apache.commons.lang3.StringUtils;

import com.accelad.docx4j.facade.Alignment;
import com.accelad.docx4j.facade.HorizontalMerge;
import com.accelad.docx4j.facade.Paragraph;
import com.accelad.docx4j.facade.Table;
import com.accelad.docx4j.facade.TableBorders;
import com.accelad.docx4j.facade.TableCell;
import com.accelad.docx4j.facade.TableCellWidth;
import com.accelad.docx4j.facade.TableGrid;
import com.accelad.docx4j.facade.TableGridColumn;
import com.accelad.docx4j.facade.TableProperties;
import com.accelad.docx4j.facade.TableRow;

public class TagUpdaterTable implements TagValueUpdater {
    private final TagValueTable valueTable;

    public TagUpdaterTable(TagValueTable valueTable) {
        this.valueTable = valueTable;
    }


    @Override
    public boolean update(Tag tag) {
        Paragraph container = tag.getParagraph();

        String[][] value = valueTable.getValue();

        int columnCount = valueTable.getColumnCount();

        Table table = createTable();
        table.addRow(createTitle(columnCount));
        if (valueTable.getHeaderGroups() != null) {
            table.addRow(createTitleGroup());
        }
        table.addRow(createHeaders(valueTable.getHeaders()));

        int rowCount = valueTable.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            TableRow row = new TableRow();
            String[] rowValues = value[i];

            for (String cellValue : rowValues) {
                row.addCell(TableCell.builder()
                        .withText(cellValue)
                        .withAlignment(Alignment.CENTER)
                        .withWidth(800)
                        .build());
            }

            table.addRow(row);
        }

        container.removeAll(tag.getItems());
        container.addItemInTheParentContainer(table);

        return SUCCESS;
    }


    private TableRow createHeaders(String[] headers) { // NOSONAR
        TableRow headerRow = new TableRow();

        for (String header : headers) {
            headerRow.addCell(TableCell.builder()
                    .withText(header)
                    .withAlignment(Alignment.CENTER)
                    .build());
        }
        return headerRow;
    }

    private TableRow createTitle(int columnCount) {
        TableRow titleRow = new TableRow();

        titleRow.addCell(TableCell.builder()
                .withText(valueTable.getTitle())
                .withAlignment(Alignment.CENTER)
                .withMerge(HorizontalMerge.RESTART)
                .build());
        for (int i = 1; i < columnCount; i++) {
            titleRow.addCell(TableCell.builder()
                    .withMerge(HorizontalMerge.CONTINUE)
                    .build());
        }
        return titleRow;
    }

    private TableRow createTitleGroup() {
        TableRow titleRow = new TableRow();

        TableHeaderGroup[] groups = valueTable.getHeaderGroups();

        for (int g=0; g < groups.length; g++) {
            TableHeaderGroup group = groups[g];

            TableCell.TableCellBuilder builder = TableCell.builder();
            if (!StringUtils.isEmpty(group.getTitle())) {
                    builder.withText(group.getTitle()).withAlignment(Alignment.CENTER);
            }
            titleRow.addCell(builder.withMerge(HorizontalMerge.RESTART).build());

            for (int i = 1; i < group.getRowspan(); i++) {
                titleRow.addCell(builder
                        .withMerge(HorizontalMerge.CONTINUE)
                        .build());
            }
        }
        return titleRow;
    }

    private Table createTable() {
        Table table = new Table();
        TableProperties tableProperties = new TableProperties();
        tableProperties.setWidth(new TableCellWidth(5000, "pct"));

        tableProperties.setBorders(TableBorders.builder()
                .withTop(4)
                .withBottom(4)
                .withLeft(4)
                .withRight(4)
                .withInsideH(4)
                .withInsideV(4)
                .build());

        table.setProperties(tableProperties);

        TableGrid grid = new TableGrid();
        grid.addGridColumn(new TableGridColumn(10296));
        table.setGrid(grid);
        return table;
    }
}
