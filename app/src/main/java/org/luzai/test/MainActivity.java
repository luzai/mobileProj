package org.luzai.test;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder exitDlg;
    private Dialog exitDlgApp;
    public static final int MYQUESTCODE = 100;
    private int clkNum = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.buttonExit);
        exitDlg = new AlertDialog.Builder(this);
        exitDlg.setTitle("Exit Dialog");
        exitDlg.setMessage("Are You Sure To Exit?");
        exitDlg.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
                System.exit(0);
            }
        });
        exitDlg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        exitDlgApp = exitDlg.create();

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                exitDlgApp.show();
            }
        });


        TextView btnClkNum = (TextView) findViewById(R.id.buttonClkNumber);
        btnClkNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView) findViewById(R.id.textViewClickOrEdit);
                text.setText("This is " + clkNum++ + " click. ");
                Log.d("MyMessage", "This is" + clkNum);
            }
        });
        EditText editText = (EditText) findViewById(R.id.editText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                EditText editText = (EditText) findViewById(R.id.editText);
                TextView text = (TextView) findViewById(R.id.textViewClickOrEdit);
                text.setText(editText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button btnLauch1 = (Button) findViewById(R.id.buttonOther1);
        btnLauch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MultiTouchActivity.class);
                startActivity(intent);
            }
        });

        Button btnLauch2 = (Button) findViewById(R.id.buttonOther2);
        btnLauch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wther = 1;
                Intent intentOtherActivity = new Intent();
                intentOtherActivity.setClassName("org.luzai.test",
                        "org.luzai.test.WeatherActivity");
                intentOtherActivity.putExtra("weather", wther);
                startActivityForResult(intentOtherActivity, MYQUESTCODE);
            }
        });

        Button btnLauchDial = (Button) findViewById(R.id.buttonDial);
        btnLauchDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_DIAL, Uri.parse("Tel:13454943948"));
                try {
                    startActivity(in);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "yourActivity is not founded", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnLauch3 = (Button) findViewById(R.id.buttonOther3);
        btnLauch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_EDIT);
                startActivity(in);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView text = (TextView) findViewById(R.id.textViewClickOrEdit);
        if (requestCode == MYQUESTCODE) {
            int wther;
            wther = data.getIntExtra("weather", -1);
            if (wther == -1)
                text.setText("Unkonwn Weather");
            else if (wther == 0)
                text.setText("Rainy");
            else if (wther == 1)
                text.setText("Sunny");
        }

    }

}
