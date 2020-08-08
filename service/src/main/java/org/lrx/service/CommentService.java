package org.lrx.service;

import org.lrx.entity.UniversityComment;

import java.util.Map;

public interface CommentService {
    //实现评论的上传
    Map<String,Object> insertComment(UniversityComment universityComment);
    Map<String,Object> selectComment(String comMid,Integer page,Integer rows);
    Map<String,Object> deleteComment(Integer comId);
}
