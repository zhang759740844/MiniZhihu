package com.example.zachary.leakcanarytext;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by zachary on 16/2/1.
 */
public class TextActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TextView textView = (TextView) findViewById(R.id.test_text_view);

        TestDataModel.getInstance().setRetainedTextView(textView);
    }
}
