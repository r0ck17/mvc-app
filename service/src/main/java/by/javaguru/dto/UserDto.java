package by.javaguru.dto;

public class UserDto {
    private String login;
    private String password;
    private String email;
    private String name;
    private int age;

    public UserDto() {}

    public UserDto(String login, String password, String email, String name, int age) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.name = name;
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
