package com.example.service;

import com.example.entity.Card;

import java.util.Set;

public interface CardService {
    Set<Card> findAll();

    Card findCardById(long id);

    void updateCard(Card card);

    void removeCardById(long id);
}
