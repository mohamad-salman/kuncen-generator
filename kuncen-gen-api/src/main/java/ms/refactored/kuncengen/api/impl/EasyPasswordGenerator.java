package ms.refactored.kuncengen.api.impl;

import java.util.Random;
import ms.refactored.kuncengen.api.EasyPasswordOptions;
import ms.refactored.kuncengen.api.PasswordGenerator;

public final class EasyPasswordGenerator extends PasswordGenerator<EasyPasswordOptions> {

    public EasyPasswordGenerator(EasyPasswordOptions customOptions) {
        super(customOptions);

        System.out.println("--> using easy-password-generator algorithm...");
    }

    @Override
    public String generate() {
        var randomText = new StringBuilder();
        var random = new Random();
        //adding symbols and alphabet on one variabel
        for (int i = 0; i < passwordOptions.getPasswordLength(); i++) {
            if (i % 2 == 0) {
                randomText.append(Characters.CONSONANTS
                        .charAt(random.nextInt(Characters.CONSONANTS.length()))
                );
            } else {
                randomText.append(Characters.VOWELS
                        .charAt(random.nextInt(Characters.VOWELS.length()))
                );
            }
        }
        return randomText.toString();
    }

}
