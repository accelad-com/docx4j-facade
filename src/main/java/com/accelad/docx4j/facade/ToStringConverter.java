package com.accelad.docx4j.facade;

import java.io.StringWriter;

import org.docx4j.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToStringConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToStringConverter.class);

    private ToStringConverter() {
    }

    public static String toString(WordItem item) {
        try {
            StringWriter w = new StringWriter();
            TextUtils.extractText(item.asDocx4JObject(), w);
            return w.toString();
        } catch (Exception e) { // NOSONAR
            LOGGER.warn("impossible to extract text in word item", e);
            return "";
        }
    }
}
