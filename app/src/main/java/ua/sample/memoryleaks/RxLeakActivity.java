package ua.sample.memoryleaks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class RxLeakActivity extends AppCompatActivity {

    private static final String TAG = RxLeakActivity.class.getSimpleName();

    private Subscription mSubscription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rx);

        mSubscription = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Log.d(TAG, "LeakingActivity received: " + aLong);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}