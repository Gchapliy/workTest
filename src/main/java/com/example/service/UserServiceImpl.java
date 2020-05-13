package com.example.service;

import com.example.dao.UserDAO;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    public List<User> findAllUsers(){
        return userDAO.findAll();
    }

    public User findUserById(long id){
        return userDAO.findById(id).get();
    }

    public void updateUser(User user){
        userDAO.save(user);
    }

    public void removeUserById(long id){
        userDAO.deleteById(id);
    }
}
