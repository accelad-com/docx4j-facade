package com.accelad.docx4j.facade;

import java.util.List;
import java.util.Objects;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.ProofErr;
import org.docx4j.wml.R;

public class Paragraph implements WordItem, WordItemContainer {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();
    private final P p;

    public Paragraph() {
        this.p = factory.createP();
    }

    public Paragraph(P p) {
        this.p = p;
    }

    public P getP() {
        return p;
    }

    public String asString() {
        return ToStringConverter.toString(this);
    }

    @Override
    public P asDocx4JObject() {
        return p;
    }

    @Override
    public int getPosition(WordItem item) {
        return p.getContent().indexOf(item.asDocx4JObject());
    }

    @Override
    public int getFirstPosition(WordItems items) {
        int position = Integer.MAX_VALUE;
        for (WordItem item : items) {
            position = Math.min(position, getPosition(item));
        }
        return position;
    }

    @Override
    public void addItem(WordItem item) {
        p.getContent().add(item.asDocx4JObject());
    }

    @Override
    public void addItem(WordItem item, int position) {
        p.getContent().add(position, item.asDocx4JObject());
    }

    @Override
    public WordItems getContent() {
        ListWordItems items = new ListWordItems();
        for (Object obj : p.getContent()) {
            if (obj instanceof R) {
                R run = (R) obj;
                items.add(new Run(run));
            } else if (obj instanceof ProofErr) {
                ProofErr err = (ProofErr) obj;
                items.add(new ProofError(err));
            }

            else {
                throw new IllegalArgumentException(
                        "Not supported yet :" + obj.getClass().getName());
            }
        }
        return items;
    }

    @Override
    public IndexedWordItems getIndexedContent() {
        IndexedWordItems items = new IndexedWordItems();
        for (Object obj : p.getContent()) {
            int position = p.getContent().indexOf(obj);
            if (obj instanceof R) {
                R run = (R) obj;

                items.put(new Run(run), position);
            } else if (obj instanceof ProofErr) {
                ProofErr err = (ProofErr) obj;
                items.put(new ProofError(err), position);
            }
        }
        return items;
    }

    @Override
    public void removeAll(WordItems items) {
        for (WordItem item : items) {
            Object docx4JObject = item.asDocx4JObject();
            p.getContent().remove(docx4JObject);
        }
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Paragraph)) {
            return false;
        }
        Paragraph castOther = (Paragraph) other;
        return Objects.equals(p, castOther.p);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p);
    }

    public void addRun(Run run) {
        p.getContent().add(run.asDocx4JObject());
    }

    public void setProperties(ParagraphProperties properties) {
        p.setPPr(properties.asDocx4JObject());
    }

    @SuppressWarnings({ "unchecked" })
    public void addItemInTheParentContainer(WordItem item) {
        List<Object> list = (List<Object>) p.getParent();
        int pos = list.indexOf(p);
        Object itemAsDocx4JObject = item.asDocx4JObject();
        list.add(pos, itemAsDocx4JObject);
    }

}
