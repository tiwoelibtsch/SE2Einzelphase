package com.example.meineersteapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    public void onClick (View view) {
        System.out.println("Test");
        TextView answer = findViewById(R.id.answer);
        TextView matnoinput = findViewById(R.id.matno);

                try {

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    Socket socket = new Socket("se2-submission.aau.at", 20080);
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    //String testmatno = "01561075";
                    CharSequence test = matnoinput.getText();
                    System.out.println(test.toString());

                    output.writeBytes(test.toString()+'\n');
                    String result = in.readLine();
                    answer.setText(result);
                    socket.close();
                    Button ascii = findViewById(R.id.ASCII);
                    ascii.setVisibility(View.VISIBLE);




                } catch (IOException e) {
                    System.out.println("Exception thrown");
                    throw new RuntimeException(e);
                }







    }


    public void ClickCalculateascii(View view){


    }
}