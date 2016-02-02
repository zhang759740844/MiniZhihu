package com.example.zachary.minizhihu;

import java.util.List;

/**
 * Created by zachary on 16/1/20.
 */
public class ThemeBean {
    public List<TitleBean.StoriesEntity> stories;
    public String description;
    public String image;
    public List<EditorsEntity>editors;

    public class EditorsEntity{
        public String avatar;     //url of editors' avatar
        public String name;         //name of editors
        public String bio;          //brief introduction of editors
    }
}
