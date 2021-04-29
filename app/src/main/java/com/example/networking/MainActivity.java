package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Mountain[] mountain={new Mountain()};
    private ArrayList < Mountain> arrayMountain;
    private ArrayAdapter<Mountain> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       adapter=new ArrayAdapter<Mountain>(this,R.layout.list_item_text_view);
    }
}
