package service.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.SwapperApplication;
import io.github.cdimascio.dotenv.Dotenv;
import observer.Observer;

public abstract class PostgresConnection {
    private static Dotenv dotenv = Dotenv.configure().ignoreIfMissing().ignoreIfMalformed().load();
    private static String url = dotenv.get("DB_HOST");
    private static int port = Integer.parseInt(dotenv.get("DB_PORT"));
    private static String databaseName = dotenv.get("DB_NAME");
    private static String username = dotenv.get("DB_USER");
    private static String password = dotenv.get("DB_PASSWORD");
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
            ex.printStackTrace(System.err);

            SwapperApplication.getLogManager().update("Failed to establish connection.", Observer.LEVEL.severe);
        } finally {
            SwapperApplication.getLogManager().update("Application is shutting down.", Observer.LEVEL.severe);
            if (connection == null) {
                System.exit(-1);
            }
        }

        return connection;
    }
}
