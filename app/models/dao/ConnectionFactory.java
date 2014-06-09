package models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Chrysa Papadaki - papadaki.chr@gmail.com
 *
 */
public class ConnectionFactory {
    String driverClassName = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:8082/playdb_chrysa";
    String dbUser = "chrpapa";
    String dbPwd = "chrpapa";

    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory() {
            try {
                    Class.forName(driverClassName);
            } catch (ClassNotFoundException e) {
                    e.printStackTrace();
            }
    }

    public Connection getConnection() throws SQLException {
            Connection conn = null;
            conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
            return conn;
    }

    public static ConnectionFactory getInstance() {
            if (connectionFactory == null) {
                    connectionFactory = new ConnectionFactory();
            }
            return connectionFactory;
    }
}
