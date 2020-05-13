package com.example.service;

import com.example.dao.CardDAO;
import com.example.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService{
    @Autowired
    private CardDAO cardDAO;

    public Card findCardById(long id){
        return cardDAO.findById(id).get();
    }

    public void updateCard(Card card){
        cardDAO.save(card);
    }

    public void removeCardById(long id){
        cardDAO.deleteById(id);
    }
}
