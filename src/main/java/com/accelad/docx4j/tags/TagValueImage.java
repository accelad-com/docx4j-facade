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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        TagValueImage that = (TagValueImage) o;

        return image != null ? image.equals(that.image) : that.image == null;
    }

    @Override
    public int hashCode() {
        return image != null ? image.hashCode() : 0;
    }
}
