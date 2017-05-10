package com.example.memoryanddebugapp.debug;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.memoryanddebugapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DebugActivity extends AppCompatActivity {

    private TextView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        new DebugDemo().alertName();

        mView = (TextView) findViewById(R.id.debug_textview);
        mView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int result = new Random().nextInt(100);
                Log.d("DebugActivity", "onClick: " + result);
            }
        });

        int sum = test1();
        test2();
        test3();
        test4();
        test5();
    }

    /**
     * 修改变量值测试
     */
    private void test2() {
        int result = 9 + 1;
        System.out.println(result);
    }

    /**
     * 条件断点
     */
    private void test3() {
        List<String> list = new ArrayList<>();
        list.add("q");
        list.add("2q");
        list.add("3q");
        list.add("4q");

        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * 日志断点
     */
    private void test4() {
        List<String> list = new ArrayList<>();
        list.add("r");
        list.add("2r");
        list.add("3r");
        list.add("4r");

        for (String s : list) {
            System.out.println(s);
        }
    }

    /**
     * 异常断点
     */
    private void test5() {
        String s = null;
        try {
            if (s.equals("")) {

            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 变量调试
     */
    private int test1() {
        int sum = 0;
        int b = 4;
        int a = 5;
        int c = 6;
        sum = a+b+c;

        return sum;
    }


    public class DebugDemo {

        private String name = "default";

        public void alertName() {
            System.out.println(name);
            debug();
        }

        public void debug() {
            this.name = "debug";
        }
    }
}
