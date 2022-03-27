package service.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static service.PostgresConnection.connection;

public class ProductDbServices {
    public static int insertProductDb(String name, String description, boolean topped, int userId){
        try {
            //TODO: add img_id
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO products(name, description, topped, user_id) " +
                    "VALUES((?), (?), (?), (?));");

            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setBoolean(3, topped);
            stmt.setInt(4, userId);

            return stmt.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return 0;
        }
    }
}
