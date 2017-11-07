package com.example.user.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static android.widget.Toast.LENGTH_SHORT;

public class HttprequestActivity extends AppCompatActivity {

    private EditText et;
    private TextView tv;
    private Button btnSnd;
    private HttpURLConnection huc;
    private URL url;
    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httprequest);

        btnSnd = (Button) findViewById(R.id.HTTPREQUESTbtnSendGet);
        et = (EditText) findViewById(R.id.HTTPREQUESTtxtGet);
        tv = (TextView) findViewById(R.id.HTTPREQUESTtxtResult);
       btnSnd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               result = "...waiting for connection";

               new SumAsyncTask().execute(et.getText().toString());


           }
       });
    }




    public class SumAsyncTask extends AsyncTask<String, Float, String> {

        @Override
        protected String doInBackground(String... strings) {
            int codeResponse = 0;
            HttpURLConnection connection = null;
            try {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestMethod("GET");
                connection.connect();
                codeResponse = connection.getResponseCode();
            }catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (ProtocolException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }

            StringBuilder result = new StringBuilder();
            if (200 <= codeResponse && codeResponse < 300) {
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new
                            InputStreamReader(connection.getInputStream()));
                    String line = null;

                    while ((line = br.readLine()) != null) {
                        result.append(line);
                    }
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();
            return result.toString();
        }




          /*  try {
                url = new URL(strings[0].toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(HttprequestActivity.super.getBaseContext(),"url is uncorect", LENGTH_SHORT).show();
            }

            try {
                huc = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(HttprequestActivity.super.getBaseContext(),"url is uncorect", LENGTH_SHORT).show();

            }
            try {
                result = String.valueOf(huc.getResponseCode());
            } catch (IOException e) {
                e.printStackTrace();
            }*/


        @Override
        protected void onPreExecute() {
            tv.setText(result);
            tv.setMovementMethod(new ScrollingMovementMethod());

        }
        @Override
        protected void onPostExecute(String s) {
            tv.setText(s);
        }
    }

}
