package com.accelad.docx4j.facade;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.PPr;

public class ParagraphProperties implements WordItem {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    private final PPr pPr;

    public ParagraphProperties() {
        pPr = factory.createPPr();
    }

    public ParagraphProperties(PPr pPr) {
        this.pPr = pPr;
    }

    @Override
    public PPr asDocx4JObject() {
        return pPr;
    }

    public void setAlignment(Alignment alignment) {
        pPr.setJc(alignment.asDocx4JObject());
    }

}
