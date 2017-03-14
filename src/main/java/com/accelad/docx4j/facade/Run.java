package com.accelad.docx4j.facade;

import java.util.Objects;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.R;

public class Run implements WordItem {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();
    private final R r;

    public Run() {
        this.r = factory.createR();
    }

    public Run(R r) {
        this.r = r;
    }

    public String asString() {
        return ToStringConverter.toString(this);
    }

    @Override
    public R asDocx4JObject() {
        return r;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Run)) {
            return false;
        }
        Run castOther = (Run) other;
        return Objects.equals(r, castOther.r);
    }

    @Override
    public int hashCode() {
        return Objects.hash(r);
    }

    public void addText(Text text) {
        r.getContent().add(text.asDocx4JObject());
    }

}
