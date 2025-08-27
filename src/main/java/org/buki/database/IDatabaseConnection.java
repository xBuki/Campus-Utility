package org.buki.database;

import java.sql.Connection;

public interface IDatabaseConnection {
    Connection connect();
}
