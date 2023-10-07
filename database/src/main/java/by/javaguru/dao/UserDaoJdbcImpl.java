package by.javaguru.dao;

import by.javaguru.entity.User;
import by.javaguru.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDaoJdbcImpl implements UserDao {
    private static UserDaoJdbcImpl INSTANCE = new UserDaoJdbcImpl();
    private final Connection connection = ConnectionManager.open();
    private final String SAVE_SQL = """
            INSERT INTO users (login, password, name, email, age)
            VALUES (?, ?, ?, ?, ?)
            """;

    private final String FIND_ALL_SQL = """
            SELECT id, login, password, name, email, age
            FROM users
            """;
    private final String FIND_BY_LOGIN_SQL = FIND_ALL_SQL + "WHERE login = ?";

    private UserDaoJdbcImpl() {

    }

    public static UserDaoJdbcImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean save(User user) {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.setInt(5, user.getAge());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
                return true;
            }

            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_SQL)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(buildUser(resultSet));
            }

            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, User> findAll() {
        try (Statement statement = connection.createStatement()) {
            Map<String, User> users = new HashMap<>();

            ResultSet resultSet = statement.executeQuery(FIND_ALL_SQL);

            while (resultSet.next()) {
                User user = buildUser(resultSet);
                users.put(user.getLogin(), user);
            }

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setAge(resultSet.getInt("age"));
        return user;
    }
}
