package ua.sample.memoryleaks;

import android.app.Application;

/**
 * Created by d.zhuchinskiy on 1/20/16.
 */
public class MemoryApplication extends Application {
//    public static RefWatcher getRefWatcher(Context context) {
//        MemoryApplication application = (MemoryApplication) context.getApplicationContext();
//        return application.refWatcher;
//    }
//
//    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
//        refWatcher = LeakCanary.install(this);
    }
}
