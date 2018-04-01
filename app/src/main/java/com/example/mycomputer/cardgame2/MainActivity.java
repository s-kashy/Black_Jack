package com.example.mycomputer.cardgame2;

import android.app.Dialog;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout linePlayer;
    LinearLayout lineDealer;
    String inTheGame = "Still in the game";
    String playerLost="the player lost";
    String dealerLost = "the dealer lost";
    String drow = "It is a drow but dealer won";
    String playerWin = "The Player Won";
    String dealerWon = "The dealer won";
    ICards deck = new Deck();
    Button btnHit, btnStay, btnReset,btnReturn;
    Player player = new HumanPlayer();
    Player dealer = new DealerPlayer();
    ImageView img;
    Dialog dialog;
    TextView textView,txShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHit = (Button) findViewById(R.id.btnHit);
        btnStay = (Button) findViewById(R.id.btnStay);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnReset.setOnClickListener(this);
        btnStay.setOnClickListener(this);
        btnHit.setOnClickListener(this);
        lineDealer = (LinearLayout) findViewById(R.id.lineDealer);
        linePlayer = (LinearLayout) findViewById(R.id.linePlayer);
        textView = (TextView) findViewById(R.id.txResult);


        getCards();
        dealCards();


    }

    private void dealCards() {
        for (int i = 0; i < 2; i++) {
            Card cardPlayer = deck.getCard();
            Card cardDealer = deck.getCard();
            presentCard(cardPlayer, linePlayer);
            presentCard(cardDealer, lineDealer);
            player.addCard(cardPlayer);
            dealer.addCard(cardDealer);

        }
    }

    private void presentCard(Card card, LinearLayout linearLayout) {
        img = new ImageView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(500, 500);
        img.setLayoutParams(layoutParams);
        int imagKey = card.getCardImage();

        img.setImageResource(imagKey);
        linearLayout.addView(img);

    }

    public void getCards() {
        TypedArray img = getResources().obtainTypedArray(R.array.cards);
        Resources s = getResources();

        for (int i = 0; i < 53; i++) {
            Card card = new Card(img.getResourceId(i, -1), getValue(img.getResourceId(i, -1)));
            deck.addCard(card);
        }
        deck.shuffleCards();
    }

    private int getValue(int i) {


        String name = getResources().getResourceEntryName(i);
        int val = Integer.valueOf(name.substring(name.lastIndexOf("_") + 1));
        if (val > 10) {
            return 10;
        }
        return val;
    }

    @Override
    public void onClick(View v) {
        if (v == btnHit) {
            Card card = deck.getCard();
            player.addCard(card);
            if (!player.getStatus() .equals( Status.LOSE)) {
                presentCard(card, linePlayer);
                creatdialog(inTheGame);

            }
            else{
                btnHit.setEnabled(false);
                creatdialog(playerLost);
            }
        } else if (v == btnStay) {
            btnHit.setEnabled(false);
            while (dealer.getStatus() != Status.DONE) {
                Card card1 = deck.getCard();
                dealer.addCard(card1);
                presentCard(card1, lineDealer);
                if (dealer.getStatus().equals(Status.LOSE)) {
                    creatdialog(dealerLost);
                    break;
                }

            }
            if (dealer.getStatus().equals(Status.DONE)) {
                calulateResult();
            }


        } else if (v == btnReset) {
            restApplection();
        }
    }


    private void restApplection() {

        lineDealer.removeAllViews();
        linePlayer.removeAllViews();
        dealer.restArray();
        player.restArray();
        btnHit.setEnabled(true);
        textView.setText("");

        getCards();
        dealCards();

    }

    private void calulateResult() {
        int i = 0;
        int[] dealerVales = dealer.getValue();
        int[] playerValues = player.getValue();
        int tempPlayer = 21 - playerValues[i];
        int tempPlayer1 = 21 - playerValues[i + 1];

        int tempDealer = 21 - dealerVales[i];

        int tempDealer1 = 21 - dealerVales[i + 1];

        checkBelowZero(tempPlayer, tempPlayer1, tempDealer, tempDealer1);
        int resultDealer = tempDealer1;
        int resultPlayer = tempPlayer1;
        if (tempPlayer <= tempPlayer1) {
            resultPlayer = tempPlayer;
        }

        if (tempDealer <= tempDealer1) {
            resultDealer = tempDealer;

        }
        if (resultDealer < resultPlayer) {
            creatdialog(dealerWon);

        } else if (resultDealer > resultPlayer) {
            creatdialog(playerWin);

        } else if (resultDealer == resultPlayer) {
            creatdialog(drow);
        }


    }

    private void checkBelowZero(int tempPlayer, int tempPlayer1, int tempDealer, int tempDealer1) {
        if (tempDealer < 0) {
            tempDealer *= -1;
        }
        if (tempDealer1 < 0) {
            tempDealer1 *= -1;
        }
        if (tempPlayer < 0) {
            tempPlayer *= -1;
        }
        if (tempPlayer1 < 0) {
            tempPlayer1 *= -1;
        }
    }


    public void creatdialog(final String result ){
        dialog=new Dialog(this);
        dialog.setContentView(R.layout.custom_layout);
        dialog.setCancelable(true);
        dialog.setTitle("THE RESULT ARE");
        btnReturn=(Button)dialog.findViewById(R.id.btnReturn);
        txShow=(TextView)dialog.findViewById(R.id.txShow);
        txShow.setText(result);
        dialog.show();
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }


}



