package com.example.zachary.retrofit;

import java.util.List;

/**
 * Created by zachary on 16/1/11.
 */
public class Information {
    public String date;
    public List<Story> stories;

    public  static class Story{
        public String title;
        public int id;
    }
}
