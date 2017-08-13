package com.accelad.docx4j.facade;

import java.util.List;

public interface WordProcessor {

    Paragraphs getParagraphs();

    Paragraphs getHeaderParagraphs();

    Paragraphs getFooterParagraphs();

    Paragraph newStyledParagraphOfText(String style, String text);

    int getParagraphPosition(Paragraph paragraph);

    Run newText(String value);

    Run newImage(byte[] bytes) throws WordProcessorException;

    Run newImage(byte[] bytes, String filenameHint, String altText,
            int id1, int id2) throws WordProcessorException;

    void add(int position, Paragraph item, Paragraph parent);

    void replaceBy(WordItemContainer container, WordItems items, WordItem item);

    void clearBodyContent();

    void mergeContentWith(WordProcessor processor);

    List<Object> getContent();

}