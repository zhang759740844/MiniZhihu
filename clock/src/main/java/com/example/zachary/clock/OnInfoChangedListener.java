package com.example.zachary.clock;

import java.util.List;

/**
 * Created by zachary on 16/1/13.
 */
public interface OnInfoChangedListener {
    void onInfoChanged(List<TitleBean.StoriesEntity> stories, int state);
    void onInfoChanged(TitleBean.TopStoryEntity topStoryEntity);
}
