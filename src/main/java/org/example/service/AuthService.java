package org.example.service;

import org.example.model.User;
import org.example.util.AuthException;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private final Map<String, User> usersByLogin = new HashMap<>();
    private User currentUser;

    public void register(User user) {
        if (usersByLogin.containsKey(user.getLogin())){
            throw new AuthException("User already exists");
        }
        usersByLogin.put(user.getLogin(), user);
    }

    public User login(String login, String rawPassword){
        User user = usersByLogin.get(login);
        if (user == null|| !user.checkPassword(rawPassword)){
            throw new AuthException("Invalid credentials");
        }
        this.currentUser = user;
        return user;
    }
    public void logout(){
        this.currentUser = null;
    }
    public User getCurrentUser(){
        return currentUser;
    }
    public boolean loginExists(String login){
        return this.usersByLogin.containsKey(login);
    }

}
