package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList <Mountain> items;
    private ArrayAdapter <Mountain> adapter;
    ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        adapter = new ArrayAdapter<Mountain>(this, R.layout.list_item_text_view,R.id.list_item_text_view,items); // create an ArrayAdapter
        ListView my_listview = findViewById(R.id.my_list_view); // Get a reference to our ListView
        my_listview.setAdapter(adapter); // Connect ArrayAdapter to ListView
        my_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = items.get(position).getName("name");
                int size = items.get(position).getSize("size");
                String location = items.get(position).getLocation("location");
                String msg = name + " it's about " + size + " meter. " + "location: " + location + ".";
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });//  OnItemClickListener

        new JsonTask().execute("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=brom");
    } // onCreate

    @SuppressLint("StaticFieldLeak")
    private class JsonTask extends AsyncTask<String, String, String> {

            private HttpURLConnection connection = null;
            private BufferedReader reader = null;
        // this happends in the bacground
            protected String doInBackground(String... params) {
                try {
                    URL url = new URL(params[0]);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();

                    InputStream stream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null && !isCancelled()) {
                        builder.append(line).append("\n");
                    }
                    return builder.toString();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(String json) {
                Log.d("TAG", json);
            try {
                // mitt JSON objekt
                JSONArray jsonarray = new JSONArray(json);
                for(int i = 0; i < jsonarray.length(); i++){
                    JSONObject object = jsonarray.getJSONObject(i);

                    String name = object.getString("name");
                    int size = object.getInt("size");
                    String location = object.getString("location");

                    items.add(new Mountain(name, size, location));
                    adapter.notifyDataSetChanged();
                }

            } catch (JSONException e) {
                Log.e("brom","E:"+e.getMessage());
            }
        }

    } //JsonTask
} //  MainActivity

