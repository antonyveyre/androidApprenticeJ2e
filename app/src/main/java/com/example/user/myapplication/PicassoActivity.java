package com.example.user.myapplication;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PicassoActivity extends Activity {


    private EditText editText;
    private ImageView imageView;
    private Button button;
    private HttpURLConnection huc;
    private String url;
    private String result;
    private Context c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        editText = (EditText) findViewById(R.id.picassoEt);
        button = (Button) findViewById(R.id.picassoBtnGet);
        imageView = (ImageView) findViewById(R.id.picassoIv);
        final Context context = this;


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                imageView.setImageResource(0);
                imageView.setImageBitmap(null);
                imageView.destroyDrawingCache();*/

                url = editText.getText().toString();

                Picasso.with(context).invalidate(String.valueOf(url));
               // Picasso.with(context).load(String.valueOf(url)).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE);
                Picasso.with(context).load(url).into(imageView);

            }
        });


    }


}










