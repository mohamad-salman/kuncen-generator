package ms.refactored.kuncengen.api;

import ms.refactored.kuncengen.api.impl.EasyPasswordGenerator;
import ms.refactored.kuncengen.api.impl.DefaultPasswordGenerator;

public class PasswordGeneratorFactory {

    public static PasswordGenerator create(PasswordOptions passwordOptions) {                
        return switch (passwordOptions) {
            case DefaultPasswordOptions defaultOps -> new DefaultPasswordGenerator(defaultOps);
            case EasyPasswordOptions easyOps -> new EasyPasswordGenerator(easyOps);
        };
    }

}
