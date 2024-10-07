package ms.refactored.kuncengen.cli.commargs.options;

import java.util.List;
import ms.refactored.kuncengen.api.PasswordOptions;

public sealed abstract class CommandArgumentOptions
        permits SingleDashCommandArgumentOptions {

    protected int length;

    protected List<String> getListOfPasswordOptionsShort() {
        return List.of(PasswordOptions.LENGTH_SHORT,
                PasswordOptions.CASE_SHORT,
                PasswordOptions.NUMERIC_SHORT,
                PasswordOptions.SYMBOLS_SHORT,
                PasswordOptions.EASY_SHORT,
                PasswordOptions.HELP_SHORT
        );
    }

}
