package services.common;

import models.Account;
import org.apache.commons.codec.digest.DigestUtils;
import services.db.UserDbServices;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {
    public static boolean registration(String nick, String firstName, String lastName, String email, String town, String street, String school, String password) throws Exception {
//        if (!Validator.validDbInputEmail(email) || !Validator.validDbInput(nick) || !Validator.validDbInput(firstName) ||
//                !Validator.validDbInput(lastName) || !Validator.validDbInput(email) || !Validator.validDbInput(town) ||
//                !Validator.validDbInput(street) || !Validator.validDbInput(school) || !Validator.validDbPassword(password)) {
//            throw new Exception("Wrong input");
//        }

        String passwordHash = DigestUtils.sha256Hex(password);
        int result = UserDbServices.insertUserDb(nick, firstName, lastName, email, town, street, school, passwordHash);
        return result != 0;
    }


    public static boolean login(String nick, String password) throws SQLException {
        String passwordHash = DigestUtils.sha256Hex(password);
        ResultSet resultSet = UserDbServices.loginUserDb(nick, passwordHash);

        if (resultSet == null) {
            System.out.println("No data");
            return false;
        }

        resultSet.next();

        Account.getInstance().createAccount(
                resultSet.getInt(8),
                resultSet.getString(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7)
        );

        return true;
    }
}
