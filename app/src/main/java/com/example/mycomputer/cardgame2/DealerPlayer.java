package com.example.mycomputer.cardgame2;

/**
 * Created by My computer on 1/1/2018.
 */

public class DealerPlayer extends Player {


    public Status getStatus() {
        int i = 0;
        int val[] = getValue();///-> From Super class
        if (val[i + 1] != -1) {
            if (val[i] < 17 && val[i + 1] < 17)
                return Status.HITME;
        } else if ((val[i] >= 17 && val[i] <= 21))
            return Status.DONE;
        return Status.LOSE;

    }

}
