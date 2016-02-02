package com.example.zachary.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by zachary on 16/1/29.
 */
public class Third extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment,container,false);
        TextView textView = (TextView)view.findViewById(R.id.textview);
        textView.setText("third");
        Button button =(Button )view.findViewById(R.id.button);
        button.setText("popbackstack");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
