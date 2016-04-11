package org.luzai.test;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder exitDlg;
    private Dialog exitDlgApp;


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
    }
}
