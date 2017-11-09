package com.example.user.myapplication.mysqlite;

import android.app.Activity;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;

import com.example.user.myapplication.R;
import com.example.user.myapplication.mysqlite.model.CustomAdapter;
import com.example.user.myapplication.mysqlite.model.Movie;
import com.example.user.myapplication.mysqlite.model.MovieDAO;
import com.example.user.myapplication.mysqlite.model.MyCursorLoader;
import com.example.user.myapplication.mysqlite.model.MySQLiteHelper;

import java.util.List;





public class SqliteActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {

    SQLiteDatabase db;
    SimpleCursorAdapter adapter;
    CustomAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        MySQLiteHelper sqLiteHelper = new MySQLiteHelper(this);
        db = sqLiteHelper.getWritableDatabase();

       // MovieDAO.drop(db);
        MovieDAO.create(db);

        MovieDAO.insert(db, new Movie("Pulp Fiction"));
        db.close();
        db = sqLiteHelper.getReadableDatabase();
        List<Movie> movies = MovieDAO.getAllMovies(db);
        for (Movie m : movies)
            Log.i("MOVIE", m.toString());


        adapter2 = new CustomAdapter(this,MovieDAO.getAll(db));
        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_expandable_list_item_2,
                null, //cursor chargé par le loaded
// colonnes
                new String[]{MovieDAO.COLUMN_NAME_ID, MovieDAO.COLUMN_NAME_TITLE},
// identifiants des TextView
                new int[]{android.R.id.text1, android.R.id.text2},
// flag
                0);

        getLoaderManager().initLoader(0, null, this);
        GridView gridView = (GridView) findViewById(R.id.gridViewSql);
        gridView.setAdapter(adapter2);
    }

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new MyCursorLoader(getApplicationContext(), db);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            adapter.changeCursor(cursor);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            adapter.changeCursor(null);
        }

}
