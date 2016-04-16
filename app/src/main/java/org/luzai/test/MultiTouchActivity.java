package org.luzai.test;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by luzai on 4/12/16.
 */

public class MultiTouchActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new SingleTouchEventView(this,null));
        //setContentView(R.layout.activity_main);
        setContentView(new ScaleDetectorView(this));

    }
}
