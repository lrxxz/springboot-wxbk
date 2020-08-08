package org.lrx.service;

import java.util.Map;

public interface SearchService {
    //查询热搜词
    Map<String,Object> selectSearch();
    //根据热搜词查询作品
    Map<String,Object> selectSearchByContent(String searchContent,Integer page,Integer rows);
}
