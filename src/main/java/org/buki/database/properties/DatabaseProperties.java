package org.buki.database.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import static java.lang.System.setProperties;

public class DatabaseProperties extends Properties implements IDatabaseProperties {
    private static final long serialVersionUID = -149535523358222510L;
    private final Logger logger = Logger.getLogger(getClass().getName());
    private static final String DATABASE_PROPERTIES = "App.config";

    private String username;
    private String password;
    private String connectionString;
    private String driver;
    private final String propertyFile;

    public DatabaseProperties() {
        this(DATABASE_PROPERTIES);
    }

    DatabaseProperties(String fileLocation) {
        this.propertyFile = fileLocation;
        loadProperties();
        try {
           Class.forName(this.getDriver());
        } catch (ClassNotFoundException e) {
            logger.severe("Database Driver not found: " + e.getMessage());
        }
    }

    protected void loadProperties() {
        try (InputStream stream = getClass().getResourceAsStream(propertyFile)) {
            if (stream == null) {
                logger.severe(": Could not read database properties file");
                return;
            }
            load(stream);
            setProperties();
        } catch (IOException e) {
            logger.severe(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    private void setProperties() {
        this.password = getProperty("PASSWORD");
        this.username = getProperty("USERNAME");
        this.driver = getProperty("DRIVER");
        this.connectionString = getProperty("CONNECTION_STRING").replace("{{HOST}}", getProperty("HOST"))
                .replace("{{PORT}}", getProperty("PORT"));
    }

    @Override
    public String getConnectionString() {
        return "";
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public String getDriver() {
        return "";
    }
}
