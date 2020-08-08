package org.lrx.service;

import org.lrx.entity.University;

import java.util.Map;

public interface UniversityService {
    //注册大学信息
     Map<String,Object> universityInsert(University university);

    //查询大学信息
     Map<String,Object> selectUniverMessage(Integer page,Integer rows);

     //删除大学信息
    Map<String,Object> deleteUniversity(String universityId);
}
