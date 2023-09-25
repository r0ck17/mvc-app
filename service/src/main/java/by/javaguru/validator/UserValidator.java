package by.javaguru.validator;

import by.javaguru.dto.CreateUserDto;

public class UserValidator implements Validator<CreateUserDto> {
    private static final UserValidator INSTANCE = new UserValidator();
    private final String LOGIN_PATTERN = "[a-zA-Z]{6,20}";
    private final String PASSWORD_PATTERN = "\\w{6,20}";
    private final String NAME_PATTERN = "[a-zA-Z]{6,20}";
    private final String EMAIL_PATTERN = "[a-zA-Z]{3,20}@[a-zA-Z]{3,20}\\.(com|ru)";
    private final String AGE_PATTERN = "([0-9]|[1-9][0-9])";

    private UserValidator() {}

    public static UserValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public ValidatorResult validate(CreateUserDto userDto) {
        ValidatorResult validatorResult = new ValidatorResult();

        if (!isMatchesRegexp(userDto.getLogin(), LOGIN_PATTERN)) {
            validatorResult.add(Error.of("invalid.login", "Login is invalid."));
        }

        if (!isMatchesRegexp(userDto.getPassword(), PASSWORD_PATTERN)) {
            validatorResult.add(Error.of("invalid.password", "Password is invalid."));
        }

        if (!isMatchesRegexp(userDto.getName(), NAME_PATTERN)) {
            validatorResult.add(Error.of("invalid.name", "Name is invalid."));
        }

        if (!isMatchesRegexp(userDto.getEmail(), EMAIL_PATTERN)) {
            validatorResult.add(Error.of("invalid.email", "Email is invalid."));
        }

        if (!isMatchesRegexp(String.valueOf(userDto.getAge()), AGE_PATTERN)) {
            validatorResult.add(Error.of("invalid.age", "Age is invalid."));
        }

        return validatorResult;
    }

    private static boolean isMatchesRegexp(String value, String regexp) {
        return (value != null) && (value.matches(regexp));
    }
}
