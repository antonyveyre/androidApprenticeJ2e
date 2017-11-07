package com.example.user.myapplication;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.myapplication.model.Album;

public class Exo1activity extends Activity {


    public static final String MSG_TO_GAME = "name";
    final String dTag = "DEBUG";

    Button btn1;

    EditText input;


    static int compteur = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo1activity);
        if(null!=savedInstanceState)
        {
            int recuperation = savedInstanceState.getInt("key");
            System.out.println(recuperation);
        }
            System.out.println("HelloFromExo1Activity");
        //System.out.println(savedInstanceState.getBundle("message"));

        Button bngame = (Button) findViewById(R.id.btnGmName);
        Button btnFngr = (Button) findViewById(R.id.btnFinger);

        Button bgame = (Button) findViewById(R.id.btnGm);
        Button blist = (Button) findViewById(R.id.btnLst);
        Button bpaint = (Button) findViewById(R.id.btnPnt);

        input = (EditText) findViewById(R.id.editName) ;

        bgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), GameActivity.class);
                startActivity(i);
            }
        });


        bpaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),DrawingActivity.class);
                startActivity(i);
            }
        });


        bngame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), GameActivity.class);
                i.putExtra(MSG_TO_GAME, input.getText().toString()); // key value
                //startActivity(i);
                startActivityForResult(i,99);


            }
        });

        btnFngr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), FingerActivity.class);

                startActivity(i);
            }
        });

        blist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ListActivity.class);
                startActivity(i);

            }
        });

        findViewById(R.id.btnMainAssync).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ThreadsActivity.class);
                startActivity(i);
            }
        });


        findViewById(R.id.btnMainHTTP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),HttprequestActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btnMainPicasso).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),PicassoActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.btnMainAlbum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AlbumActivity.class);
                startActivity(i);
            }
        });
    }


    // requestCode => identifiant , resultCode =>
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("RESULT","RETUR");
        if (resultCode == RESULT_OK)
            Log.d("fin","joueur a fini le jeux");
        else
            Log.d("fin", "joueur appue sur le bouton de retour");
    }

    protected void onStart(){
        super.onStart();
        Log.d("tag","message");
        Log.v("vtag","message");
        Log.println(Log.INFO,"printlnTag","printlnMsg");
        Log.d(dTag,"onStart");


        Toast t = Toast.makeText(this,"onCreate",Toast.LENGTH_LONG);
        t.show();

        Toast.makeText(this,"onCreate",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("OnStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onRestart");

    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        /*super.onRestoreInstanceState(savedInstanceState);
        Log.i("tag OnRestoreInstance",savedInstanceState.getString("message"));
        */


        super.onSaveInstanceState(savedInstanceState);
      //  Log.d(dTag,this.getCallingActivity().toString());
        Log.d(dTag,"onSaveInstanceState");
        savedInstanceState.putInt("key",this.compteur);
        this.compteur++;





    }

  /*  @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message","Saved message");

    }*/



}
