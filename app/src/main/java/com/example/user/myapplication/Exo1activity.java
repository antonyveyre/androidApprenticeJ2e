package com.example.user.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class Exo1activity extends Activity {



    final String dTag = "DEBUG";

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
    }

    @Override
    protected void onStart() {
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message","Saved message");

    }



}
