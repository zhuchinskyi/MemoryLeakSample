package ua.sample.memoryleaks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.btnListenerActivity)
    void onStartListenerLeakActivity() {
        startActivity(new Intent(this, ListenerLeakActivity.class));
    }

    @OnClick(R.id.btnThreadActivity)
    void onStartThreadLeakActivity() {
        startActivity(new Intent(this, ThreadLeakActivity.class));
    }

    @OnClick(R.id.btnRxActivity)
    void onStartRxLeakActivity() {
        startActivity(new Intent(this, RxLeakActivity.class));
    }

}
