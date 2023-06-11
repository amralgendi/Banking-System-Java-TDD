//
//  DatabaseAccess.java
//  Studyico
//
//  Created by Mohamed Salah on 4/22/2022.
//

package com.egybank.helpers.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseAccess {
    private static final String location = DatabaseAccess.class.getResource("/db/main.db").toExternalForm();

    public static void initSQLite() {
        try {
            Class.forName("org.sqlite.JDBC"); //force Java ClassLoader to load class
            DriverManager.registerDriver(new org.sqlite.JDBC()); //register class with DriverManager
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection connect() {
        String dbPrefix = "jdbc:sqlite:";
        Connection connection;
        try {
            connection = DriverManager.getConnection(dbPrefix + DatabaseAccess.location);
        } catch (SQLException exception) {
            Logger.getAnonymousLogger().log(Level.SEVERE,
                    LocalDateTime.now() + ": Could not connect to SQLite DB at " +
                            DatabaseAccess.location);
            return null;
        }
        return connection;
    }

}
