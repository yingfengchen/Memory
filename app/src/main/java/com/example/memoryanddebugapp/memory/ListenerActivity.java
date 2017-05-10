package com.example.memoryanddebugapp.memory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.example.memoryanddebugapp.R;

/**
 * 不需要用的监听未移除会发生内存泄露
 *
 * Tip：tv.setOnClickListener();//监听执行完回收对象，不用考虑内存泄漏
 * tv.getViewTreeObserver().addOnWindowFocusChangeListene,add监听，放到集合里面，需要考虑内存泄漏
 */
public class ListenerActivity extends AppCompatActivity {

    private TextView tv;
    private ViewTreeObserver.OnWindowFocusChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener);

        tv = (TextView)findViewById(R.id.listener_textview);

        listener = new ViewTreeObserver.OnWindowFocusChangeListener() {
            @Override
            public void onWindowFocusChanged(boolean b) {
                //监听view的加载，view加载出来的时候，计算他的宽高
            }
        };

        //add监听，放到集合里面
        tv.getViewTreeObserver().addOnWindowFocusChangeListener(listener);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

//        tv.getViewTreeObserver().removeOnWindowFocusChangeListener(listener);
    }
}
