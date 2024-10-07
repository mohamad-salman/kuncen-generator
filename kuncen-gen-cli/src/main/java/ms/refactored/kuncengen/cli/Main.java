package ms.refactored.kuncengen.cli;

import ms.refactored.kuncengen.cli.commargs.CommandArgumentsFactory;

public class Main {

    public static void main(String[] args) {

        try {
            var commandArguments = CommandArgumentsFactory.createCommandArguments(args);
            if (!commandArguments.isEmpty()) {

                if(commandArguments.isValid()) {
                var passwordGenerator = commandArguments.createPasswordGenerator();
                var passwordGenerated = passwordGenerator.generate();
                
                System.out.println("password generated: " + passwordGenerated);                    
                }
                
            } else {
                System.out.println("kuncen-gen: missing operand\n" + "Try 'kuncen-gen -h or --help' for more information.");
            }

        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid arguments. Try 'kuncen-gen -h or --help' for more information.");
        }

    }

}
