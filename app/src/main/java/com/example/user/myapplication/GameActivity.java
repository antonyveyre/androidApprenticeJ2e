package com.example.user.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.model.Game;

public class GameActivity extends Activity implements View.OnClickListener {

/*
    int toFind;
    int counter;
    */

    Game game;
    int comparable;
    Button b;
    TextView resultat;
    EditText input;
    TextView hist;
    ProgressBar prog;
    TextView bonjour;
    String s ="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);




        /*int i = 10000;
        while (toFind+1!=0) {
            i = (int) ((Math.random() * 100) + 1);
            Log.i("start", "Starting " + i );
         */
//        findViewById(R.layout.activity_game)


        b = (Button) findViewById(R.id.btn);
        resultat = (TextView) findViewById(R.id.resultat);
        input = (EditText) findViewById(R.id.input);
        hist = (TextView) findViewById(R.id.history);
        prog = (ProgressBar) findViewById(R.id.progressBar);
        b.setOnClickListener(this);
        s = getIntent().getStringExtra(Exo1activity.MSG_TO_GAME);
        Log.d("intent",s);
        s = "Bonjour " + s;
        bonjour = (TextView) findViewById(R.id.bnjr);
        this.reset();



    }


    public void reset() {
        game = new Game();
        game.initGame(100);


/*
        counter = 0;
        toFind = (int) (Math.random() * 100) + 1;
*/
        comparable = 0;

        resultat.setText("");
        hist.setText("");
        prog.setProgress(game.getCounter());

        bonjour.setText(s);

    }


    @Override
    public void onClick(View v) {

        prog.setProgress(game.getCounter());

        //  hist.append((String.format("%d%n",toFind) ));

//        hist.setText(input.getText().toString());
        Log.i("i", "onclick ");

        try {
            comparable = Integer.parseInt(input.getText().toString());
        } catch (Exception e) {
            Toast.makeText(v.getContext(), "le numero saisie n'est pas bon !!!", Toast.LENGTH_LONG).show();
            //comparable = 0;

            return;
        }

        if (comparable > 100) {
            resultat.setText("TROP grand!");
        } else if (comparable > game.getToFind()) {
            resultat.setText("Trop grand!");
            hist.append((String.format("%d%n", comparable)));

        } else if (comparable < game.getToFind()) {
            resultat.setText("Trop petit!");
            hist.append((String.format("%d%n", comparable)));

        } else if (comparable == game.getToFind()) {

            resultat.setText("Bingo !!!");
//            b.setOnClickListener(null);
            this.MyAlert();
        }

        game.upCounter();
        input.setText("");

        if (game.getCounter() > 8) {
            new AlertDialog.Builder(this).setMessage("Vous avez perdu!!!").create().show();

            this.MyAlert();
        }


    }


    public void MyAlert(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Encore", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reset();
            }
        });
        builder.setNegativeButton("Fin", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                stopTheGame();
            }
        });
        AlertDialog gain = builder.create();
        gain.show();
    }

    private void stopTheGame(){
        Intent result = new Intent();
        result.putExtra("res","end game");
        setResult(RESULT_OK,result);
        finish();
    }

}

