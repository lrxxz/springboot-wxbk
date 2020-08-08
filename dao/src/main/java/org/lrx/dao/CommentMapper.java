package org.lrx.dao;

import org.apache.ibatis.annotations.Mapper;
import org.lrx.entity.UniversityComment;

import java.util.List;

@Mapper
public interface CommentMapper {
    //实现评论上传
    int insertComment(UniversityComment universityComment);
    //实现评论的查询
    List<UniversityComment> selectComment(String comMid);
    //实现删除自己的评论
    int deleteComment(Integer comId);
}
