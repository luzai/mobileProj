package org.luzai.test;

/**
 * Created by luzai on 4/15/16.
 */
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

public class ScaleDetectorView extends View {
    private Drawable image;
    private float scaleFactor = 1.0f;
    private ScaleGestureDetector scaleGestureDetector;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ScaleDetectorView(Context context) {
        super(context);
        image = context.getResources().getDrawable(R.drawable.wolf);
        setFocusable(true);
        image.setBounds(0,0, image.getIntrinsicWidth(),
                image.getIntrinsicHeight());
        scaleGestureDetector = new ScaleGestureDetector(context,
                new ScaleListener());

        }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Set the image boundries
        canvas.save();
       // canvas.clipRect(canvas.getWidth()/4,canvas.getHeight()/4,canvas.getWidth()/4,canvas.getHeight()/4);
        canvas.scale(scaleFactor, scaleFactor);

        image.draw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        scaleGestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }

    private class ScaleListener extends
            ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();

            // don't let the object get too small or too large.
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));

            invalidate();
            return true;
        }
    }
}
