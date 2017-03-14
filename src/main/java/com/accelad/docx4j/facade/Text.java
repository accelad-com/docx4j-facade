package com.accelad.docx4j.facade;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;

public class Text implements WordItem {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    private final org.docx4j.wml.Text txt;
    private final String value;

    public Text(String value) {
        this.value = value;
        txt = factory.createText();
        txt.setValue(value);
    }

    public Text(org.docx4j.wml.Text text) {
        this.txt = text;
        this.value = text.getValue();
    }

    public String asString() {
        return value;
    }

    @Override
    public Object asDocx4JObject() {
        return txt;
    }
}
