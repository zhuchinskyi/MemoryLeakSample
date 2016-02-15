package ua.sample.memoryleaks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class HandlerLeakActivity extends AppCompatActivity {

    /**
     * In this case the message holds a reference to the activity's Handler, and the Handler holds an implicit reference to its outer class.
     * It would cause Memory Leaks of activity even after onDestroy will be called.
     */
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //..
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //..
            }
        }, 1000 * 60 * 10);
    }
}
