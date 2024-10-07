package ms.refactored.kuncengen.cli.commargs;

import ms.refactored.kuncengen.api.PasswordGenerator;

final class MultipleDashCommandArguments extends CommandArguments {

    public MultipleDashCommandArguments(String[] args) {
        super(args);
    }

    @Override
    public boolean isValid() {
        return false;
    }

    
    @Override
    public PasswordGenerator createPasswordGenerator() {
        return null;
    }

}
