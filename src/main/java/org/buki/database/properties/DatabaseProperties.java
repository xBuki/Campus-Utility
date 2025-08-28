package org.buki.database.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseProperties extends Properties implements IDatabaseProperties {
    private static final long serialVersionUID = -149535523358222510L;
    private static final Logger logger = Logger.getLogger(DatabaseProperties.class.getName());
    private static final String RESOURCE_PATH = "/App.config"; // classpath root

    private String username;
    private String password;
    private String connectionString;
    private String driver;

    public DatabaseProperties() {
        loadProperties();
        try {
            Class.forName(getDriver());
        } catch (ClassNotFoundException e) {
            logger.severe("Database Driver not found: " + e.getMessage());
        }
    }

    protected void loadProperties() {
        try (InputStream stream = getClass().getResourceAsStream(RESOURCE_PATH)) {
            if (stream == null) {
                logger.severe("Could not read database properties file: " + RESOURCE_PATH);
                return;
            }
            load(stream);
            apply();
        } catch (IOException e) {
            logger.severe(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private void apply() {
        this.driver   = getProperty("DRIVER", "com.mysql.cj.jdbc.Driver");
        this.username = getProperty("USERNAME", "root");
        this.password = getProperty("PASSWORD", "");

        // Build connection string from template (supports {{HOST}}, {{PORT}}, {{DB_NAME}})
        String host   = getProperty("HOST", "localhost");
        String port   = getProperty("PORT", "3306");
        String dbName = getProperty("DB_NAME", getProperty("DBNAME", "test")); // support both keys

        String template = getProperty("CONNECTION_STRING");
        if (template == null || template.isEmpty()) {
            template = "jdbc:mysql://{{HOST}}:{{PORT}}/{{DB_NAME}}?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        }
        this.connectionString = template
                .replace("{{HOST}}", host)
                .replace("{{PORT}}", port)
                .replace("{{DB_NAME}}", dbName);
    }

    @Override public String getConnectionString() { return connectionString; }
    @Override public String getPassword()         { return password; }
    @Override public String getUsername()         { return username; }
    @Override public String getDriver()           { return driver; }
}
