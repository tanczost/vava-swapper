package service.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static service.PostgresConnection.connection;

public class UserDbServices {
    public static int insertUserDb(String nick, String firstName, String lastName, String email, String town, String street, String school, String password) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO users(nick, first_name, last_name, email, town, street, school, password) " +
                    "VALUES((?), (?), (?), (?), (?), (?), (?), (?) );");

            stmt.setString(1, nick);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
            stmt.setString(5, town);
            stmt.setString(6, street);
            stmt.setString(7, school);
            stmt.setString(8, password);

            return stmt.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return 0;
        }
    }

    public static ResultSet loginUserDb(String nick, String password){
        try{
            PreparedStatement stmt = connection.prepareStatement("SELECT " +
                    "users.nick, " +
                    "users.first_name, " +
                    "users.last_name, " +
                    "users.email, " +
                    "users.town, " +
                    "users.street, " +
                    "users.school, " +
                    "users.id " +
                    "FROM users WHERE nick LIKE (?) AND password LIKE (?)");

            stmt.setString(1, nick);
            stmt.setString(2, password);

            ResultSet sqlReturnValues = stmt.executeQuery();
            sqlReturnValues.next();

            return sqlReturnValues;
        }
        catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
