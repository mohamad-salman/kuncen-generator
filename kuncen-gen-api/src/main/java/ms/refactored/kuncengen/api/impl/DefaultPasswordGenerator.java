package ms.refactored.kuncengen.api.impl;

import ms.refactored.kuncengen.api.DefaultPasswordOptions;
import java.util.Random;
import ms.refactored.kuncengen.api.PasswordGenerator;

public final class DefaultPasswordGenerator extends PasswordGenerator<DefaultPasswordOptions> {

    public DefaultPasswordGenerator(DefaultPasswordOptions customOptions) {
        super(customOptions);
        
        System.out.println("--> using default-password-generator algorithm...");
    }

    @Override
    public String generate() {
        var characters = new StringBuilder(Characters.ALPHABET);
        appendAdditionalChars(characters);

        var randomText = createRandomTextFrom(characters);

        return passwordOptions.hasCase() ? adjustCase(randomText, passwordOptions.getCaseValue()) : randomText;
    }

    private void appendAdditionalChars(StringBuilder characters) {
        //adding symbols and alphabet on one variabel
        if (passwordOptions.hasSymbols()) {
            characters.append(Characters.SYMBOLS);
        }

        if (passwordOptions.hasNumber()) {
            characters.append(Characters.NUMBER);
        }
    }

    private String createRandomTextFrom(StringBuilder characters) {
        var random = new Random();
        var randomText = new StringBuilder();

        for (int i = 0; i < passwordOptions.getPasswordLength(); i++) {
            // generate random index number
            int index = random.nextInt(characters.length());
            // get character specified by index
            char randomChar = characters.charAt(index);
            // append the character to string builder
            randomText.append(randomChar);
        }

        return randomText.toString();
    }

   

}
