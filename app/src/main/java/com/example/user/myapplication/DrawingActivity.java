package com.example.user.myapplication;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;




public class DrawingActivity extends Activity {

Button btn1;


    private Layout mainLayout;

    DrawingView dv ;

    private Paint mPaint;



    @SuppressLint("ResourceType")
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        dv = new DrawingView(this);

        mPaint = new Paint();

        mPaint.setAntiAlias(true);

        mPaint.setDither(true);

        mPaint.setColor(Color.GREEN);

        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setStrokeJoin(Paint.Join.ROUND);

        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mPaint.setStrokeWidth(12);


//        btn1 = (Button) findViewById(R.id.paintBtn1);


        // Question 3 il faudra modifier cette ligne

       setContentView(R.layout.activity_drawing);
       RelativeLayout rl = findViewById(R.id.DrawingL);
       rl.addView(dv);



        findViewById(R.id.paintBtn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("erase", "click on erase");
                mPaint.setColor(Color.GREEN);
                dv.mCanvas.drawColor(Color.WHITE);


            }
        });

        findViewById(R.id.paintBtnRd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPaint.setColor(Color.RED);
            }
        });


        findViewById(R.id.paintBtnBlu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPaint.setColor(Color.BLUE);
            }
        });

    }



    // Class qui hérite de VIEW !

    public class DrawingView extends View {



        private Bitmap mBitmap;

        private Canvas mCanvas;

        private Path mPath;

        private Paint   mBitmapPaint;

        Context context;



        public DrawingView(Context c) {

            super(c);

            context=c;

            mPath = new Path();

            mBitmapPaint = new Paint(Paint.DITHER_FLAG);



        }



        @Override

        protected void onSizeChanged(int w, int h, int oldw, int oldh) {

            super.onSizeChanged(w, h, oldw, oldh);



            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

            mCanvas = new Canvas(mBitmap);

        }



        @Override

        protected void onDraw(Canvas canvas) {

            super.onDraw(canvas);

            canvas.drawBitmap( mBitmap, 0, 0, mBitmapPaint);

            canvas.drawPath( mPath,  mPaint);

        }



        private float mX, mY;

        private static final float TOUCH_TOLERANCE = 4;



        private void touch_start(float x, float y) {

            mPath.reset();

            mPath.moveTo(x, y);

            mX = x;

            mY = y;

        }



        private void touch_move(float x, float y) {

            float dx = Math.abs(x - mX);

            float dy = Math.abs(y - mY);

            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {

                mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);

                mX = x;

                mY = y;



            }

        }



        private void touch_up() {

            mPath.lineTo(mX, mY);



            // commit the path to our offscreen

            mCanvas.drawPath(mPath,  mPaint);

            // kill this so we don't double draw

            mPath.reset();

        }



        @Override

        public boolean onTouchEvent(MotionEvent event) {

            float x = event.getX();

            float y = event.getY();



            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    touch_start(x, y);

                    invalidate();

                    break;

                case MotionEvent.ACTION_MOVE:

                    touch_move(x, y);

                    invalidate();

                    break;

                case MotionEvent.ACTION_UP:

                    touch_up();

                    invalidate();

                    break;

            }

            return true;

        }

    }

}
