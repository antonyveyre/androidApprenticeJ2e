package com.example.user.myapplication.mysqlite.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 07/11/17.
 */

public class MovieDAO {

    public static final String TABLE_NAME = "Movies";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_ID = "_id";

    // les méthodes statiques ici ont pour but d'effectuer
// des requetes SQL pour gérer les données, les tables

    public static void create(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
//                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                COLUMN_NAME_TITLE +" TEXT NOT NULL )");
        db.execSQL(new StringBuilder("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_TITLE + " TEXT NOT NULL )").toString());

    }

    public static void drop(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public static void insert(SQLiteDatabase db, Movie m)
    {

        System.out.println("insert" + m);
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, m.getMovieTitle());
        values.putNull("_id");
        db.insert(TABLE_NAME, null, values);
    }
    public static void deleteFromId(SQLiteDatabase db, long id)
    {
        db.delete(TABLE_NAME, "_id = ?", new String[] { String.valueOf(id) });
    }
    public static void deleteFromTitle(SQLiteDatabase db, String title)
    {
        db.delete(TABLE_NAME, COLUMN_NAME_TITLE + " = ?", new String[] { title });
    }

    public static List<Movie> getAllMovies(SQLiteDatabase db){
        List<Movie> movies = new LinkedList<Movie>();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        Movie movie = null;
        if (cursor.moveToFirst()) {
        //check si c'est pas null et se positionne sur la 1er ligne
            do {
                movie = new Movie();
                movie.setId(Integer.parseInt(cursor.getString(0)));
                movie.setMovieTitle(cursor.getString(1));
                movies.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return movies;
    }

    public static Cursor getAll(SQLiteDatabase db) {
        return db.rawQuery("SELECT  * FROM " + TABLE_NAME, null);
    }
}
