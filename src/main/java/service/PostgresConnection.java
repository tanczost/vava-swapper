package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public abstract class PostgresConnection {
    private static Dotenv dotenv = Dotenv.configure().ignoreIfMissing().ignoreIfMalformed().load();
    private static String url = "localhost";
    private static int port = 2346;
    private static String databaseName = "postgres";
    private static String username = "postgres";
    private static String password = "vava";
    public static Connection connection = null;

    public static boolean isResultEmpty(ResultSet resultSet) throws SQLException {
        return !resultSet.isBeforeFirst();
    }

    public static Connection initializePostgresqlDatabase() {
        if (connection != null){
            return connection;
        }

        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + databaseName, username, password);
            System.out.println("DB connected");
        } catch (SQLException | IllegalArgumentException ex) {
            LogManager.severe("Cant connect");
            ex.printStackTrace(System.err);
        } finally {
            if (connection == null) {
                System.exit(-1);
            }
        }

        return connection;
    }
}
