package ms.refactored.kuncengen.cli.commargs;

import java.util.Optional;
import ms.refactored.kuncengen.api.DefaultPasswordOptions;
import ms.refactored.kuncengen.api.EasyPasswordOptions;
import ms.refactored.kuncengen.api.PasswordGenerator;
import ms.refactored.kuncengen.api.PasswordGeneratorFactory;
import ms.refactored.kuncengen.api.PasswordOptions;
import ms.refactored.kuncengen.api.PasswordOptions.Case;
import ms.refactored.kuncengen.cli.commargs.options.SingleDashCommandArgumentOptions;

final class SingleDashCommandArguments extends CommandArguments {

    private final SingleDashCommandArgumentOptions customOptions;
    private final Optional<String> secondArgument;
    private final Optional<String> thirdArgument;

    public SingleDashCommandArguments(String[] args) {
        super(args);

        customOptions = getFirstArgument();
        secondArgument = getSecondArgument();
        thirdArgument = getThirdArgument();
    }

    private SingleDashCommandArgumentOptions getFirstArgument() {
        return new SingleDashCommandArgumentOptions(args[0]);
    }

    private Optional<String> getSecondArgument() {
        if ((args.length == 2 || args.length == 3)
                && (args[1] != null)) {
            return Optional.of(args[1]);
        }
        return Optional.empty();
    }

    private Optional<String> getThirdArgument() {
        if (args.length == 3 && args[2] != null) {
            return Optional.of(args[2]);
        }
        return Optional.empty();
    }

    @Override
    public boolean isValid() {
        var valid = true;

        // syntaxnya: kuncen-gen [CUSTOM_OPTIONS]...[LENGTH]...[CASE]
        // jadi total jumlah argumen hanya 3
        if (argumentsMoreThenMaxLimit()) {
            System.out.println("Jumlah arguman max 3");
            valid = false;

            // bila ada option -h maka tampilkan menu help,
            // options lainnya  diabaikan
        } else if (customOptionsHasHelp()) {
            if (customOptions.length() > 1) {
                System.out.println("ada option -h, menampilkan menu\n");
            }
            printMenu();
            valid = false;

        } else if (customOptions.hasEasy()) {
            if(customOptions.length() > 1) {
                System.out.println("ada option -e, menggunakan algoritma easy-password-generator\n");
            }
            
        } else if (customOptions.hasUnknownOptions()) {
            valid = false;

        } else if (customOptionsHasCaseButNoValue()) {
            System.out.println("ada option -c namun tidak ditentukan upper/lower");
            valid = false;
        }
        // tambahkan lagi untuk cek validitas...

        return valid;
    }

    private boolean argumentsMoreThenMaxLimit() {
        final int MAX_LIMIT = 3;

        return args.length > MAX_LIMIT;
    }

    private boolean customOptionsHasHelp() {
        return customOptions.hasHelp();
    }

    private boolean customOptionsHasCaseButNoValue() {
        if (customOptions.hasCase()) {
            if (onlyFirstArgumentExist()) {
                return true;

            } else if (firstAndSecondArgumentExist()) {
                if (secondArgumentIsNotEqualUpperOrLower()) {
                    return true;
                }
            } else if (allArgumentExist()) {
                if (secondArgumentIsNotEqualUpperOrLower()
                        && thirdArgumentIsNotEqualUpperOrLower()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean onlyFirstArgumentExist() {
        return secondArgument.isEmpty() && thirdArgument.isEmpty();
    }

    private boolean firstAndSecondArgumentExist() {
        return secondArgument.isPresent()
                && (!thirdArgument.isPresent() || thirdArgument.isEmpty());
    }

    private boolean secondArgumentIsNotEqualUpperOrLower() {
        return !isEqualsUpper(secondArgument) && !isEqualsLower(secondArgument);
    }

    private boolean isEqualsUpper(Optional<String> argument) {
        final String UPPER = "upper";

        return argument.get().equalsIgnoreCase(UPPER);
    }

    private boolean isEqualsLower(Optional<String> argument) {
        final String LOWER = "lower";

        return argument.get().equalsIgnoreCase(LOWER);
    }

    private boolean allArgumentExist() {
        return secondArgument.isPresent() && thirdArgument.isPresent();
    }

    private boolean thirdArgumentIsNotEqualUpperOrLower() {
        return !isEqualsUpper(thirdArgument) && !isEqualsLower(thirdArgument);
    }

    @Override
    public PasswordGenerator createPasswordGenerator() {
        var passwordOptions = createPasswordOptions();

        return PasswordGeneratorFactory.create(passwordOptions);
    }

    public PasswordOptions createPasswordOptions() {
        int passwordLength = getPasswordLength();
        // bila custom options ada -e (easy)
        // maka password options yang dibuat EasyPasswordOptions
        // options lainnya diabaikan
        return customOptions.hasEasy()
                ? new EasyPasswordOptions(passwordLength)
                : createDefaultPasswordOptions(passwordLength);
    }

    private int getPasswordLength() {
        if (customOptions.hasLength()) {
            if (firstAndSecondArgumentExist()) {
                if (isDigit(secondArgument)) {
                    return toInt(secondArgument);
                }
            } else if (allArgumentExist()) {
                if (isDigit(secondArgument)) {
                    return toInt(secondArgument);
                } else if (isDigit(thirdArgument)) {
                    return toInt(thirdArgument);
                }
            }
        }
        return DEFAULT_LENGTH_VALUE;
    }

    private boolean isDigit(Optional<String> argument) {
        return argument.get().chars().allMatch(Character::isDigit);
    }

    private int toInt(Optional<String> argument) {
        return Integer.valueOf(argument.get());
    }

    private PasswordOptions createDefaultPasswordOptions(int length) {
        var defaultOptions = new DefaultPasswordOptions(length);

        if (customOptions.hasCase()) {
            var caseValue = getCaseValue();
            caseValue.ifPresent(cv -> defaultOptions.setCase(cv));
        }

        if (customOptions.hasNumeric()) {
            defaultOptions.addNumber();
        }

        if (customOptions.hasSymbols()) {
            defaultOptions.addSymbols();
        }

        return defaultOptions;
    }

    private Optional<Case> getCaseValue() {
        Optional<Case> caseValue = Optional.empty();
        if (firstAndSecondArgumentExist()) {
            caseValue = isEqualsUpper(secondArgument)
                    ? Optional.of(Case.UPPER) : Optional.of(Case.LOWER);

        } else if (allArgumentExist()) {
            if (isEqualsUpper(secondArgument)) {
                caseValue = Optional.of(Case.UPPER);
            } else if (isEqualsLower(secondArgument)) {
                caseValue = Optional.of(Case.LOWER);
            } else if (isEqualsUpper(thirdArgument)) {
                caseValue = Optional.of(Case.UPPER);
            } else if (isEqualsLower(thirdArgument)) {
                caseValue = Optional.of(Case.LOWER);
            }
        }

        return caseValue;
    }

}
