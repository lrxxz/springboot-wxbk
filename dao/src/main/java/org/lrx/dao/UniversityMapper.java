package org.lrx.dao;

import org.apache.ibatis.annotations.Mapper;
import org.lrx.entity.University;
import org.lrx.entity.UniversityPicture2;

import java.util.List;

@Mapper
public interface UniversityMapper {
    //大学插入
    int universityInsert(University university);

    //照片上传
    int insertUniversityFile(List<UniversityPicture2> universityPictureList2);

    //查询大学信息
    List<University> selectUniverMessage();
    //查询图片
    List<UniversityPicture2> selectUniverPicture2(String universityId);

    //大学信息删除
    int deleteUniversity(String universityId);
}
