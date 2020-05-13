package com.example.service;

import com.example.entity.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUsers();

    public User findUserById(long id);

    public void updateUser(User user);

    public void removeUserById(long id);
}
