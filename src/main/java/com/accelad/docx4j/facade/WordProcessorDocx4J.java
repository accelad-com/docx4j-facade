package com.accelad.docx4j.facade;

import java.util.List;
import java.util.stream.Collectors;

import org.docx4j.TraversalUtil;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.finders.ClassFinder;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.FooterPart;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.Text;

public class WordProcessorDocx4J implements WordProcessor {

    private static final ObjectFactory factory = Context.getWmlObjectFactory();

    private final WordprocessingMLPackage wordprocessingMLPackage;
    private final ProcessorHelper helper;

    public WordProcessorDocx4J(WordprocessingMLPackage wordprocessingMLPackage) {
        this(wordprocessingMLPackage, new ProcessorHelper());
    }

    WordProcessorDocx4J(WordprocessingMLPackage wordprocessingMLPackage, ProcessorHelper helper) {
        this.wordprocessingMLPackage = wordprocessingMLPackage;
        this.helper = helper;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> findElementsByType(Class<T> clazz, Object content) {
        ClassFinder finder = new ClassFinder(clazz);
        new TraversalUtil(content, finder); // NOSONAR
        return (List<T>) finder.results;
    }

    @Override
    public Paragraphs getParagraphs() {
        List<Object> content = getContent();
        return extractParagraphs(content);
    }

    private Paragraphs extractParagraphs(Object content) {
        List<P> list = findElementsByType(P.class, content);
        List<Paragraph> paragraphs = list.stream().map(Paragraph::new).collect(Collectors.toList());
        return new Paragraphs(paragraphs);
    }

    @Override
    public Paragraphs getHeaderParagraphs() {
        List<HeaderPart> headerParts = helper.getHeaderParts(wordprocessingMLPackage);
        Paragraphs paragraphs = new Paragraphs();

        for (HeaderPart headerPart : headerParts) {
            paragraphs.addAll(extractParagraphs(headerPart));
        }

        return paragraphs;
    }


    @Override
    public Paragraphs getFooterParagraphs() {
        List<FooterPart> parts = helper.getFooterParts(wordprocessingMLPackage);
        Paragraphs paragraphs = new Paragraphs();

        for (FooterPart headerPart : parts) {
            paragraphs.addAll(extractParagraphs(headerPart));
        }

        return paragraphs;
    }

    @Override
    public Paragraph newStyledParagraphOfText(String style, String title) {
        MainDocumentPart mainDocumentPart = wordprocessingMLPackage.getMainDocumentPart();
        P pTitle = mainDocumentPart.createStyledParagraphOfText(style, title);

        return new Paragraph(pTitle);
    }

    @Override
    public int getParagraphPosition(Paragraph paragraph) {
        return getContent().indexOf(paragraph.asDocx4JObject());
    }

    @Override
    public Run newText(String value) {
        R run = factory.createR();
        Text t = factory.createText();
        t.setValue(value);
        run.getContent().add(t);
        return new Run(run);
    }

    @Override
    public Run newImage(byte[] bytes) throws WordProcessorException {
        return newImage(bytes, null, null, 0, 1);
    }

    @Override
    public Run newImage(byte[] bytes, String filenameHint, String altText,
            int id1, int id2) throws WordProcessorException {
        try {
            BinaryPartAbstractImage imagePart = helper.createImagePart(wordprocessingMLPackage,
                    bytes);
            Inline inline = imagePart.createImageInline(filenameHint, altText, id1, id2, 6000,
                    false);
            Drawing drawing = factory.createDrawing();
            drawing.getAnchorOrInline().add(inline);

            R run = factory.createR();
            run.getContent().add(drawing);
            return new Run(run);
        } catch (Exception e) {
            throw new WordProcessorException("Impossible to create new image", e);
        }
    }

    @Override
    public void add(int position, Paragraph item, Paragraph parent) {
        getContent().add(position, item.asDocx4JObject());
        if (parent != null && parent.asDocx4JObject() != null) {
            item.asDocx4JObject().setParent(parent.asDocx4JObject().getParent());
        }
    }

    @Override
    public void replaceBy(WordItemContainer container, WordItems items, WordItem item) {
        int position = container.getFirstPosition(items);

        container.removeAll(items);
        container.addItem(item, position);
    }

    private List<Object> getContent() {
        return wordprocessingMLPackage.getMainDocumentPart().getContent();
    }
}
