package com.example.user.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.user.myapplication.model.Album;
import com.example.user.myapplication.model.AlbumAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class AlbumActivity extends AppCompatActivity {

    private AlbumAdapter adapter;
    ArrayList<Album> myAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        myAlbum = new ArrayList<>();


/*        Album a1 = new Album( "lkj1","lkj1");
        Album a2 = new Album( "lkj2","lkj2");
        Album a3 = new Album( "lkj3","lkj3");
        Album a4 = new Album( "lkj4","lkj4");
        Album a5 = new Album( "lkj5","lkj5");


        myAlbum.add(a1);
        myAlbum.add(a2);
        myAlbum.add(a3);
        myAlbum.add(a4);
        myAlbum.add(a5);*/

        adapter = new AlbumAdapter(this,myAlbum);
        ListView lv = (ListView) findViewById(R.id.albumLv);
        lv.setAdapter(adapter);
        AlbumTask at = (AlbumTask) new AlbumTask(adapter,this);
        at.execute();
        lv.setAdapter(at.albumAdapter);
        at.albumAdapter.notifyDataSetChanged();

    }

    public class AlbumTask extends AsyncTask<Void, Void, String> {

        private static final String TAG = "AlbumTask";
        private Context context;
        private AlbumAdapter albumAdapter;
        public AlbumTask(AlbumAdapter albumAdapter, Context context) {
            this.context = context;
            this.albumAdapter = albumAdapter;
        }

        @Override
        protected String doInBackground(Void... strings) {
            StringBuilder result = null;
            String path = "https://jsonplaceholder.typicode.com/photos";
            URL url;
            HttpURLConnection connection= null;
            try {
                url = new URL(path);
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestMethod("GET");
                connection.connect();
                int codeResponse = connection.getResponseCode();
                Log.d("Code Responce", String.valueOf(codeResponse));
                result = new StringBuilder();
                if( 200 <= codeResponse && codeResponse < 300 ){
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line = null;
                    while((line = br.readLine()) != null){
                        result.append(line);
                    }
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
        return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            if(result == null || result.isEmpty()) {
                Log.d("debug", "Result is empty...");
                return;
            }
            try{
//Create the type “ArrayList<Album>” with a anonyme class
                Type listType = new TypeToken<ArrayList<Album>>() {}.getType();
//Convert result to objects
                List<Album> list = new Gson().fromJson(result, listType);
//Updates the listView
//
                Log.d(TAG,list.toString());
                Log.d(TAG,result);
               // albumAdapter.addAll(list);
                myAlbum.addAll(list);
                albumAdapter.notifyDataSetChanged();
                //albumAdapter.notifyDataSetChanged();
            }catch(Exception e){
                System.out.println("e " + e);
            }
        }


    }
}
