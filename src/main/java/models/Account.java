package models;

public class Account {
    public static User currentUser = null;

    public static void createAccount(String nick, String firstName, String lastName, String email, String town, String street, String school){
        if(currentUser == null){
            currentUser = new User(nick, firstName, lastName, email, town, street, school);
        }
        else{
            System.out.println("User already logged in");
        }
    }

    public static String checkLogin(){
        if(currentUser == null){
            return "Nobody is logged in";
        }
        else{
            return currentUser.toString();
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
