package org.luzai.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by luzai on 4/10/16.
 */
public class WeatherActivity extends Activity {
    int[] rainSun;

    private int checkAnswer(int ans, int rainSun) {
        int messageResId;
        if (ans == rainSun)
            messageResId = R.string.correct_toast;
        else
            messageResId = R.string.incorrect_toast;
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
        if (ans != rainSun) {
            updateQues(1 - rainSun);
            rainSun = 1 - rainSun;
        }
        return rainSun;
    }

    private void updateQues(int quesIndex) {
        //rain -- 0
        ImageView imgRainSun = (ImageView) findViewById(R.id.imageViewWeather);
        if (quesIndex == 0)
            imgRainSun.setImageResource(R.drawable.weather_rain);
        else
            imgRainSun.setImageResource(R.drawable.weather_sun);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        Intent intent=getIntent();
        int rainSunTmp = intent.getIntExtra("weather",1);
        rainSun = new int[1];
        rainSun[0]=rainSunTmp;
        updateQues(rainSun[0]);
        TextView textViewRain = (TextView) findViewById(R.id.textViewRainy);
        textViewRain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rainSun[0] = checkAnswer(0, rainSun[0]);
            }
        });

        TextView textViewSun = (TextView) findViewById(R.id.textViewSunny);
        textViewSun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rainSun[0] = checkAnswer(1, rainSun[0]);
            }
        });

        Button btnExit = (Button) findViewById(R.id.buttonExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.putExtra("weather",rainSun[0]);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Log.i("TAG", "onBackPressed");
        Intent intent= new Intent();
        intent.putExtra("weather",rainSun[0]);
        setResult(RESULT_OK,intent);
        super.onBackPressed();
    }

}