package com.accelad.docx4j.tags;

public interface TagValueVisitor {

    void visit(TagValueString tagValueString);

    void visit(TagValueParagraphs tagValueParagraph);

    void visit(TagValueBlock tagValueBlock);

    void visit(TagValueTable tagValueTable);

    void visit(TagValueImage tagValueImage);

}
