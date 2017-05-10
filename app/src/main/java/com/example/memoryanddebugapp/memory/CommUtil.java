package com.example.memoryanddebugapp.memory;

import android.content.Context;

/**
 * Created by pipi on 2017/5/5.
 * 单例造成的内存泄漏
 * 当调用getInstance时，如果传入的context是Activity的context。
 * 只要这个单例没有被释放，那么这个Activity也不会被释放一直到进程退出才会释放
 */

public class CommUtil {
    private static CommUtil instance;
    private Context context;
    private CommUtil(Context context) {
//        this.context = context;//错误写法
        this.context = context.getApplicationContext();//正确写法
    }

    public static CommUtil getInstance(Context mContext) {
        if (instance == null) {
            instance = new CommUtil(mContext);
        }
        return  instance;
    }

}
