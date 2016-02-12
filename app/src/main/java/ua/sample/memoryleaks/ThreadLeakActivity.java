package ua.sample.memoryleaks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.lang.ref.WeakReference;

public class ThreadLeakActivity extends AppCompatActivity {

    private static class SomeEventHandler extends Handler {
        private final WeakReference<ThreadLeakActivity> mActivity;

        public SomeEventHandler(ThreadLeakActivity activity) {
            this.mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {

            ThreadLeakActivity activity = mActivity.get();
            if (activity != null) {
                // ...
            }
        }
    }

    private final SomeEventHandler mLeakyHandler = new SomeEventHandler(this);

//    private final Handler mLeakyHandler = new Handler() {
//        ThreadLeakActivity activityWeakReference;
//
//        @Override
//        public void handleMessage(Message msg) {
//        }
//    };

    private static final Runnable sRunnable = new Runnable() {
        @Override
        public void run() { /* ... */ }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLeakyHandler.postDelayed(sRunnable, 1000 * 60 * 10);

        // Post a message and delay its execution for 10 minutes.
//        mLeakyHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() { /* ... */ }
//        }, 1000 * 60 * 10);

        // Go back to the previous Activity.
//        finish();


    }


}
