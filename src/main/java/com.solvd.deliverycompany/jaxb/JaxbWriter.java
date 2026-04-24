package com.solvd.deliverycompany.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import java.io.File;

public class JaxbWriter {

    public static <T> void marshal(T object, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(object, new File(filePath));

        } catch (Exception e) {
            throw new RuntimeException("Error during marshalling", e);
        }
    }
}