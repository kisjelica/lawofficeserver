/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laptop-02
 */
public class DbConnectionFactory {

    private Connection connection;
    private static DbConnectionFactory instance;
    private ConnectionPool connectionPool;

    private DbConnectionFactory()  {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("config/config.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connectionPool = BasicConnectionPool.create(url, username, password);
        } catch (Exception ex) {
            Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DbConnectionFactory getInstance()  {
        if (instance == null) {
            instance = new DbConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws Exception {
        connection = connectionPool.getConnection();
        connection.setAutoCommit(false);
       return connection;
    }
}
