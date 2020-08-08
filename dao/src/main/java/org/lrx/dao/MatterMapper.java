package org.lrx.dao;

import org.apache.ibatis.annotations.Mapper;
import org.lrx.entity.UniversityMatter;
import org.lrx.entity.UniversityPicture;

import java.util.List;

@Mapper
public interface MatterMapper {

    //新闻插入
    public int matterInsert(UniversityMatter universityMatter);

    //照片上传
    int insertFile(List<UniversityPicture> universityPictureList);

    //查询全部的新闻
    //下面两个方法关联
    List<UniversityMatter> selectUniver();
    //查询图片
    List<UniversityPicture> selectUniverPicture(String pMid);

    //查询单个
    UniversityMatter selectUniverOne(String mId);

    //查询属于一个种类的新闻
    List<UniversityMatter> selectUniverKind(String mKind);

    //根据用户id查询发布的作品
    List<UniversityMatter> selectUniverByUser(String mUid);

    //根据作品的id来删除作品
    int deleteUniverMatterById(String mId);


}
