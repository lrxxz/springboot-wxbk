package org.lrx.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.lrx.dao.MatterMapper;
import org.lrx.dao.SearchMapper;
import org.lrx.dao.UserMapper;
import org.lrx.entity.UniversityMatter;
import org.lrx.entity.UniversityPicture;
import org.lrx.entity.UniversityUser;
import org.lrx.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchMapper searchMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    MatterMapper matterMapper;

    /**
     * 查询全部的热搜词，根据词的出现频率排序
     */
    @Override
    public Map<String, Object> selectSearch() {
        Map<String ,Object> map = new HashMap<>();
        List<String> searchs = searchMapper.selectSearch();
        map.put("searchs",searchs);
        return map;
    }

    /**
     * 实现更具热搜词查询相关的作品，并把热搜词保存
     * @param searchContent
     * @return
     */
    @Override
    public Map<String, Object> selectSearchByContent(String searchContent,Integer page,Integer rows) {
        Map<String ,Object> map = new HashMap<>();
        //保存该热搜词
        searchMapper.insertSearch(searchContent);

        Page<UniversityMatter> pages = PageHelper.startPage(page,rows).doSelectPage(()->searchMapper.selectSearchByContent(searchContent));
        List<UniversityMatter> result = pages.getResult();
        Iterator<UniversityMatter> universityMatterIterator = result.iterator();
        while(universityMatterIterator.hasNext()){
            UniversityMatter universityMatter = universityMatterIterator.next();
            //查出的属于它的图片
            List<UniversityPicture> universityPictureList = matterMapper.selectUniverPicture(universityMatter.getmId());
            //查出属于他的用户
            UniversityUser universityUser = userMapper.userSelectById(universityMatter.getmUid());
            universityUser.setPassword(null);
            universityMatter.setUniversityPictureList(universityPictureList);
            universityMatter.setUniversityUser(universityUser);
        }
        map.put("universityMatters",result);
        map.put("page", pages.getPageNum());  //当前页
        map.put("pages",pages.getPages());    //总页数
        map.put("total",pages.getTotal());    //总数据量
        return map;
    }
}
