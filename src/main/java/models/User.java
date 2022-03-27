package models;
import java.util.ArrayList;

public class User {
    private int id;
    private String nick;
    private String firstName;
    private String lastName;
    private String email;
    private String town;
    private String street;
    private String school;

    public User(int id,String userName, String firstName, String lastName, String email, String town, String street, String school) {
        this.id = id;
        this.nick = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.town = town;
        this.street = street;
        this.school = school;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "nick='" + nick + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", town='" + town + '\'' +
                ", street='" + street + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
