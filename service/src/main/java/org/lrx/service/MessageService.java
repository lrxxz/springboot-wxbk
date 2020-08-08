package org.lrx.service;

import org.lrx.entity.UniversityMessage;

import java.util.Map;

public interface MessageService {

    //查村个人信息
    Map<String,Object> selectMessage(String messageUid);
    //修改个人信息
    Map<String,Object>  updateMessage(UniversityMessage universityMessage);
}
