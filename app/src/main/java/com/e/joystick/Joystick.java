package com.e.joystick;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import static android.content.ContentValues.TAG;

public class Joystick extends FrameLayout implements View.OnTouchListener {

    private Context context;


    private int Square_startX;  //The horizontal center of a square
    private int Square_startY;  //The vertical center of a square
    private int Swidth;          //The width of square
    private int Sheight;         //The Sheight of square


    private Paint innerPaint;   //A brush to draw small circles
    private int Circle_centerX; //The Horizontal of circle button
    private int Circle_centerY; //The Vertical of circle button
    private int CircleR=50;        //The radius of circle button


    private boolean isContact;


    public void setCircleR(int R){
        this.CircleR=R;
    }
    private Handler handler = new Handler( Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isContact = false;//用于判断是否在短时间内点击了小圆两次
        }
    };


    public Joystick(Context context, AttributeSet attr) {
        super( context,attr);
        this.context=context;
        setBackgroundColor(Color.GRAY );        //设置背景为灰
        this.setOnTouchListener(this);          //设置触摸监听事件




        innerPaint=new Paint(  );               //初始化paint
        innerPaint.setColor( Color.argb(255, 255, 255, 255));//设置颜色
        innerPaint.setAntiAlias(true);//设置抗锯齿
        innerPaint.setStyle( Paint.Style.FILL);//设置绘制风格，填充模式


        invalidate();

        setWillNotDraw(false);//如果onDraw不执行，运行该还代码或者重写dispatchDraw替换onDraw
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("tag", "onDraw: ");
        canvas.drawCircle(Circle_centerX, Circle_centerY, CircleR, innerPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        Swidth=getMeasuredWidth();
        Sheight=getMeasuredHeight();

        Square_startX=0;
        Square_startY=0;

        Circle_centerX=Square_startX+Swidth/2;
        Circle_centerY=Square_startY+Sheight/2;

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
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
                touch_up(x,y);
                invalidate();
                break;
        }
        return true;
    }




    private void touch_start(float x, float y) {
        Circle_centerX=(int)x;
        Circle_centerY=(int)y;

    }
    private void touch_move(float x, float y) {
        int tx=(int)x;
        int ty=(int)y;

        Log.d( TAG, "touch_move1: "+tx+" "+ty );
        Log.d( TAG, "touch_move2: "+Square_startX+" "+Square_startY );
        Log.d( TAG, "touch_move3: "+Swidth+"  "+Sheight );

        if(tx<Square_startX+CircleR){
            if(ty<Square_startY+CircleR){
                Circle_centerX=Square_startX+CircleR;
                Circle_centerY=Square_startY+CircleR;
            }else if(ty>Square_startY+Sheight-CircleR){
                Circle_centerX=Square_startX+CircleR;
                Circle_centerY=Square_startY+Sheight-CircleR;
            }else{
                Circle_centerX=Square_startX+CircleR;
                Circle_centerY=ty;
            }
        }else if(tx>Square_startX+Swidth-CircleR){
            if(ty<Square_startY+CircleR){
                Circle_centerX=Square_startX+Swidth-CircleR;
                Circle_centerY=Square_startY+CircleR;
            }else if(ty>Square_startY+Sheight-CircleR){
                Circle_centerX=Square_startX+Swidth-CircleR;
                Circle_centerY=Square_startY+Sheight-CircleR;
            }else{
                Circle_centerX=Square_startX+Swidth-CircleR;
                Circle_centerY=ty;
            }
        }else if(ty<Square_startY+CircleR){
            Circle_centerX=tx;
            Circle_centerY=Square_startY+CircleR;
        }else if(ty>Square_startY+Sheight-CircleR){
            Circle_centerX=tx;
            Circle_centerY=Square_startY+Sheight-CircleR;
        }else{
            Circle_centerX=tx;
            Circle_centerY=ty;
        }

    }
    private void touch_up(float x,float y) {
        int tx=(int)x;
        int ty=(int)y;
        Log.d( TAG, "touch_move: "+tx+" "+ty );

        if(tx<Square_startX+CircleR){
            if(ty<Square_startY+CircleR){
                Circle_centerX=Square_startX+CircleR;
                Circle_centerY=Square_startY+CircleR;
            }else if(ty>Square_startY+Sheight-CircleR){
                Circle_centerX=Square_startX+CircleR;
                Circle_centerY=Square_startY+Sheight-CircleR;
            }else{
                Circle_centerX=Square_startX+CircleR;
                Circle_centerY=ty;
            }
        }else if(tx>Square_startX+Swidth-CircleR){
            if(ty<Square_startY+CircleR){
                Circle_centerX=Square_startX+Swidth-CircleR;
                Circle_centerY=Square_startY+CircleR;
            }else if(ty>Square_startY+Sheight-CircleR){
                Circle_centerX=Square_startX+Swidth-CircleR;
                Circle_centerY=Square_startY+Sheight-CircleR;
            }else{
                Circle_centerX=Square_startX+Swidth-CircleR;
                Circle_centerY=ty;
            }
        }else if(ty<Square_startY+CircleR){
            Circle_centerX=tx;
            Circle_centerY=Square_startY+CircleR;
        }else if(ty>Square_startY+Sheight-CircleR){
            Circle_centerX=tx;
            Circle_centerY=Square_startY+Sheight-CircleR;
        }else{
            Circle_centerX=tx;
            Circle_centerY=ty;
        }
    }





}
