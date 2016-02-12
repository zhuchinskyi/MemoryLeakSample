package ua.sample.memoryleaks;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ThirdLeakActivity extends AppCompatActivity {

    private static String TAG = ThirdLeakActivity.class.getSimpleName();
    TextView tvStatus;
    Button someButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_leak);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        someButton = (Button) findViewById(R.id.someButton);
        tvStatus = (TextView) findViewById(R.id.tvStatus);


        setSupportActionBar(toolbar);


        RxView.clicks(someButton).flatMap(new Func1<Void, Observable<String>>() {
            @Override
            public Observable<String> call(Void aVoid) {
                return getDataFromApi();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        saveData("AA");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        saveData(s);
                    }
                });


    }

    Observable<String> getDataFromApi() {
        SystemClock.sleep(100);
        return Observable.just("Mocked server response");
    }

    void saveData(String string) {
        Log.i(TAG, "Saved. Data: " + string);
        tvStatus.setText("Updated");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
