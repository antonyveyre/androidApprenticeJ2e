package com.example.user.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.os.AsyncTask;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import android.view.View;

import android.widget.Button;

import android.widget.EditText;
import android.widget.ProgressBar;

import android.widget.Scroller;
import android.widget.TextView;







public class ThreadsActivity extends AppCompatActivity {



    private TextView txtStatus;
    private ProgressBar progress;
    private Button btnGo;
    private EditText input;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads);

        txtStatus = (TextView) findViewById(R.id.txtStatus);
        progress = (ProgressBar) findViewById(R.id.progressBarCircle);
        btnGo = (Button) findViewById(R.id.btnGo);

        input = (EditText) findViewById(R.id.txtFib);


        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                new SumAsyncTask().execute(Integer.parseInt(input.getText().toString()));
            }
        });
    }


    public static int fibonacci(int n) {
        if (n <= 1)
            return n;
        else return fibonacci(n-1) + fibonacci(n-2);
    }


    public class SumAsyncTask extends AsyncTask<Integer, Float, String> {
        private static final String TAG = "Test";



        @Override
        protected String doInBackground(Integer... myInt) {
            String result;
            result = "\n0\n";

            for (Integer i = 1; i <= myInt[0]; i++){
                result += String.valueOf(fibonacci(i)+"\n");
                publishProgress((float)i/Float.parseFloat(myInt[0].toString()));
                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
            return result;
        }

        @Override
        protected void onPreExecute() { // UI Thread
            super.onPreExecute();
            txtStatus.setText("Loading");
        }


/*

        @Override

        protected String doInBackground(String... params) { // Exécuté dans un autre thread

            // params [1, 2, 3, 4]

            String result = "";
            for( int i = 0; i < params.length;  i++ ){
                result += params[i];
                publishProgress((float)i/params.length);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            publishProgress((float)1.0);
            return result;
        }
*/


        @Override
        protected void onProgressUpdate(Float... values) { // Exécuté dans le UIThread
            super.onProgressUpdate(values);
            Log.i(TAG, "Progress " + values[0]);
            progress.setProgress(Math.round(values[0] * 100));
        }



        @Override
        protected void onPostExecute(String res) { // Exécuté dans le UIThread
            Log.i(TAG, "Result " + res);
            progress.setVisibility(View.INVISIBLE);
            txtStatus.setText("Finish, results: " + res);
            txtStatus.setMovementMethod(new ScrollingMovementMethod());
        }
    }
}