package ua.sample.memoryleaks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import timber.log.Timber;

public class RxLeakActivity extends AppCompatActivity {

    /**
     * In this case we have a hot observable that emits a sequential number every second.
     * To fix this issue you can use unsubscribe method in onDestroy or using RxLifecycle.
     */

    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rx);

        mSubscription = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Timber.d("RxLeakActivity received: " + aLong);
                    }
                });
    }

    @Override
    protected void onDestroy() {
//        mSubscription.unsubscribe();
        super.onDestroy();
    }
}