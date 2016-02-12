package ua.sample.memoryleaks;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.RefWatcher;

public class MemoryApplication extends Application {

    public static RefWatcher getRefWatcher(Context context) {
        MemoryApplication application = (MemoryApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
//        refWatcher = LeakCanary.install(this);
    }
}
