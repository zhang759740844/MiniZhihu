package com.example.zachary.leakcanarytext;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;

import org.w3c.dom.Text;

/**
 * Created by zachary on 16/2/1.
 */
public class Fragment2 extends Fragment {
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment,container,false);
        textView = (TextView) view.findViewById(R.id.asd);

        TestDataModel.getInstance().setRetainedTextView(textView);
        return view ;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = ExampleApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
