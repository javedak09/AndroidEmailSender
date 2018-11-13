package com.example.javedak09.sendemail;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSendEmail = (Button) findViewById(R.id.btnSendEmail);
        final EditText txtEmail = (EditText) findViewById(R.id.txtEmail);


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
    }
}