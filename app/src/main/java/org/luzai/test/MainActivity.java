package org.luzai.test;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder exitDlg;
    private Dialog exitDlgApp;

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
                text.setText("This is " + clkNum++ + "click. ");
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

    }
}
