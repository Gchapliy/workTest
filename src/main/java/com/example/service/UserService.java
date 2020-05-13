package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();

    User findUserById(long id);

    void updateUser(User user);

    void removeUserById(long id);

    void addCardToUser(long userId, long cardId);

    void removeCard(long userId, long cardId);

    void updateUserData(User user);
}
