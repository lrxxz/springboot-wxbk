package org.lrx.service;

import org.lrx.entity.UniversityLike;

import java.util.Map;

public interface LikeService {

    //收藏插入
    Map<String,Object> likeInsert(UniversityLike universityLike);
    //查询收藏根据用户的id
    Map<String,Object> likeSelect(String likeUid);

    Map<String,Object> likeDelete(String likeMid);
}
