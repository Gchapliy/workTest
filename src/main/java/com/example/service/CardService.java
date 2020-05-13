package com.example.service;

import com.example.entity.Card;

public interface CardService {
    public Card findCardById(long id);

    public void updateCard(Card card);

    public void removeCardById(long id);
}
