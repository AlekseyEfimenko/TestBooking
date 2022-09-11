package com.utils;

import aquality.selenium.core.logging.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Properties;

public class Config {
    private static final Logger LOGGER = Logger.getInstance();
    private static Config instance;
    private final Properties properties = new Properties();

    private Config() {}

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    /**
     * Get value from .properties file
     * @param key The key to get value
     * @return The value of .properties file in String format
     */
    public String getProperties(String key) {
        loadFile("config.properties");
        return properties.getProperty(key);
    }

    /**
     * Load properties from custom file to Properties object
     * @param src File, contains properties to load
     */
    private void loadFile(String src) {
        try (FileInputStream fileInputStream = new FileInputStream(getFile(src))) {
            properties.load(fileInputStream);
        } catch (IOException ex) {
            LOGGER.error(String.format("File %s is not found%n%s", src, ex.getMessage()));
        }
    }

    /**
     * Get file, from resources folder
     * @param src path to file
     * @return File from resources folder
     */
    private File getFile(String src) {
        File file = null;
        try {
            file = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource(src)).toURI());
        } catch (URISyntaxException ex) {
            LOGGER.error(ex.getMessage());
        }
        return file;
    }
}
