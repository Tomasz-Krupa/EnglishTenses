package tenses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBconnector
{
    /**
     * Method setting and getting connection with database
     */
    public static Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/englishwords?currentSchema=public";
        Properties props = new Properties();
        props.setProperty("user", "visitor");
        props.setProperty("password", "visitor");
        Connection connection;

        connection = DriverManager.getConnection(url, props);

        return connection;
    }
}
