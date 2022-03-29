package service;

import java.util.regex.Pattern;

public class Validator {
    public static boolean validDbInput(String string){
        if(string == null || string.isEmpty()) return false;

        String regex = "[A-Za-z0-9]{1,30}";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        return !p.matcher(string).find();

    }

    public static boolean validDbInputEmail(String string){
        if(string == null) return false;

        String regex = "[A-Za-z0-9._%+-]{1,20}@[A-Za-z0-9._%+-]{1,20}\\.[A-Za-z]{2,6}";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        System.out.println(p.matcher(string).find());

        return !p.matcher(string).find();
    }

    public static boolean validDbPassword(String string){
        if(string == null) return false;

        String regex = "[A-Za-z0-9_#$@]{6,30}";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        return !p.matcher(string).find();

    }
}
