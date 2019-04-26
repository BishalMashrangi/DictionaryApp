package com.dictionaryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tvOutput = findViewById(R.id.tvOutput);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String message = bundle.getString("myMessage");
            tvOutput.setText(message);
        } else{

        }
            Toast.makeText(this, "No Message", Toast.LENGTH_SHORT).show();

    }

}


