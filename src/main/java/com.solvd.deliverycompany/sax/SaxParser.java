package com.solvd.deliverycompany.sax;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.InputStream;

public class SaxParser {

    public static <T> T parse(String fileName, BaseHandler<T> handler) {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);

            InputStream is = SaxParser.class
                    .getClassLoader()
                    .getResourceAsStream(fileName);

            if (is == null) {
                throw new RuntimeException("File not found in resources: " + fileName);
            }

            reader.parse(new InputSource(is));

            return handler.getResult();

        } catch (Exception e) {
            throw new RuntimeException("Error parsing XML: " + fileName, e);
        }
    }
}