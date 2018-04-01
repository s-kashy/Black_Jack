package com.example.mycomputer.cardgame2;


import com.example.mycomputer.cardgame2.Player;
import com.example.mycomputer.cardgame2.Status;

class HumanPlayer extends Player {
    public HumanPlayer() {
        super();
    }

    @Override
    public Status getStatus() {
        int i = 0;
        int val[] = this.getValue();
        if (val[i+1] != -1) {
            if (val[i] > 21 && val[i + 1] > 21) {
                return Status.LOSE;
            }

        }
        else if (val[i]>21 && val[i+1]==-1){
            return Status.LOSE;
        }
        return Status.HITME;
    }


}

