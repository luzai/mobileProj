package org.luzai.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View {

    private static final int initRadius = 120; // 半径的初始值
    private float cy;
    private float cx;
    private int radius = initRadius;
    public float graphScale = 1;
    private float preInterPointDistance;
    private boolean bScreenPress = false;
    private Paint paint;

    public MyView(Context context) {
        super(context);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        cx = canvas.getWidth() / 2;
        cy = canvas.getHeight() / 2;
        canvas.drawCircle(cx, cy, radius * graphScale, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float px1, px2;
        float py1, py2;
        float interPointDistance;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() == 2) {
                    px1 = event.getX(0);
                    py1 = event.getY(0);
                    px2 = event.getX(1);
                    py2 = event.getY(1);

                    interPointDistance = (float) Math.sqrt((px1 - px2) * (px1 - px2) + (py1 - py2) * (py1 - py2));

                    if (!bScreenPress) {
                        bScreenPress = true;
                        preInterPointDistance = interPointDistance;
                    } else {
                        graphScale = interPointDistance / preInterPointDistance;
                        invalidate();
                    }

                } else {
                    bScreenPress = false;
                    radius = (int) (radius * graphScale);
                }
                break;
            case MotionEvent.ACTION_UP:
                bScreenPress = false;
                radius = (int) (radius * graphScale);
                break;
            default:
                break;
        }

        return super.onTouchEvent(event);
    }
}


