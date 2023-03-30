package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    
    private Properties properties;

    public PropertiesReader(String propertiesPath) throws IOException {
        this.properties = new Properties();

        InputStream stream = App.class.getResourceAsStream(propertiesPath);
        try {
            properties.load(stream);
        } catch (NullPointerException e) {
            throw new IOException();
        }
    }

    public String getUser() {
        if (this.properties != null) {
            return properties.getProperty("user");
        } else {
            return null;
        }
    }

    public String getPassword() {
        if (this.properties != null) {
            return properties.getProperty("password");
        } else {
            return null;
        }
    }

    public String getUri() {
        if (this.properties != null) {
            return properties.getProperty("url");
        } else {
            return null;
        }
    }
}
