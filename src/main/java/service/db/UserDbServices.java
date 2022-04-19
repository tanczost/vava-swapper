package service.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDbServices extends PostgresConnection {
    public static int insertUserDb(String nick, String firstName, String lastName, String email, String town, String street, String school, String password) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO users(nick, first_name, last_name, email, town, street, school, password) " +
                    "VALUES((?), (?), (?), (?), (?), (?), (?), (?) );");

            setUserAttributes(nick, firstName, lastName, email, town, street, school, stmt);
            stmt.setString(8, password);

            return stmt.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return 0;
        }
    }

    public static ResultSet loginUserDb(String nick, String password) throws SQLException {
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

            if(isResultEmpty(sqlReturnValues)){
                System.out.println("hah");
                return null;
            }
            return sqlReturnValues;
    }

    public static int updateUserDb(String nick, String firstName, String lastName, String email, String town, String street, String school){
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE users SET " +
                            "nick = (?), " +
                            "first_name = (?), " +
                            "last_name = (?), " +
                            "email = (?), " +
                            "town = (?), " +
                            "street = (?), " +
                            "school = (?) " +
                            "WHERE users.nick LIKE (?)");

            setUserAttributes(nick, firstName, lastName, email, town, street, school, stmt);

            stmt.setString(8, nick);

            return stmt.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return 0;
        }
    }

    private static void setUserAttributes(String nick, String firstName, String lastName, String email, String town, String street, String school,  PreparedStatement stmt) throws SQLException {
        stmt.setString(1, nick);
        stmt.setString(2, firstName);
        stmt.setString(3, lastName);
        stmt.setString(4, email);
        stmt.setString(5, town);
        stmt.setString(6, street);
        stmt.setString(7, school);
    }

    public static boolean isUserAdmin(int userId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT is_admin FROM users WHERE id = (?)");

        stmt.setInt(1, userId);

        ResultSet sqlReturnValues = stmt.executeQuery();

        if(isResultEmpty(sqlReturnValues)){
            System.out.println("hah");
            return false;
        }
        sqlReturnValues.next();
        return sqlReturnValues.getBoolean(1);
    }
}
