package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.Toast;




// Activity qui montre les divers façons d'ajout de OnClicListener.


public class EventActivity extends AppCompatActivity implements  View.OnClickListener{





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


        findViewById(R.id.MyRelativeLayout).setOnTouchListener(new View.OnTouchListener() {
                                                                   @Override
                                                                   public boolean onTouch(View v, MotionEvent event) {
                                                                       Log.d("debug","le doigt est au x = " +event.getX()+
                                                                               " get raw X ="+
                                                                               + event.getRawX()+
                                                                       "et y = " +event.getY());
                                                                       return false;
                                                                   }
                                                               }
        );

        Button b1 = (Button) findViewById(R.id.btn1);
        Button b2 = (Button) findViewById(R.id.btn2);
        Button b3 = (Button) findViewById(R.id.btn3);

/*
        b1.setOnClickListener(bClick1Listener);
        b2.setOnClickListener(this);
*/
        b1.setOnClickListener(this);
        b2.setOnClickListener(bClick1Listener);
        b3.setOnClickListener(this);





// Methode 3

/*
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),"b3 text"+this.toString(),Toast.LENGTH_SHORT).show();

            }
        });
*/


    }

    public void myClic(View v){

        Toast.makeText(v.getContext(),"Text from myClic "+this.toString(), Toast.LENGTH_SHORT).show();

    }

   View.OnClickListener bClick1Listener = new View.OnClickListener() {

        @Override

        public void onClick(View v) {

            Toast.makeText(v.getContext(), "Click 1 " + this.toString()   , Toast.LENGTH_SHORT).show();

        }

    };

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(),"Text from toast " + this.toString()  , Toast.LENGTH_SHORT).show();
    }
}
