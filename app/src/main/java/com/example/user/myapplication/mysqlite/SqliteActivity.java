package com.example.user.myapplication.mysqlite;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;

import com.example.user.myapplication.R;
import com.example.user.myapplication.mysqlite.model.Movie;
import com.example.user.myapplication.mysqlite.model.MovieDAO;
import com.example.user.myapplication.mysqlite.model.MyCursorLoader;
import com.example.user.myapplication.mysqlite.model.MySQLiteHelper;

import java.util.List;



public class SqliteActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MySQLiteHelper sqLiteHelper = new MySQLiteHelper(this);
        db = sqLiteHelper.getWritableDatabase();
        MovieDAO.insert(db, new Movie("Pulp Fiction"));
        db.close();
        db = sqLiteHelper.getReadableDatabase();
        List<Movie> movies = MovieDAO.getAllMovies(db);
        for(Movie m : movies)
            Log.i("MOVIE", m.toString());

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                null, //cursor chargé par le loaded
// colonnes
                new String[]{"_id", "title"},
// identifiants des TextView
                new int[]{android.R.id.text1, android.R.id.text2},
// flag
                0);
        GridView gridView = (GridView) findViewById(R.id.gridViewSql);
        gridView.setAdapter(adapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(getApplicationContext(), db);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
