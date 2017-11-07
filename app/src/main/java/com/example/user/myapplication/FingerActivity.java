package com.example.user.supinf;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FingerActivity extends Activity implements View.OnTouchListener {



    RelativeLayout myLayout;
    String txt2show;
    TextView myTextField2show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_finger);
        myLayout = findViewById(R.id.relatLay);
        myTextField2show = findViewById(R.id.txt);

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        myTextField2show.setText(String.format("finger is on X = %d y = %d ",event.getX(),event.getY()));
        return false;
    }
}
