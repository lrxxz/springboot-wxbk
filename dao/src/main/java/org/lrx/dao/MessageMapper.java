package org.lrx.dao;

import org.apache.ibatis.annotations.Mapper;
import org.lrx.entity.UniversityMessage;

@Mapper
public interface MessageMapper {
    //查村个人信息
    UniversityMessage selectMessage(String messageUid);
    //修改个人信息
    int updateMessage(UniversityMessage universityMessage);

    int insertMessage(UniversityMessage universityMessage);
}
