package com.example.niyat.myclicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnClick;
    Button btnReset;
    Button btnDown;
    TextView textCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClick = (Button) findViewById(R.id.buttonClick);
        btnReset = (Button) findViewById(R.id.buttonReset);
        btnDown = (Button) findViewById(R.id.buttonClickDown);
        textCount = (TextView) findViewById(R.id.textViewCount);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countValue = textCount.getText().toString();

                int intCountValue = Integer.parseInt(countValue);
                intCountValue++;

                textCount.setText(String.valueOf(intCountValue));
            }
        });
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countValue = textCount.getText().toString();

                int intCountValue = Integer.parseInt(countValue);
                intCountValue--;

                textCount.setText(String.valueOf(intCountValue));
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textCount.setText(String.valueOf(0));
            }
        });


    }
}
