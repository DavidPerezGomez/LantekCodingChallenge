package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

class PropertiesReaderTest {

    @Test
    void testReadProperties() throws IOException {
        PropertiesReader propertiesReader = new PropertiesReader("/application.properties");

        assertEquals(propertiesReader.getUser(), "testUser");
        assertEquals(propertiesReader.getPassword(), "testPassword");
        assertEquals(propertiesReader.getUri(), "testURL");
    }

    @Test
    void testReadEmptyProperties() throws IOException {
        PropertiesReader propertiesReader = new PropertiesReader("/application.empty.properties");

        assertEquals(propertiesReader.getUser(), "");
        assertEquals(propertiesReader.getPassword(), "");
        assertEquals(propertiesReader.getUri(), null);
    }

    @Test
    void testReadMissingFile() {
        assertThrows(IOException.class, new Executable() {
            @Override
            public void execute() throws IOException {
                new PropertiesReader("unknown.file");
            }
        });
    }
}
