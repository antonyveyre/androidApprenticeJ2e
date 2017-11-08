package com.example.user.myapplication.mysqlite.model;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by user on 07/11/17.
 */
public class MyCursorLoader extends CursorLoader {
    private SQLiteDatabase db;
    public MyCursorLoader(Context context, SQLiteDatabase db) {
        super(context);
        this.db = db;
    }
    @Override
    protected Cursor onLoadInBackground() {
//async
        return MovieDAO.getAll(db);
    }
}