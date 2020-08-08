package org.lrx.service;

import org.lrx.entity.UniversityMatter;
import java.util.Map;

public interface MatterService {
    public Map<String,Object> matterInsert(UniversityMatter universityMatter);

    public Map<String,Object> selectUniver(Integer page,Integer rows);
    //查询单个新闻
    public UniversityMatter selectUniverOne(String mId);
    //查询一类新闻
    public Map<String,Object> selectUniverKind(Integer page,Integer rows,String mKind);
    //根据用户id查询他自己的发布
    public Map<String,Object> selectUniverByUser(String mUid);
    //删除用户发布的作品
    public  Map<String,Object> deleteUniverMatterById(String mId);
}
