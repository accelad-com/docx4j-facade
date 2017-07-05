package com.accelad.docx4j.tags;

import java.util.Arrays;

public class ReportImage {

    private final byte[] imageData;

    public ReportImage(byte[] imageData) {
        this.imageData = imageData;
    }

    public byte[] asBytes() {
        return imageData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ReportImage that = (ReportImage) o;

        return Arrays.equals(imageData, that.imageData);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(imageData);
    }
}
