package com.accelad.docx4j.facade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.List;

import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.FooterPart;
import org.docx4j.openpackaging.parts.WordprocessingML.HeaderPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.Ftr;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.R;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.accelad.docx4j.facade.Paragraph;
import com.accelad.docx4j.facade.Paragraphs;
import com.accelad.docx4j.facade.ProcessorHelper;
import com.accelad.docx4j.facade.Run;
import com.accelad.docx4j.facade.WordProcessorDocx4J;

public class WordProcessorDocx4JTest {

    @Mock WordprocessingMLPackage mlPackage;
    @Mock MainDocumentPart documentPart;
    WordProcessorDocx4J processor;
    @Mock ProcessorHelper processorHelper;

    @Before
    public void initBeforeTest() throws Exception {
        initMocks(this);

        when(mlPackage.getMainDocumentPart()).thenReturn(documentPart);

        processor = new WordProcessorDocx4J(mlPackage, processorHelper);
    }

    @Test
    public void should_return_only_paragraph_when_WordProcessingMLPgckage_contains_more_than_paragraphs_in_main_document()
            throws Exception {
        List<Object> objects = new ArrayList<>();

        Paragraph paragraph = new Paragraph();
        Run run = new Run();
        objects.add(paragraph.asDocx4JObject());
        objects.add(run.asDocx4JObject());
        when(documentPart.getContent()).thenReturn(objects);

        Paragraphs paragraphs = processor.getParagraphs();

        assertThat(paragraphs, containsInAnyOrder(paragraph));
        assertThat(paragraphs, not(containsInAnyOrder(run)));
    }

    @Test
    public void should_return_only_paragraph_when_WordProcessingMLPgckage_contains_more_than_paragraphs_in_footer()
            throws Exception {
        List<FooterPart> parts = new ArrayList<>();
        when(processorHelper.getFooterParts(mlPackage)).thenReturn(parts);

        Paragraph paragraph = new Paragraph();
        Run run = new Run();
        FooterPart part = new FooterPart();
        Ftr ftr = new Ftr();
        ftr.getContent().add(paragraph.asDocx4JObject());
        ftr.getContent().add(run.asDocx4JObject());
        part.setContents(ftr);
        parts.add(part);

        Paragraphs paragraphs = processor.getFooterParagraphs();

        assertThat(paragraphs, containsInAnyOrder(paragraph));
        assertThat(paragraphs, not(containsInAnyOrder(run)));
    }

    @Test
    public void should_return_only_paragraph_when_WordProcessingMLPgckage_contains_more_than_paragraphs_in_header()
            throws Exception {
        List<HeaderPart> parts = new ArrayList<>();
        when(processorHelper.getHeaderParts(mlPackage)).thenReturn(parts);

        Paragraph paragraph = new Paragraph();
        Run run = new Run();
        HeaderPart part = new HeaderPart();
        Hdr hdr = new Hdr();
        hdr.getContent().add(paragraph.asDocx4JObject());
        hdr.getContent().add(run.asDocx4JObject());
        part.setContents(hdr);
        parts.add(part);

        Paragraphs paragraphs = processor.getHeaderParagraphs();

        assertThat(paragraphs, containsInAnyOrder(paragraph));
        assertThat(paragraphs, not(containsInAnyOrder(run)));
    }

    @Test
    public void should_return_a_new_run_containg_a_text_when_we_create_a_new_text()
            throws Exception {

        Run run = processor.newText("The text");

        R r = run.asDocx4JObject();
        List<Object> content = r.getContent();
        org.docx4j.wml.Text actual = (org.docx4j.wml.Text) content.get(0);
        assertThat(actual.getValue(), is("The text"));
    }

    @Test
    public void should_return_a_new_run_containg_a_drawing_when_we_create_a_new_image()
            throws Exception {
        byte[] bytes = new byte[] {};
        BinaryPartAbstractImage value = mock(BinaryPartAbstractImage.class);
        Inline inline = mock(Inline.class);
        when(value.createImageInline(null, null, 0,
                1, 6000, false)).thenReturn(inline);

        when(processorHelper.createImagePart(mlPackage, bytes)).thenReturn(value);


        Run run = processor.newImage(bytes);

        R r = run.asDocx4JObject();
        List<Object> content = r.getContent();
        Drawing actual = (Drawing) content.get(0);
        assertThat(actual.getAnchorOrInline(), containsInAnyOrder(inline));
    }

}
