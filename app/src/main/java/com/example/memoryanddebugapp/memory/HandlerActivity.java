package com.example.memoryanddebugapp.memory;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.memoryanddebugapp.R;

import java.lang.ref.WeakReference;

public class HandlerActivity extends AppCompatActivity {

    private MyHandler mHandler = new MyHandler(this);
    private TextView mTextView;

    /**
     * 错误示例
     * mHandler是Handler的非静态匿名内部类的实例，所以它持有外部类Activity的引用，
     * 我们知道消息队列是在一个Looper线程中不断轮询处理消息，
     * 那么当这个Activity退出时消息队列中还有未处理的消息或者正在处理消息，
     * 而消息队列中的Message持有mHandler实例的引用，mHandler又持有Activity的引用，
     * 所以导致该Activity的内存资源无法及时回收，引发内存泄漏。
     */
    private class MyHandler extends Handler {
        private WeakReference<Context> reference;
        public MyHandler(Context context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerActivity activity = (HandlerActivity)reference.get();
            if (null != activity) {
                activity.mTextView.setText("哈哈哈哈哈哈");
            }
        }
    }


    //解决方案
    //创建一个静态Handler内部类，然后对Handler持有的对象使用弱引用，这样在回收时也可以回收Handler持有的对象，
    // 这样虽然避免了Activity泄漏，不过Looper线程的消息队列中还是可能会有待处理的消息，
    // 所以我们在Activity的Destroy时或者Stop时应该移除消息队列中的消息
//    private static class MyHandler extends Handler {
//        private WeakReference<Context> reference;
//        public MyHandler(Context context) {
//            reference = new WeakReference<>(context);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            HandlerActivity activity = (HandlerActivity)reference.get();
//            if (null != activity) {
//                activity.mTextView.setText("哈哈哈哈哈哈");
//            }
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_handler);

        mTextView = (TextView) findViewById(R.id.handler_textview);
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mHandler.removeCallbacksAndMessages(null);
    }

    private void loadData() {
        Message message = Message.obtain();
        mHandler.sendMessageDelayed(message, 15000);
    }



}
