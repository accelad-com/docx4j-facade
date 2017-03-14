package com.accelad.docx4j.tags;

public class ReportImage {

    private final byte[] imageData;

    public ReportImage(byte[] imageData) {
        this.imageData = imageData;
    }

    public byte[] asBytes() {
        return imageData;
    }

}
