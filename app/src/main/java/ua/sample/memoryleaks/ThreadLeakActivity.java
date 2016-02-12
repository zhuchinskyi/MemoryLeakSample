package ua.sample.memoryleaks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.lang.ref.WeakReference;

public class ThreadLeakActivity extends AppCompatActivity {

    static SomeEventHandler mSomeEventHandler;

    private static class SomeEventHandler extends Handler {
        private WeakReference<ThreadLeakActivity> mActivity;

        SomeEventHandler(ThreadLeakActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        public void setTarget(ThreadLeakActivity activity) {
            mActivity.clear();
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            ThreadLeakActivity activity = mActivity.get();
            activity.update(msg.arg1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_leak);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (null == mSomeEventHandler) {
            mSomeEventHandler = new SomeEventHandler(this);
        } else {
            mSomeEventHandler.setTarget(this);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    void update(int arg) {
    }

}
