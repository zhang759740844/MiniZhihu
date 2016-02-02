package com.example.zachary.minizhihu;

import java.util.List;

/**
 * Created by zachary on 16/1/26.
 */
public class CommentBean {
    public List<CommentEntity> comments;

    public class CommentEntity{
        public String author;
        public int id;
        public String content;
        public int likes;
        public int time;
        public String avatar;
    }
}
