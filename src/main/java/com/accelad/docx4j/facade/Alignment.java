package com.accelad.docx4j.facade;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.Jc;
import org.docx4j.wml.JcEnumeration;
import org.docx4j.wml.ObjectFactory;

public class Alignment implements WordItem {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    public static final Alignment CENTER = new Alignment(JcEnumeration.CENTER);
    public static final Alignment LEFT = new Alignment(JcEnumeration.LEFT);
    public static final Alignment RIGHT = new Alignment(JcEnumeration.RIGHT);
    private final Jc jc;

    private Alignment(JcEnumeration enumeration) {
        this.jc = factory.createJc();
        jc.setVal(enumeration);
    }

    Alignment(Jc jc) {
        this.jc = jc;
    }

    @Override
    public Jc asDocx4JObject() {
        return jc;
    }
}
