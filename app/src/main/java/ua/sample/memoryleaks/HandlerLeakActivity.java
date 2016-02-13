package ua.sample.memoryleaks;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import timber.log.Timber;

public class HandlerLeakActivity extends AppCompatActivity {

    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Timber.i("handleMessage");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 1000 * 60 * 10);
    }
}
