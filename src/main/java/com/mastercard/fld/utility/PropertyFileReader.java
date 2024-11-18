package com.mastercard.fld.utility;

import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {

    private static Properties prop = null;

    private static String propertyFile = "application.properties";

    public static void loadProperties() {

        if (prop == null || prop.isEmpty()) {
            try {
                InputStream input = PropertyFileReader.class.getClassLoader().getResourceAsStream(propertyFile);
                prop = new Properties();
                if (input == null) {
                    return;
                }
                prop.load(input);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    public static String getProperty(String key) {

        loadProperties();
        return prop.getProperty(key);
    }
}
