package com.epam.cdp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private String path;

    public ConfigReader(String path) {
        this.path = path;
    }

    public Properties getProperties() {
        InputStream fileInput = ConfigReader.class.getResourceAsStream(path);
        Properties properties = new Properties();
        try {
            properties.load(fileInput);
            fileInput.close();
            return properties;
        } catch (IOException e) {
            return null;
        }
    }

}
