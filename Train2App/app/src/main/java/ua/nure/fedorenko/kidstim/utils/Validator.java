package ua.nure.fedorenko.kidstim.utils;

public class Validator {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    public static boolean isEmailValid(String email) {
        return email.matches(EMAIL_PATTERN);
    }
}
