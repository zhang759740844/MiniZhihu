package com.example.zachary.minizhihu;

import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.List;
import java.util.Objects;

/**
 * Created by zachary on 16/1/19.
 */
public abstract class BaseFragment extends Fragment implements OnInfoChangedListener {
    @Override
    public void onInfoChanged(List entity, int state) {
        Log.d("monitor2", "BaseFragment infochangedexception1");
    }

    @Override
    public void onInfoChanged(Object entity) {
        Log.d("monitor2", "BaseFragment infochangedexception2");
    }

    @Override
    public void onInfoChanged(List entity) {
        Log.d("monitor2", "BaseFragment infochangedexception3");
    }
}
