package com.solvd.deliverycompany.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.InputStream;

public class JacksonParser {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
    }

    public static <T> T read(String fileName, Class<T> type) {
        try {
            InputStream is = JacksonParser.class
                    .getClassLoader()
                    .getResourceAsStream(fileName);

            if (is == null) {
                throw new RuntimeException("File not found in classpath: " + fileName);
            }

            return mapper.readValue(is, type);

        } catch (Exception e) {
            throw new RuntimeException("Error reading JSON", e);
        }
    }

    public static void write(Object obj, String filePath) {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(filePath), obj);
        } catch (Exception e) {
            throw new RuntimeException("Error writing JSON", e);
        }
    }
}