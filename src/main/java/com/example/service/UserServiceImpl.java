package com.example.service;

import com.example.dao.CardDAO;
import com.example.dao.UserDAO;
import com.example.entity.Card;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CardDAO cardDAO;

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

    public void updateUserData(User user){
        User inDb = userDAO.findById(user.getId()).get();
        user.setCards(inDb.getCards());
        userDAO.save(user);
    }


    public void addCardToUser(long userId, long cardId) {
        User user = userDAO.findById(userId).get();
        Card card = cardDAO.findById(cardId).get();
        user.getCards().add(card);
        userDAO.save(user);
    }

    public void removeCard(long userId, long cardId){
        User user = userDAO.findById(userId).get();
        user.setCards(user.getCards().stream().filter(f -> f.getId() != cardId).collect(Collectors.toSet()));
        userDAO.save(user);
    }
}
