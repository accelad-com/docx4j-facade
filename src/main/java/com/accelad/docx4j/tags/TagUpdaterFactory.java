package com.accelad.docx4j.tags;

import com.accelad.docx4j.facade.WordProcessor;

public class TagUpdaterFactory {
    private final WordProcessor processor;
    private final TagUpdaterFactoryByVisiting visitor = new TagUpdaterFactoryByVisiting();

    public TagUpdaterFactory(WordProcessor processor) {
        this.processor = processor;
    }

    TagValueUpdater getUpdater(TagValue value) {
        value.accept(visitor);
        return visitor.getUpdater();
    }

    private class TagUpdaterFactoryByVisiting implements TagValueVisitor {
        private TagValueUpdater updater;

        public TagValueUpdater getUpdater() {
            return updater;
        }

        @Override
        public void visit(TagValueString tagValueString) {
            updater = new TagUpdaterString(tagValueString, processor);
        }

        @Override
        public void visit(TagValueParagraphs tagValueParagraph) {
            updater = new TagUpdaterParagraphs(tagValueParagraph, processor);
        }

        @Override
        public void visit(TagValueBlock tagValueBlock) {
            updater = new TagUpdaterBlock(tagValueBlock, processor);
        }

        @Override
        public void visit(TagValueTable tagValueTable) {
            updater = new TagUpdaterTable(tagValueTable);
        }

        @Override
        public void visit(TagValueImage tagValueImage) {
            updater = new TagUpdaterImage(tagValueImage, processor);
        }
    }
}
