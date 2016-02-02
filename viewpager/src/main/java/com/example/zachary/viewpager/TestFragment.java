package com.example.zachary.viewpager;

/**
 * Created by zachary on 16/1/14.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TestFragment extends Fragment {
    private static final String TAG = "TestFragment";
    private String hello;// = "hello android";
    private String defaultHello = "default value";
    private ImageView image;
    static TestFragment newInstance(String s) {
        TestFragment newFragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("hello", s);
        newFragment.setArguments(bundle);

        //bundle还可以在每个标签里传送数据


        return newFragment;

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Log.d(TAG, "TestFragment-----onCreateView");
        Bundle args = getArguments();
        hello = args != null ? args.getString("hello") : defaultHello;
        View view = inflater.inflate(R.layout.layout2, container, false);
        TextView viewhello = (TextView) view.findViewById(R.id.tv);
        viewhello.setText(hello);
        image = (ImageView)view.findViewById(R.id.button);
        return view;

    }



}