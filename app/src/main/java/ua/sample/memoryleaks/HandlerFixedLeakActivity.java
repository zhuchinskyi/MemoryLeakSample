package ua.sample.memoryleaks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class HandlerFixedLeakActivity extends AppCompatActivity {
    /**
     * This is a fixed version of HandlerLeakActivity.
     * Static inner classes do not hold an implicit reference to their outer class, so the activity will not be leaked.
     * SampleHandle demonstrates the right way how to hold reference to outer class using WeakReference.
     */
    private static class SampleHandle extends Handler {
        private final WeakReference<HandlerFixedLeakActivity> handlerFixedLeakActivityWeakReference;

        public SampleHandle(HandlerFixedLeakActivity activity) {
            handlerFixedLeakActivityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlerFixedLeakActivity handlerFixedLeakActivity = handlerFixedLeakActivityWeakReference.get();
            if (handlerFixedLeakActivity != null) {
                //..
            }
        }
    }

    private final SampleHandle handle = new SampleHandle(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        handle.postDelayed(runnable, 1000 * 60 * 10);
    }

    private static final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //..
        }
    };
}
