package com.api.appactivitats.entities.users.service;

import com.api.appactivitats.entities.users.domain.User;
import com.api.appactivitats.entities.users.dto.UserReference;

import java.util.List;
import java.util.Set;

public interface UserService {
    void createUser(User user);

    User readUser(String id);

    List<User> readAllUsers();

    Set<UserReference> readUsersByActivity(String id);

    void updateUser(String id, User userWithUpdates);

    void deleteUser(String id);

    void addActivityToUser(String id, String nameId);
}
