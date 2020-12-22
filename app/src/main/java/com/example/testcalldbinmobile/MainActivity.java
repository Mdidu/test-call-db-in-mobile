package com.example.testcalldbinmobile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static TextView txt = null;
    private Button btn = null;

    String url = "http://10.0.2.2:8080/test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView)findViewById(R.id.text);
        btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientHttp client = new ClientHttp();
                client.execute(url);
            }
        });
    }
    public static void setTxt(StringBuilder text) {
        txt.setText(text);
    }
}