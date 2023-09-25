package by.javaguru.dao;

import by.javaguru.entity.User;

import java.util.Optional;

public interface UserDao {
    boolean save(User user);
    Optional<User> getUserByLogin(String login);
}
