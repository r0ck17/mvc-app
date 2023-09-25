package by.javaguru.mapper;

import by.javaguru.dto.UserDto;
import by.javaguru.entity.User;

public class UserDtoMapper implements Mapper<UserDto, User> {
    private static final UserDtoMapper INSTANCE = new UserDtoMapper();

    private UserDtoMapper() {}

    public static UserDtoMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public User mapFrom(UserDto object) {
        return new User(
                object.getLogin(),
                object.getPassword(),
                object.getEmail(),
                object.getName(),
                object.getAge()
        );
    }
}
