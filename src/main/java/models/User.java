package models;

public class User {
    private int id;
    private String nickName;
    private String firstName;
    private String lastName;
    private String email;
    private String town;
    private String street;

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    private String school;
    private boolean hasEnglishLanguageSelected = true;

    public User(int id, String nickName, String firstName, String lastName, String email, String town, String street, String school) {
        this.id = id;
        this.nickName = nickName;
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
                "nick='" + nickName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", town='" + town + '\'' +
                ", street='" + street + '\'' +
                ", school='" + school + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public boolean hasEnglishLanguageSelected() {
        return hasEnglishLanguageSelected;
    }

    public void setHasEnglishLanguageSelected(boolean hasEnglishLanguageSelected) {
        this.hasEnglishLanguageSelected = hasEnglishLanguageSelected;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getTown() {
        return town;
    }

    public String getStreet() {
        return street;
    }

    public String getSchool() {
        return school;
    }
}
