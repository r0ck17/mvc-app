package by.javaguru.dao;

import by.javaguru.entity.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

public class UserDaoJsonImpl implements UserDao {
    private static final UserDaoJsonImpl INSTANCE = new UserDaoJsonImpl();
    private static final Path ROOT_PATH = Path.of("database").toAbsolutePath();
    private static final Path JSON_FILE_PATH = ROOT_PATH.resolve("database.json");

    private UserDaoJsonImpl() {
    }

    public static UserDaoJsonImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean save(User user) {
        Map<String, User> users = getAllUsersFromJson();
        users.put(user.getLogin(), user);
        UserDaoJsonImpl.writeUsersToJson(users);
        return true;
    }


    @Override
    public Optional<User> getUserByLogin(String login) {
        return Optional.ofNullable(getAllUsersFromJson().get(login));
    }

    public static void generateDatabase() {
        if (!Files.exists(ROOT_PATH)) {
            try {
                Files.createDirectory(ROOT_PATH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        User user1 = new User("user", "1", "test@mail.ru", "userName", 25);
        User user2 = new User("user", "1", "test@mail.ru", "userName", 25);

        UserDaoJsonImpl.getInstance().save(user1);
        UserDaoJsonImpl.getInstance().save(user2);
    }

    private static void writeUsersToJson(Map<String, User> users) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();

        try {
            objectWriter.writeValue(JSON_FILE_PATH.toFile(), users);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, User> getAllUsersFromJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(JSON_FILE_PATH.toFile(), new TypeReference<Map<String, User>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
