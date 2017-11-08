package com.example.user.myapplication.mysqlite.model;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 07/11/17.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {


    // nom du fichier contenant la base de données
    private static final String DB_NAME = "myFirstDB.db";
    private static final int DB_VERSION = 1;

    public MySQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION );
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
