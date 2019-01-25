package com.dev.thunderkilll.testoxfordapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }

    public void StartTest(View view) {
        InternetConnectivityObserver.get().setConsumer(new InternetConnectivityObserver.Consumer() {
            @Override
            public void accept(boolean internet) {
                if (internet) goToHomeOnline();//connected
                else  goToHomeOffline() ;
            }
        });
        InternetConnectivityObserver.get().start();
        return;

    }

    private void goToHomeOnline() {
        Intent intent = new Intent(Main2Activity.this , Main3Activity.class);
        startActivity(intent);
        Main2Activity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    private void goToHomeOffline() {
        Intent intent = new Intent(Main2Activity.this , Main4Activity.class);
        startActivity(intent);
        Main2Activity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}
