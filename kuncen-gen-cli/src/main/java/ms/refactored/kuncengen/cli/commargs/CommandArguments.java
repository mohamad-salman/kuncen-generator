package ms.refactored.kuncengen.cli.commargs;

import ms.refactored.kuncengen.api.PasswordGenerator;

public sealed abstract class CommandArguments
        permits SingleDashCommandArguments, MultipleDashCommandArguments {

    protected final int DEFAULT_LENGTH_VALUE = 8;

    protected final String[] args;

    public CommandArguments(String[] args) {
        this.args = args;
    }

    public boolean isEmpty() {
        return args.length == 0;
    }

    public void printMenu() {
        System.out.println("kuncen-gen (version 1.1)");
        System.out.println("""
                Usage:
                 kuncen-gen [CUSTOM_OPTIONS]...[LENGTH]...[CASE]\t
                 Example: kuncen-gen -lcns '10' upper
                  -l, --length [values]    specified length default 8.
                  -c, --case [upper/lower]     adjust case upper or lower.
                  -n, --numeric     with numeric digits.
                  -s, --symbols [custom_char]     with symbol can add custom char.
                  -e, --easy [read and write]     easy to read or write pass.
                """);
    }

    public abstract boolean isValid();
    public abstract PasswordGenerator createPasswordGenerator();
}
