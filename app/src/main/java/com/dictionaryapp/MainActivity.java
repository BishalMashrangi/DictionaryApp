package com.dictionaryapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helper.MyHelper;

public class MainActivity extends AppCompatActivity {
    private Button btnOpen;
    private ListView lstDictionary;
    private Map<String, String> dictionary;
//
//    public static final String words[]={
//            "Yeta Aunus", "Come here",
//            "Uta janus", "Go there",
//            "Nameste", "Hello",
//            "Kata chau ?", "Where are you ?",
//            "Kata jane?", "Where are you going?"
//};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lstDictionary = findViewById(R.id.lstDictionary);
        dictionary = new HashMap<>();
        readFromFile();

//        for (int   i=0; i<words.length; i+=2){
//            dictionary.put(words[i], words[i + 1]);
//        }
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                new ArrayList<>(dictionary.keySet())
        );

        lstDictionary.setAdapter(adapter);
        btnOpen= findViewById(R.id.btnOpen);


        lstDictionary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getItemAtPosition(position).toString();
                String meaning = dictionary.get(key);
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("myMessage", meaning);
                startActivity(intent);

            }
        });
        btnOpen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("myMessage", "This is my message");
                startActivity(intent);
            }
        });




    }
    private void readFromFile(){
        try {
            FileInputStream fos = openFileInput("words.txt");
            InputStreamReader isr= new InputStreamReader(fos);
            BufferedReader br = new BufferedReader(isr);
            String line="";
            while ((line=br.readLine())!=null){
                String[] parts= line.split("->");
                dictionary.put(parts[0], parts[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
