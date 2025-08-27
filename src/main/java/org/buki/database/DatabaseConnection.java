package org.buki.database;

import org.buki.database.properties.IDatabaseProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection implements IDatabaseConnection {
    private final IDatabaseProperties databaseProperties;

    public DatabaseConnection(IDatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }


    @Override
    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(databaseProperties.getConnectionString(), databaseProperties.getUsername(), databaseProperties.getPassword());
        } catch (SQLException e) {
            System.out.println(e.toString());
            System.out.println(e.getClass().getName() + ": Error trying to connect with the database.");
        }
        return connection;
    }
}
