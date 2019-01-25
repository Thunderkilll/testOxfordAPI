package com.dev.thunderkilll.testoxfordapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//add dependencies to your class
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.os.AsyncTask;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String value = "";

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            value = bundle.getString("word");
        }
        context = findViewById(R.id.context);
        new CallbackTask().execute(value);
    }

    private String dictionaryEntries() {
        final String language = "en";
        final String word = "war";
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }

    public String getDictEnt() {
        return dictionaryEntries();
    }


    //in android calling network requests on the main thread forbidden by default
    //create class to do async job
    private class CallbackTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            //TODO: replace with your own app id and app key
            final String app_id = "aeaa5a81";
            final String app_key = "e0ecd250dc554c33d323d1dfaf34c2a4";
            try {
                URL url = new URL(params[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestProperty("app_id", app_id);
                urlConnection.setRequestProperty("app_key", app_key);

                // read the output from the server
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                return stringBuilder.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            context.setText(result);
            System.out.println(result);
        }
    }
}
