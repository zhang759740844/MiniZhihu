package com.example.zachary.annotation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class MainActivity extends Activity {
    @TextViewAnnovation(id=R.id.text1)
    public TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            setAnnotation();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        textView.setText("Modified by Annotation");
    }

    private void setAnnotation() throws ClassNotFoundException, IllegalAccessException {
        Class mainActivity = this.getClass();
        Field[] fields = mainActivity.getFields();
        for(Field field:fields){
//            this.getResources();
            if(field.isAnnotationPresent(TextViewAnnovation.class)){
                TextViewAnnovation textViewAnnovation = field.getAnnotation(TextViewAnnovation.class);
                int id = textViewAnnovation.id();
                if(id>0){
                    field.setAccessible(true);
                    field.set(this, this.findViewById(id));
                }
            }
        }

    }
}


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface TextViewAnnovation{
    int id() default -1;
}
