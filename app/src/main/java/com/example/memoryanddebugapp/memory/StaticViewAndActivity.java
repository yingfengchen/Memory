package com.example.memoryanddebugapp.memory;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.memoryanddebugapp.R;

public class StaticViewAndActivity extends AppCompatActivity {

    static View view;
    static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_view_and_activity);

        Button svButton = (Button) findViewById(R.id.sv_button);
        svButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setStaticView();
            }
        });

        Button saButton = (Button) findViewById(R.id.sa_button);
        saButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStaticActivity();
            }
        });

    }

    /**
     * 设置静态view
     */
    private void setStaticView() {
        view = findViewById(R.id.sv_button);
    }

    /**
     * 设置静态Activity
     */
    private void setStaticActivity() {
        activity = this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        view = null;
//        activity = null;
    }
}
