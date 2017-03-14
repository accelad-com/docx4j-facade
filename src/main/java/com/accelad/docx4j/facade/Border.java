package com.accelad.docx4j.facade;

import java.math.BigInteger;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.CTBorder;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.STBorder;

public class Border implements WordItem {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    private final CTBorder ctBorder;

    public Border() {
        ctBorder = factory.createCTBorder();
    }

    public Border(CTBorder ctBorder) {
        this.ctBorder = ctBorder;
    }

    public Border(int size) {
        ctBorder = factory.createCTBorder();
        ctBorder.setVal(STBorder.SINGLE);
        setSize(size);
    }

    @Override
    public CTBorder asDocx4JObject() {
        return ctBorder;
    }

    public void setColor(String value) {
        ctBorder.setColor(value);
    }

    public void setThemeTint(String value) {
        ctBorder.setThemeTint(value);
    }

    public void setThemeShade(String value) {
        ctBorder.setThemeShade(value);
    }

    public void setSize(int value) {
        ctBorder.setSz(BigInteger.valueOf(value));
    }

    public void setSpace(BigInteger value) {
        ctBorder.setSpace(value);
    }

    public void setShadow(Boolean value) {
        ctBorder.setShadow(value);
    }

    public void setFrame(Boolean value) {
        ctBorder.setFrame(value);
    }


}
