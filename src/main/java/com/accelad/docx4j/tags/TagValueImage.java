package com.accelad.docx4j.tags;

public class TagValueImage implements TagValue {

    private final ReportImage image;

    public TagValueImage(ReportImage image) {
        this.image = image;
    }

    public ReportImage getImage() {
        return image;
    }

    @Override
    public void accept(TagValueVisitor visitor) {
        visitor.visit(this);
    }

}
