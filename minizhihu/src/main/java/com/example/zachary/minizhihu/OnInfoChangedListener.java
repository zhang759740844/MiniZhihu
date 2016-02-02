package com.example.zachary.minizhihu;

import android.app.Fragment;

import java.util.List;

/**
 * Created by zachary on 16/1/13.
 */
public interface OnInfoChangedListener {
    void onInfoChanged(List entity,int state);
    void onInfoChanged(Object entity);
    void onInfoChanged(List entity);

}
