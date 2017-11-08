package com.example.user.myapplication.todolist.todolistmodel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.todolist.todolistmodel.Todo;

import java.util.ArrayList;

/**
 * Created by user on 03/11/17.
 */

public class TodoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Todo> todolist;

    public TodoAdapter(Context _context,ArrayList<Todo> mListe) {

        this.todolist = mListe;
        this.context = _context;
    }



    static class ViewHolder {
        protected CheckBox cb;
        protected TextView tv;
        protected Button btn;
    }


    @Override
    public int getCount() {
        return todolist.size();
    }

    @Override
    public Object getItem(int position) {
        return todolist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




//@Override
public View BadgetView(final int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout = null;
        if (convertView != null) {
            linearLayout = (LinearLayout) convertView;
        }
        else {
            linearLayout = (LinearLayout) LayoutInflater.from(context).
                    inflate(R.layout.layoutitem, parent, false);
        }

        final Todo currentTodo = (Todo) getItem(position);

        TextView textViewItemName = (TextView) linearLayout.findViewById(R.id.textId);
        CheckBox cb = (CheckBox) linearLayout.findViewById(R.id.chkBx);
        Button bn = (Button) linearLayout.findViewById(R.id.btnListing);


        textViewItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, todolist.get(position).getCurrentState().name(), Toast.LENGTH_SHORT).show();
            }
        });


        textViewItemName.setText(currentTodo.getAction());

        cb.setChecked(currentTodo.getEnabled());

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("chek",String.valueOf(isChecked));
                if(isChecked)
                    currentTodo.changeState(Todo.STATE.ACTIVE);
                else{
                    currentTodo.changeState(Todo.STATE.COMPLETED);
                }
            }
        });

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todolist.remove(position);
                todolist.remove(currentTodo);
                notifyDataSetChanged();
                System.out.println("onclik button");
            }
        });
        return linearLayout;
    }
@Override
public View getView(final int position, View convertView, ViewGroup parent) {
    ViewHolder viewHolder = null;

    if(null==convertView){
        convertView = (LinearLayout) LayoutInflater.from(context).
                inflate(R.layout.layoutitem, parent, false);
        viewHolder = new ViewHolder();
        viewHolder.tv = (TextView) convertView.findViewById(R.id.textId);
        viewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, todolist.get(position).getCurrentState().name(), Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.cb = (CheckBox) convertView.findViewById(R.id.chkBx);
        viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();
                todolist.get(getPosition).changeStateB(buttonView.isChecked());
                notifyDataSetChanged();

            }
        });
        viewHolder.btn = (Button) convertView.findViewById(R.id.btnListing);
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int getPosition = (Integer) v.getTag();

                todolist.remove(getPosition);
                notifyDataSetChanged();
                System.out.println("onclik button");
            }
        });

        convertView.setTag(viewHolder);
        convertView.setTag(R.id.textId, viewHolder.tv);
        convertView.setTag(R.id.chkBx, viewHolder.cb);
        convertView.setTag(R.id.btnListing,viewHolder.btn);


    }else {
        viewHolder = (ViewHolder) convertView.getTag();
    }
    viewHolder.cb.setTag(position); // This line is important.

    viewHolder.tv.setText(todolist.get(position).getAction());
    viewHolder.tv.setTextColor(todolist.get(position).getColor());
    viewHolder.cb.setChecked(todolist.get(position).getEnabled());
    viewHolder.btn.setTag(position);
    return convertView;

}
}

