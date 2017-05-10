package com.example.memoryanddebugapp.memory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.memoryanddebugapp.R;

/**
 * 对于使用了BraodcastReceiver，ContentObserver，File，Cursor，Stream，Bitmap等资源的使用，
 * 应该在Activity销毁时及时关闭或者注销，否则这些资源将不会被回收，造成内存泄漏
 */
public class BroadCastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        initView();
    }

    /**
     * E/ActivityThread: Activity com.example.memoryanddebugapp.memory.BroadCastActivity has leaked IntentReceiver com.example.memoryanddebugapp.memory.BroadCastActivity$1@427d646 that was originally registered here. Are you missing a call to unregisterReceiver()?
     * android.app.IntentReceiverLeaked: Activity com.example.memoryanddebugapp.memory.BroadCastActivity has leaked IntentReceiver com.example.memoryanddebugapp.memory.BroadCastActivity$1@427d646 that was originally registered here. Are you missing a call to unregisterReceiver()?
     * at android.app.LoadedApk$ReceiverDispatcher.<init>(LoadedApk.java:920)
     * at android.app.LoadedApk.getReceiverDispatcher(LoadedApk.java:721)
     * at android.app.ContextImpl.registerReceiverInternal(ContextImpl.java:1182)
     * at android.app.ContextImpl.registerReceiver(ContextImpl.java:1162)
     * at android.app.ContextImpl.registerReceiver(ContextImpl.java:1156)
     * at android.content.ContextWrapper.registerReceiver(ContextWrapper.java:564)
     * at com.example.memoryanddebugapp.memory.BroadCastActivity.initView(BroadCastActivity.java:40)
     * at com.example.memoryanddebugapp.memory.BroadCastActivity.onCreate(BroadCastActivity.java:24)
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(playerBroadcastReceiver);//在Activity销毁时及时关闭或者注销
    }

    /**
     * 初始化页面
     */
    private void initView() {

        IntentFilter playerFilter = new IntentFilter();
        playerFilter.addAction("close_music_video_play");// 用于盒子端多播放器冲突
        registerReceiver(playerBroadcastReceiver, playerFilter);// 应用界面广播注册
    }

    /**
     * 音乐界面接收暂停当前播放广播
     */
    private BroadcastReceiver playerBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context cxt, Intent intent) {
            if (intent.getAction().equals("close_music_video_play")) {
                int fromId = intent.getExtras().getInt("id");
            }
        }
    };
}
