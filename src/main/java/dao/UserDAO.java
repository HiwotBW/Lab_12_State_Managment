package dao;


import models.User;

import java.util.HashMap;

public class UserDAO {
    private final HashMap<String, User> userModelHashMap = new HashMap<String, User>();

    public UserDAO() {
        User userModel1 = new User("hiwot", "123");
        User userModel2 = new User("surafel", "123");
        User userModel3 = new User("bisrat", "123");

        userModelHashMap.put(userModel1.getUsername(), userModel1);
        userModelHashMap.put(userModel2.getUsername(), userModel1);
        userModelHashMap.put(userModel3.getUsername(), userModel1);
    }

    public User addUser(User userModel) {
        userModelHashMap.put(userModel.getUsername(), userModel);
        return userModel;
    }

    public User getUser(String username, String password) {
        User userModel = userModelHashMap.get(username);

        if (username.equals(userModel.getUsername()) && password.equals(userModel.getPassword())) {
            return userModel;
        }

        return null;
    }
    public User getUserByUserName(String username) {
        User userModel = userModelHashMap.get(username);


        return userModel;
    }

    public boolean authenticateUser(String username, String password) {
        User userModel = userModelHashMap.get(username);
        if (userModel == null) {
            return false;
        } else {
            return username.equals(userModel.getUsername()) && password.equals(userModel.getPassword());
        }
    }
}
