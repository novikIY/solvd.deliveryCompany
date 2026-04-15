package com.solvd.deliverycompany.SAX;

import org.xml.sax.helpers.DefaultHandler;

public abstract class BaseHandler<T> extends DefaultHandler {

    protected StringBuilder content = new StringBuilder();

    protected T result;

    @Override
    public void characters(char[] ch, int start, int length) {
        content.append(ch, start, length);
    }

    protected String getValue() {
        return content.toString().trim();
    }

    protected Long parseLongSafe(String value) {
        if (value == null || value.isBlank()) return null;
        return Long.parseLong(value.trim());
    }

    protected Integer parseIntSafe(String value) {
        if (value == null || value.isBlank()) return null;
        return Integer.parseInt(value.trim());
    }

    protected Double parseDoubleSafe(String value) {
        if (value == null || value.isBlank()) return null;
        return Double.parseDouble(value.trim());
    }

    @Override
    public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) {
        content.setLength(0);
    }

    public T getResult() {
        return result;
    }
}