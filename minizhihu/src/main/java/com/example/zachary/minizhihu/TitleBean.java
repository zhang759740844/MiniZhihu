package com.example.zachary.minizhihu;

import java.util.List;

/**
 * Created by zachary on 16/1/11.
 */
public class TitleBean {
    public String date;
    public List<StoriesEntity> stories;
    public List<TopStoryEntity> top_stories;
    public class StoriesEntity{
        public String title;
        public int id;
        public List<String> images;
    }
    public class TopStoryEntity{
        public String title;
        public int id;
        public String image;
    }

}
