package ms.refactored.kuncengen.cli.commargs.options;

import static ms.refactored.kuncengen.api.PasswordOptions.CASE_SHORT;
import static ms.refactored.kuncengen.api.PasswordOptions.EASY_SHORT;
import static ms.refactored.kuncengen.api.PasswordOptions.HELP_SHORT;
import static ms.refactored.kuncengen.api.PasswordOptions.LENGTH_SHORT;
import static ms.refactored.kuncengen.api.PasswordOptions.NUMERIC_SHORT;
import static ms.refactored.kuncengen.api.PasswordOptions.SYMBOLS_SHORT;

public final class SingleDashCommandArgumentOptions extends CommandArgumentOptions {

    private final String customOptions;

    public SingleDashCommandArgumentOptions(String firstArgument) {
        customOptions = dashRemoved(firstArgument);
    }

    private String dashRemoved(String firstArgument) {
        return firstArgument.replace("-", "");
    }

    public boolean hasHelp() {
        return customOptions.contains(HELP_SHORT);
    }

    public int length() {
        return customOptions.length();
    }

    public boolean hasEasy() {
        return customOptions.contains(EASY_SHORT);
    }

    public boolean hasCase() {
        return customOptions.contains(CASE_SHORT);
    }

    public boolean hasNumeric() {
        return customOptions.contains(NUMERIC_SHORT);
    }

    public boolean hasSymbols() {
        return customOptions.contains(SYMBOLS_SHORT);
    }

    public boolean hasUnknownOptions() {
        var unknown = false;

        for (int i = 0; i < customOptions.length(); i++) {
            var option = String.valueOf(customOptions.charAt(i));

            var match = getListOfPasswordOptionsShort().stream()
                    .anyMatch(opt -> opt.equals(option));

            if (!match) {
                unknown = true;
                System.out.println("option -" + option + " unknown\n");

                break;
            }
        }

        return unknown;
    }

    public boolean hasLength() {
        return customOptions.contains(LENGTH_SHORT);
    }

}
