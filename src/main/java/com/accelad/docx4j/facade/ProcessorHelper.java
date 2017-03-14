package com.accelad.docx4j.facade;

import java.util.ArrayList;
import java.util.List;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.FooterPart;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.openpackaging.parts.relationships.Namespaces;
import org.docx4j.openpackaging.parts.relationships.RelationshipsPart;
import org.docx4j.relationships.Relationship;

class ProcessorHelper {

    List<HeaderPart> getHeaderParts(WordprocessingMLPackage wordprocessingMLPackage){
        List<HeaderPart> parts = new ArrayList<>();
        MainDocumentPart mainDocumentPart = wordprocessingMLPackage.getMainDocumentPart();
        RelationshipsPart rp = mainDocumentPart.getRelationshipsPart();
        for (Relationship r : rp.getRelationships().getRelationship()) {
            if (r.getType().equals(Namespaces.HEADER)) {
                HeaderPart part = (HeaderPart) rp.getPart(r);
                parts.add(part);
            }
        }

        return parts;

    }

    List<FooterPart> getFooterParts(WordprocessingMLPackage wordprocessingMLPackage) {
        List<FooterPart> parts = new ArrayList<>();
        MainDocumentPart mainDocumentPart = wordprocessingMLPackage.getMainDocumentPart();
        RelationshipsPart rp = mainDocumentPart.getRelationshipsPart();
        for (Relationship r : rp.getRelationships().getRelationship()) {
            if (r.getType().equals(Namespaces.FOOTER)) {
                FooterPart part = (FooterPart) rp.getPart(r);
                parts.add(part);
            }
        }
        
        return parts;

    }

    BinaryPartAbstractImage createImagePart(WordprocessingMLPackage wordprocessingMLPackage,
            byte[] bytes) throws Exception {
        return BinaryPartAbstractImage.createImagePart(wordprocessingMLPackage, bytes);
    }

}
