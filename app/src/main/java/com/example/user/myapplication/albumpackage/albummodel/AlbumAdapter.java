package com.example.user.myapplication.albumpackage.albummodel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.albumpackage.albummodel.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 06/11/17.
 */

public class AlbumAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Album> myAlbum;
    private final String TAG = "Album Adapter";

    public AlbumAdapter(Context context, ArrayList<Album> _myAlbum) {
        this.context = context;
        this.myAlbum = _myAlbum;
    }


    public void addAll(List<Album> list){
        myAlbum.addAll(list);
        Log.d(TAG, "list added");

        notifyDataSetChanged();
        Log.d(TAG,"data changed");

        Log.d(TAG, String.valueOf(this.getCount()));
    }

    @Override
    public int getCount() {
        return myAlbum.size();
    }

    @Override
    public Object getItem(int position) {
        return myAlbum.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout = null;
        if (convertView != null) {
            linearLayout = (LinearLayout) convertView;
        }
        else {
            linearLayout = (LinearLayout) LayoutInflater.from(context).
                    inflate(R.layout.layaoutalbum, parent, false);
        }
        Album currentAlbum = (Album) getItem(position);
// On récup les deux textView de notre template (user_row.xml)
        TextView textViewAlbumUrl = (TextView)
                linearLayout.findViewById(R.id.albumTv1);
        TextView textViewAlbumTitle = (TextView)
                linearLayout.findViewById(R.id.albumTv2);
        ImageView imageView = (ImageView) linearLayout.findViewById(R.id.albumIv);
        Picasso.with(context).load(currentAlbum.getThumbnailUrl()).into(imageView);

        textViewAlbumUrl.setText(currentAlbum.getUrlAlbum());
        textViewAlbumTitle.setText(currentAlbum.getTitle());
// retourne la ligne
        return linearLayout;    }
}
