package com.dev.thunderkilll.testoxfordapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {
EditText ed ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ed = findViewById(R.id.editText);
    }

    public void GetWord(View view) {
        System.out.println(ed.getText());
        String wordApi = dictionaryEntries() ;
        Intent intent = new Intent(Main3Activity.this , MainActivity.class);
        intent.putExtra("word",wordApi);
        startActivity(intent);

    }
    public   String dictionaryEntries() {
        final String language = "en";
        final String word = String.valueOf(ed.getText());
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }
}
