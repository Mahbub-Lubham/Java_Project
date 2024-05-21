package com.cookbook;

import java.util.*;


public class UserManager {
    private HashMap<String, User> users;

    public UserManager() {
        users = new HashMap<>();
    }

    public boolean registerUser(String username, String password, String email) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password, email));
            return true;
        }
        return false;
    }

    public User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        return null;
    }

    public boolean updateUserEmail(String username, String newEmail) {
        User user = users.get(username);
        if (user != null) {
            user.setEmail(newEmail);
            return true;
        }
        return false;
    }
}
