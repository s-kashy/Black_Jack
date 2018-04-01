package com.example.mycomputer.cardgame2;

/**
 * Created by My computer on 1/1/2018.
 */

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by My computer on 12/24/2017.
 */

class Deck implements ICards {
    ArrayList<Card> arrayOfCards;


    public Deck() {
        arrayOfCards = new ArrayList<>();

    }

    @Override
    public Card getCard() {
        if (arrayOfCards != null) {
            Card cards = arrayOfCards.get(0);
            removeCard(arrayOfCards.get(0));
            return cards;

        }
        return null;
    }

    private void removeCard(Card cards) {
        arrayOfCards.remove(cards);
    }

    @Override
    public void addCard(Card cards) {
        arrayOfCards.add(cards);
    }

    @Override
    public void shuffleCards() {
        Collections.shuffle(arrayOfCards);
    }





}

