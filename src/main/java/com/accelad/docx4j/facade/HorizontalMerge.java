package com.accelad.docx4j.facade;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.TcPrInner.HMerge;

public class HorizontalMerge implements WordItem {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    public static final HorizontalMerge RESTART = new HorizontalMerge("restart");
    public static final HorizontalMerge CONTINUE = new HorizontalMerge("continue");

    private final HMerge hMerge;

    private HorizontalMerge(String value) {
        hMerge = factory.createTcPrInnerHMerge();
        hMerge.setVal(value);
    }

    HorizontalMerge(HMerge hMerge) {
        this.hMerge = hMerge;
    }

    @Override
    public HMerge asDocx4JObject() {
        return hMerge;
    }
}
