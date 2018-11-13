package com.example.javedak09.sendemail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
        final EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
        Button btnBrowse = (Button) findViewById(R.id.btnBrowse);

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {

                            GMailSender sender = new GMailSender("email id", "password");
                            String body = "Dear Sir,<br /><br />We are going to start training of NNS 2018 study on 28th July 2018. Please acknowledge.<br /><br /><br /><br /><br />Regards,<br />Javed Ahmed Khan";
                            sender.sendMail("Testing Email", body, "email id", txtEmail.getText().toString());

                        } catch (Exception e) {
                            Log.e("SendMail", e.getMessage(), e);
                        }
                    }

                }).start();

            }
        });

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fpath = Environment.getExternalStorageDirectory().getPath() + "/mypdf";
                File root = new File(fpath);

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                Uri uri = Uri.fromFile(root);
                intent.setDataAndType(uri, "file/*");
                startActivity(intent);

                try {
                    startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), 0);
                } catch (android.content.ActivityNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

    }
}