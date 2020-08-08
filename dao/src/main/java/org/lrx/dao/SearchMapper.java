package org.lrx.dao;

import org.apache.ibatis.annotations.Mapper;
import org.lrx.entity.UniversityMatter;

import java.util.List;

@Mapper
public interface SearchMapper {
    //查询热搜词
    List<String> selectSearch();
    //保存热搜词
    int insertSearch(String searchContent);
    //根据热搜词查询作品
    List<UniversityMatter> selectSearchByContent(String searchContent);
}
