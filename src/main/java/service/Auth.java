package service;

import models.Account;
import service.db.UserDbServices;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {
    public static boolean registration(String nick, String firstName, String lastName, String email, String town, String street, String school, String password){

//        TODO: input validation, sql insertion, make password hash

        String passwordHash = password;

        int result = UserDbServices.insertUserDb(nick, firstName, lastName, email, town,street, school, passwordHash);

        System.out.println(result);

        return result != 0;
    }


    public static boolean login(String nick, String password) throws SQLException {
        String passwordHash = password;

        ResultSet resultSet = UserDbServices.loginUserDb(nick, passwordHash);

        Account.createAccount(
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
