package by.javaguru.service;

import by.javaguru.dao.UserDao;
import by.javaguru.dao.UserDaoJsonImpl;
import by.javaguru.dto.CreateUserDto;
import by.javaguru.dto.UserDto;
import by.javaguru.entity.User;
import by.javaguru.exception.ValidationException;
import by.javaguru.mapper.CreateUserMapper;
import by.javaguru.mapper.UserDtoMapper;
import by.javaguru.validator.UserValidator;
import by.javaguru.validator.ValidatorResult;

import java.util.Optional;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private static final UserDao userDao = UserDaoJsonImpl.getInstance();
    private static final UserDtoMapper USER_DTO_MAPPER = UserDtoMapper.getInstance();
    private static final CreateUserMapper CREATE_USER_MAPPER = CreateUserMapper.getInstance();

    private UserService() {}

    public static UserService getInstance() {
        return INSTANCE;
    }

    public boolean save(CreateUserDto createUserDto) {
        UserValidator userValidator = UserValidator.getInstance();
        ValidatorResult validatorResult = userValidator.validate(createUserDto);

        if (!validatorResult.isValid()) {
            throw new ValidationException(validatorResult.getErrors());
        }

        UserDto userDto = CREATE_USER_MAPPER.mapFrom(createUserDto);

        User user = USER_DTO_MAPPER.mapFrom(userDto);

        return userDao.save(user);
    }

    public Optional<User> getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    public boolean isCorrectUserDataAuth(String login, String password) {
        Optional<User> user = userDao.getUserByLogin(login);
        return user.map(u -> u.getPassword().equals(password))
                .orElse(false);
    }
}
