package by.javaguru.entity;

import lombok.Builder;
import lombok.NoArgsConstructor;

public class User {
    private static long counterId = 1;
    private long id;
    private String login;
    private String password;
    private String email;
    private String name;
    private int age;

    public User() {

    }

    public User(String login, String password, String email, String name, int age) {
        this.id = counterId++;
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
