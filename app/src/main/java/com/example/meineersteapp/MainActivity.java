package com.example.meineersteapp;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
        answer.setText("test");
                try {

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    Socket socket = new Socket("se2-submission.aau.at", 20080);
                    DataOutputStream matno = new DataOutputStream(socket.getOutputStream());
                    //BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String testmatno = "01561075";


                    matno.writeBytes(testmatno+'\n');
                    System.out.println("Test3");
                    System.out.println(in.readLine());
                    socket.close();



                } catch (IOException e) {
                    System.out.println("Exception thrown");
                    throw new RuntimeException(e);
                }






    }
}