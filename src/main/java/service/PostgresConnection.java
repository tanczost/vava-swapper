package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {
    private static String url = "localhost";
    private static int port = 5432;
    private static String databaseName = "vava";
    private static String username = "postgres";
    private static String password = "414272";
    public static Connection connection = null;

    public static Connection initializePostgresqlDatabase() {
        if (connection != null){
            return connection;
        }

        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
            System.out.println("DB connected");
        } catch (SQLException | IllegalArgumentException ex) {
            ex.printStackTrace(System.err);
        } finally {
            if (connection == null) {
                System.exit(-1);
            }
        }

        return connection;
    }
}
