package com.example.memoryanddebugapp;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.memoryanddebugapp.debug.DebugActivity;
import com.example.memoryanddebugapp.memory.BroadCastActivity;
import com.example.memoryanddebugapp.memory.HandlerActivity;
import com.example.memoryanddebugapp.memory.InnerClassActivity;
import com.example.memoryanddebugapp.memory.ListenerActivity;
import com.example.memoryanddebugapp.memory.ServiceActivity;
import com.example.memoryanddebugapp.memory.StaticViewAndActivity;
import com.example.memoryanddebugapp.memory.ThreadActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        ActivityManager activityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        int memory = activityManager.getMemoryClass();
        int largeMemory = activityManager.getLargeMemoryClass();

        Log.d(TAG, "onCreate: memory: "+memory);//192
        Log.d(TAG, "onCreate: largeMemory: "+largeMemory);//512

        initViewAndListeners();
    }

    private void initViewAndListeners() {
        Button startBtn = (Button)this.findViewById(R.id.inner_class_text);
        Button handlerBtn = (Button)this.findViewById(R.id.handler_test);
        Button threadBtn = (Button)this.findViewById(R.id.thread_test);
        Button bcrBtn = (Button)this.findViewById(R.id.bcr_test);
        Button staticAVBtn =  (Button)this.findViewById(R.id.static_acti_and_view_test);
        Button serviceBtn =  (Button)this.findViewById(R.id.service_test);
        Button listenerBtn =  (Button)this.findViewById(R.id.listener_test);
        Button debugBtn =  (Button)this.findViewById(R.id.debug_test);

        startBtn.setOnClickListener(this);
        handlerBtn.setOnClickListener(this);
        threadBtn.setOnClickListener(this);
        bcrBtn.setOnClickListener(this);
        staticAVBtn.setOnClickListener(this);
        serviceBtn.setOnClickListener(this);
        listenerBtn.setOnClickListener(this);
        debugBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.inner_class_text:
                Intent classintent = new Intent(MainActivity.this, InnerClassActivity.class);
                startActivity(classintent);
                break;
            case R.id.handler_test:
                Intent handlerintent = new Intent(MainActivity.this, HandlerActivity.class);
                startActivity(handlerintent);
                break;
            case R.id.thread_test:
                Intent threadintent = new Intent(MainActivity.this, ThreadActivity.class);
                startActivity(threadintent);
                break;
            case R.id.bcr_test:
                Intent bcrintent = new Intent(MainActivity.this, BroadCastActivity.class);
                startActivity(bcrintent);
                break;
            case R.id.static_acti_and_view_test:
                Intent savintent = new Intent(MainActivity.this, StaticViewAndActivity.class);
                startActivity(savintent);
                break;
            case R.id.service_test:
                Intent serviceintent = new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(serviceintent);
                break;
            case R.id.listener_test:
                Intent listererintent = new Intent(MainActivity.this, ListenerActivity.class);
                startActivity(listererintent);
                break;
            case R.id.debug_test:
                Intent debugintent = new Intent(MainActivity.this, DebugActivity.class);
                startActivity(debugintent);
                break;
            default:break;
        }
    }
}
