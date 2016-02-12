package ua.sample.memoryleaks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), ListenerLeakActivity.class);
//                Intent intent = new Intent(v.getContext(), ThreadLeakActivity.class);
                Intent intent = new Intent(v.getContext(), RxLeakActivity.class);
                startActivity(intent);
            }
        });
    }
}
