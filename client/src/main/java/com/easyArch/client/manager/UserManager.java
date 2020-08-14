package com.easyArch.client.manager;

import com.easeArch.common.entry.User;

public class UserManager {
    private static final UserManager instance =new UserManager();
    private User user;

    public static UserManager getInstance() {
        return instance;
    }
    public User getUser() {
        return user;
    }

    public void addUser(User user) {
        this.user = user;
    }
}
