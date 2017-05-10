package com.example.memoryanddebugapp.memory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.memoryanddebugapp.R;

/**
 * 在实际的项目开发中，有时候我们需要频繁的启动某个页面（Activity），启动的时候总是需要初始化一些资源，
 * 为了避免重复创建相同资源，常常会使用静态对象去保存这些值，这种情况下，也很容易照成内存泄漏
 */
public class InnerClassActivity extends AppCompatActivity {
    //为避免频繁启动时初始化相同资源，声明mResource为静态变量
    private static TestResource mResource = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_innerclass);

//        initData();
    }

    private void initData() {
        if (mResource == null) {
            mResource = new TestResource();
        }
    }


    /**
     * 非静态内部类
     */
    class TestResource {

    }

    /*******************
     *  1. 首先，非静态内部类默认会持有外部类的引用。
     *  2. 然后又使用了该非静态内部类创建了一个静态的实例。
     *  3. 该静态实例的生命周期和应用的一样长，这就导致了该静态实例一直会持有该Activity的引用，导致Activity的内存资源不能正常回收。
     *
     *  正确的做法有两种，一种是将内部类testResource改成静态内部类，
     *  还有就是将testResource抽取出来，封装成一个单例，如上一个例子那样，但是需要context时单例要切记注意Context的泄漏，使用applicationContext。
     *******************/
    /**
     * 静态内部类
     */
//    private static class TestResource {
//
//    }



}
