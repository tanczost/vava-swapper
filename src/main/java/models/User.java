package models;
import java.util.ArrayList;

public class User {
    private String nick;
    private String firstName;
    private String lastName;
    private String email;
    private String town;
    private String street;
    private String school;

    private ArrayList<Integer> myProducts = new ArrayList<Integer>();

    public User(String userName, String firstName, String lastName, String email, String town, String street, String school) {
        this.nick = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.town = town;
        this.street = street;
        this.school = school;
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
                ", myProducts=" + myProducts +
                '}';
    }
}