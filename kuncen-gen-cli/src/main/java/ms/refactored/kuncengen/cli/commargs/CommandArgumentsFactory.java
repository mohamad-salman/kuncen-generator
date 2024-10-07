package ms.refactored.kuncengen.cli.commargs;

import java.util.Arrays;

public class CommandArgumentsFactory {

    public static CommandArguments createCommandArguments(String[] args) {
        var dashCount = computeDash(args);
        
        return dashCount ==  1 ?
                new SingleDashCommandArguments(args)
                : new MultipleDashCommandArguments(args);
    }
    
    private static long computeDash(String[] args) {
        return Arrays.toString(args).chars()
                .filter(ch -> ch == '-')
                .count();
    }
}
