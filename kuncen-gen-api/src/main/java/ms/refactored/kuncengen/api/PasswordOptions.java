package ms.refactored.kuncengen.api;

public sealed abstract class PasswordOptions
        permits DefaultPasswordOptions, EasyPasswordOptions {

    public enum Case {
        UPPER, LOWER;
    }

    public static final String LENGTH_SHORT = "l";
    public static final String CASE_SHORT = "c";
    public static final String NUMERIC_SHORT = "n";
    public static final String SYMBOLS_SHORT = "s";
    public static final String EASY_SHORT = "e";
    public static final String HELP_SHORT = "h";

    protected int passwordLength;

    public PasswordOptions(int passwordLength) {
        this.passwordLength = passwordLength;
    }

    public int getPasswordLength() {
        return passwordLength;
    }

}
