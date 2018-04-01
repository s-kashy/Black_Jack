package com.example.mycomputer.cardgame2;

/**
 * Created by My computer on 1/1/2018.
 */

public class Card {
    private int cardValue;
    private int cardImage;

    public Card( int cardImage,int  cardValue) {
        this.cardValue = cardValue;
        this.cardImage = cardImage;
    }

    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }


    public int getCardValue() {

        return cardValue;
    }


    public int getCardImage() {
        return cardImage;
    }

    public void setCardImage(int cardImage) {

        this.cardImage = cardImage;
    }
}
