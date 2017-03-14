package com.accelad.docx4j.facade;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.ProofErr;

public class ProofError implements WordItem {
    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    private final ProofErr err;

    public ProofError() {
        this.err = factory.createProofErr();
    }

    public ProofError(ProofErr err) {
        this.err = err;
    }

    @Override
    public ProofErr asDocx4JObject() {
        return err;
    }


}
