package com.example.memoryanddebugapp.memory;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.memoryanddebugapp.R;

import java.lang.ref.WeakReference;

public class ThreadActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        mTextView = (TextView) findViewById(R.id.thread_textview);

        //错误示例:
        //异步任务和Runnable都是一个匿名内部类，因此它们对当前Activity都有一个隐式引用。如果Activity在销毁之前，任务还未完成，
        //那么将导致Activity的内存资源无法回收，造成内存泄漏
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                SystemClock.sleep(10000);
                return null;
            }
        }.execute();

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(10000);
            }
        }).start();

        //正确实例
//        new Thread(new MyRunnable()).start();
//        new MyAsyncTask(this).execute();
    }

    static class MyAsyncTask extends AsyncTask<Void, Void, Void> {

        private WeakReference<Context> weakReference;

        public MyAsyncTask(Context context) {
            weakReference = new WeakReference<Context>(context);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            SystemClock.sleep(10000);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ThreadActivity activity = (ThreadActivity)weakReference.get();
            if (activity != null) {
                activity.mTextView.setText("我变我变我变变变");

            }
        }
    }

    static class MyRunnable implements  Runnable {
        @Override
        public void run() {
            SystemClock.sleep(10000);
        }
    }
}
