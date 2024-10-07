package ms.refactored.kuncengen.api;

public final class DefaultPasswordOptions extends PasswordOptions {

    private boolean hasSymbols;
    private boolean hasNumber;
    private boolean hasCase;
    private Case caseValue;

    public DefaultPasswordOptions(int passwordLength) {
        super(passwordLength);
        
        hasSymbols = false;
        hasNumber = false;
        hasCase = false;
    }

    public DefaultPasswordOptions addSymbols() {
        this.hasSymbols = true;
        
        return this;
    }

    public DefaultPasswordOptions addNumber() {
        this.hasNumber = true;
        
        return this;
    }

    public DefaultPasswordOptions setCase(Case caseValue) {
        this.caseValue = caseValue;
        hasCase = true;
        
        return this;
    }

    public boolean hasSymbols() {
        return hasSymbols;
    }

    public boolean hasNumber() {
        return hasNumber;
    }

    public boolean hasCase() {
        return hasCase;
    }

    public Case getCaseValue() {
        return caseValue;
    }

}
