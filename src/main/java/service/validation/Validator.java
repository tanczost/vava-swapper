package service.validation;

import javafx.scene.control.Label;

import java.util.regex.Pattern;

public class Validator {
    public static boolean validDbInput(String string) {
        if (string == null) return false;

        String regex = "[A-Za-z0-9_]{1,30}";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        return !p.matcher(string).find();

    }

    public static boolean validDbInputEmail(String string, Label label) {
        String regex = "[A-Za-z0-9._%+-]{1,20}@[A-Za-z0-9._%+-]{1,20}\\.[A-Za-z]{2,6}";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        System.out.println(p.matcher(string).find());

        if (!p.matcher(string).find()) {
            label.setText("Bad email input");
            return false;
        }

        return true;
    }

    public static boolean validDbPassword(String string) {
        if (string == null) return false;

        String regex = "[A-Za-z0-9_#$@]{6,30}";

        // Compile the ReGex
        Pattern p = Pattern.compile(regex);

        return !p.matcher(string).find();

    }

    public static boolean validRegisterPassword(String string, Label label) {
        if (string.length() < 6 || string.length() > 30) {
            label.setText("Password is not in range 6 and 30");
            return false;
        }

        String regex = "[A-Z]";

        Pattern p = Pattern.compile(regex);

        if (!p.matcher(string).find()) {
            label.setText("Password doesn't contain uppercase");
            return false;
        }

        regex = "[0-9]";

        p = Pattern.compile(regex);

        if (!p.matcher(string).find()) {
            label.setText("Password doesn't contain numbers");
            return false;
        }

        regex = "[#$@]";

        p = Pattern.compile(regex);

        if (!p.matcher(string).find()) {
            label.setText("Password doesn't special symbols(#$@)");
            return false;
        }

        label.setText("");
        return true;

    }
}
