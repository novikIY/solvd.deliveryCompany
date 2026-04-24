package com.solvd.deliverycompany.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class JaxbParser {

    public static <T> T unmarshal(String filePath, Class<T> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            return (T) unmarshaller.unmarshal(new File(filePath));

        } catch (Exception e) {
            throw new RuntimeException("Error during unmarshalling", e);
        }
    }
}
