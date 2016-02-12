package ua.sample.memoryleaks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by d.zhuchinskiy on 1/21/16.
 */
public class LeakyFragment extends Fragment {

    private View mLeak; // retained

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLeak = inflater.inflate(R.layout.activity_second, container, false);
        return mLeak;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // not cleaning up.
    }
}