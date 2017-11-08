package com.example.user.myapplication.albumpackage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.user.myapplication.R;
import com.example.user.myapplication.albumpackage.albummodel.Album;
import com.example.user.myapplication.albumpackage.albummodel.AlbumAdapter;
import java.util.ArrayList;


public class AlbumActivity extends AppCompatActivity {

    private AlbumAdapter adapter;
    ArrayList<Album> myAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        myAlbum = new ArrayList<>();


/*      Album a1 = new Album( "lkj1","lkj1");
        Album a2 = new Album( "lkj2","lkj2");
        Album a3 = new Album( "lkj3","lkj3");
        Album a4 = new Album( "lkj4","lkj4");
        Album a5 = new Album( "lkj5","lkj5");


        myAlbum.add(a1);
        myAlbum.add(a2);
        myAlbum.add(a3);
        myAlbum.add(a4);
        myAlbum.add(a5);

        */

        adapter = new AlbumAdapter(this,myAlbum);
        ListView lv = (ListView) findViewById(R.id.albumLv);
        lv.setAdapter(adapter);
        AlbumTask at = new AlbumTask(adapter,this);
        at.execute();
        new AlbumTask(new AssyncResponse() {
            @Override
            public void assyncTaskFinished(String s) {
                System.out.println("string is "+s);
            }
        });

        lv.setAdapter(at.getAlbumAdapter());
        at.getAlbumAdapter().notifyDataSetChanged();

    }

}
