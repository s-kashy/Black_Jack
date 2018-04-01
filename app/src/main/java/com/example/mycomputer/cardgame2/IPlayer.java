package com.example.mycomputer.cardgame2;

/**
 * Created by My computer on 1/1/2018.
 */

public interface IPlayer {
    void addCard(Card card);
    Status getStatus();

    int[] getValue();
}
