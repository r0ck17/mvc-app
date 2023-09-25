package by.javaguru.mapper;

import by.javaguru.dto.CreateUserDto;
import by.javaguru.dto.UserDto;

public class CreateUserMapper implements Mapper<CreateUserDto, UserDto> {
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();
    private CreateUserMapper() {}

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public UserDto mapFrom(CreateUserDto object) {
        return new UserDto(object.getLogin(),
                object.getPassword(),
                object.getEmail(),
                object.getName(),
                Integer.parseInt(object.getAge())
        );
    }
}
