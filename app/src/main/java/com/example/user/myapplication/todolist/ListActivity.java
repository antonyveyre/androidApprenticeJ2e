package com.example.user.myapplication.todolist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.todolist.todolistmodel.Todo;
import com.example.user.myapplication.todolist.todolistmodel.TodoAdapter;

import java.util.ArrayList;

public class ListActivity extends Activity {



    Button btnAdd;
    EditText et;
    TodoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // must have often
        super.onCreate(savedInstanceState);

        // définition de layout pour Activity

        setContentView(R.layout.activity_list);

        final ArrayList<Todo> mListe = new ArrayList<>();

/*
//        Todo t1 = new Todo("faire les courses");
//        Todo t2 = new Todo("action 1");
//        Todo t3 = new Todo("action 2");
//        Todo t4 = new Todo("action 3");
//        Todo t5 = new Todo("action 4");
//
//        mListe.add(t1);
//        mListe.add(t2);
//        mListe.add(t3);
//        mListe.add(t4);
//        mListe.add(t5);

       t4.changeStateB(false);*/

        //ArrayAdapter<Todo>
        adapter = new TodoAdapter(this, mListe);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
        et = findViewById(R.id.addAction);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListe.add(new Todo(et.getText().toString()));
                et.setText("");
                adapter.notifyDataSetChanged();
            }
        });
    }
}