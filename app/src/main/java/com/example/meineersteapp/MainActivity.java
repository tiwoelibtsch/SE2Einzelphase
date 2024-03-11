package com.example.meineersteapp;

import android.content.Intent;
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
    String matnoasci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }
    public void onClick (View view) {
        System.out.println("Test");
        TextView answer = findViewById(R.id.answer);
        TextView matnoinput = findViewById(R.id.matno);
        Button ascii = findViewById(R.id.ASCII);
        ascii.setVisibility(View.INVISIBLE);
                try {

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    Socket socket = new Socket("se2-submission.aau.at", 20080);
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    //String testmatno = "01561075";
                    CharSequence test = matnoinput.getText();

                    output.writeBytes(test.toString()+'\n');
                    String result = in.readLine();
                    answer.setText(result);
                    socket.close();
                    if(!result.equals("Dies ist keine gueltige Matrikelnummer")){

                        ascii.setVisibility(View.VISIBLE);
                        matnoasci = convertascii(test.toString());

                    }


                } catch (IOException e) {
                    System.out.println("Exception thrown");
                    throw new RuntimeException(e);
                }







    }

    public String convertascii(String matno) {
        System.out.println("Test1"+matno);


        char[] matnoarray = matno.toCharArray();

        for (int i = 1; i<matnoarray.length; i=i+2){
            switch (matnoarray[i]){
                case '0':
                    matnoarray[i]='a';
                    break;
                case '1':
                    matnoarray[i]='b';
                    break;
                case '2':
                    matnoarray[i]='c';
                    break;
                case '3':
                    matnoarray[i]='d';
                    break;
                case '4':
                    matnoarray[i]='e';
                    break;
                case '5':
                    matnoarray[i]='f';
                    break;
                case '6':
                    matnoarray[i]='g';
                    break;
                case '7':
                    matnoarray[i]='h';
                    break;
                case '8':
                    matnoarray[i]='i';
                    break;
                case '9':
                    matnoarray[i]='j';
                    break;
            }
        }

        return new String(matnoarray);



    }
    public void ClickCalculateascii(View view){
        System.out.println("clickcalculate" + matnoasci);

        Intent myIntent = new Intent(MainActivity.this, ShowASCII.class);

        myIntent.putExtra("key", matnoasci);

        MainActivity.this.startActivity(myIntent);


    }
}