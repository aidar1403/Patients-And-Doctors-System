package org.example.model;

import org.example.util.PasswordHasher;
import java.util.Objects;

public abstract class User {
private final String login;
private final String passwordHash;
private final Role role;

protected User(String login, String passwordHash, Role role) {
    if (login == null || login.isBlank()){
        throw new IllegalArgumentException("login cannot be null or blank");
    }
    this.login = login;
    this.passwordHash = passwordHash;
    this.role = role;
}

public String getLogin() {return login;}
    public Role getRole(){return role;}

    public boolean checkPassword(String rawPassword){
    return PasswordHasher.hash(rawPassword).equals(passwordHash);
    }

    @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return login.equals(user.login);
    }

    @Override
    public int hashCode() {
    return Objects.hash(login);
    }
}
