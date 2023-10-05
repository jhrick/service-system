package org.example.databaseconfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static final String CONFIG_FILE = "config.properties";

    public static String getDatabaseFile() {
        Properties properties = new Properties();

        try (InputStream inputStream = DatabaseConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE))
        {
            if (inputStream != null) {
                properties.load(inputStream);
                return properties.getProperty("db.path");
            } else {
                throw new IOException("Config file not found: " + CONFIG_FILE);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
