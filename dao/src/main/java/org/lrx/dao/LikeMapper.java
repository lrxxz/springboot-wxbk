package org.lrx.dao;

import org.apache.ibatis.annotations.Mapper;
import org.lrx.entity.UniversityLike;

import java.util.List;

@Mapper
public interface LikeMapper {
    //收藏插入
    int likeInsert(UniversityLike universityLike);
    //查询收藏根据用户的id
    List<UniversityLike> likeSelect(String likeUid);
    //删除收藏根据作品的id
    int likeDelete(String likeMid);
}
