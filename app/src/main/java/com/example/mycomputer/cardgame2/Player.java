package com.example.mycomputer.cardgame2;


import java.util.ArrayList;

 public abstract class Player implements IPlayer {
    private ArrayList<Card> cards;

    public Player() {
        this.cards = new ArrayList<>();

    }

    @Override
    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public int[] getValue() {
        int valueCards = 0;
        int amountOfAce = 0;
        int val[] = new int[2];
        int i = 0;
        for (Card card : this.cards) {
            if (card.getCardValue() != 1) {
                valueCards += card.getCardValue();

            } else {
                amountOfAce++;
            }
        }
        val[i] = amountOfAce + valueCards;
        val[i+1] = -1;
        if (amountOfAce != 0) {
            val[i+1] = valueCards + (amountOfAce - 1 + 11);
        }
        return val;
    }
    public void restArray(){
        this.cards.clear();
    }
}
