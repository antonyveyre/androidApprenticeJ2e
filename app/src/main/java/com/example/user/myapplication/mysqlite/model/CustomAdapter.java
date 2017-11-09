package com.example.user.myapplication.mysqlite.model;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.user.myapplication.R;

/**
 * Created by user on 08/11/17.
 */

public class CustomAdapter extends CursorAdapter {

    private LayoutInflater mInflater;

    public CustomAdapter(Context context, Cursor cursor ) {
        super(context, cursor, 0);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (cursor.getPosition() % 2 == 1) {
            view.setBackgroundColor(0xFFBBE5FF);
        } else {
// view.setBackgroundColor(context.getResources().getColor(R.color.background_even));
        }
        TextView titleTextView = (TextView) view.findViewById(R.id.textViewTitle);
        String title = cursor.getString(cursor.getColumnIndex(MovieDAO.COLUMN_NAME_TITLE));
        titleTextView.setText(title.toUpperCase());
        TextView idText = (TextView) view.findViewById(R.id.textViewId);
        idText.setText(cursor.getString(cursor.getColumnIndex(MovieDAO.COLUMN_NAME_ID)));

    }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return mInflater.inflate(R.layout.list_row_movie, parent, false);
        }
}
