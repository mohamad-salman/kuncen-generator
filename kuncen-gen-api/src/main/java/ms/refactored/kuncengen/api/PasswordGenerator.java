package ms.refactored.kuncengen.api;

import ms.refactored.kuncengen.api.impl.EasyPasswordGenerator;
import ms.refactored.kuncengen.api.impl.DefaultPasswordGenerator;
import ms.refactored.kuncengen.api.PasswordOptions.Case;

public abstract sealed class PasswordGenerator<T extends PasswordOptions>
        permits DefaultPasswordGenerator, EasyPasswordGenerator {

    protected final T passwordOptions;

    public PasswordGenerator(T passwordOptions) {
        this.passwordOptions = passwordOptions;
    }

    protected String adjustCase(String randomText, Case caseValue) {
        return PasswordOptions.Case.UPPER.equals(caseValue)
                ? randomText.toUpperCase()
                : randomText.toLowerCase();
    }

    public abstract String generate();
}
