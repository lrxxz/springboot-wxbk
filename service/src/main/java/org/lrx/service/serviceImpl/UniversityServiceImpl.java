package org.lrx.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.lrx.dao.UniversityMapper;
import org.lrx.entity.University;
import org.lrx.entity.UniversityPicture2;
import org.lrx.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UniversityServiceImpl implements UniversityService {

    @Autowired
    UniversityMapper universityMapper;

    /**
     * 发布大学信息
     */
    @Override
    public Map<String, Object> universityInsert(University university) {
        university.setUniversityId(UUID.randomUUID().toString());
        Map<String ,Object> map = new HashMap<>();
        int i = universityMapper.universityInsert(university);
        if(i<0){
            map.put("type","error");
            map.put("msg","发布错误");
            return map;
        }
        map.put("type","success");
        map.put("msg","发布成功");
        map.put("universityId",university.getUniversityId());
        return map;
    }

    /**
     * 查询大学信息
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Map<String, Object> selectUniverMessage(Integer page, Integer rows) {
        //先将新闻查出
        Map<String,Object> map = new HashMap<>();
        Page<University> pages = PageHelper.startPage(page,rows).doSelectPage(()->universityMapper.selectUniverMessage());
        List<University> result = pages.getResult();
        //迭代查出的新闻，为其查询对应的多张图片（为什么要这么做，因为直接两个表关联查询会出bug）
        Iterator<University> universityIterator = result.iterator();
        while(universityIterator.hasNext()){
            University university = universityIterator.next();
            //查出的属于它的图片
            List<UniversityPicture2> universityPicture2List = universityMapper.selectUniverPicture2(university.getUniversityId());
            university.setUniversityPictureList2(universityPicture2List);
        }
        map.put("universityMessages",result);
        map.put("page", pages.getPageNum());  //当前页
        map.put("pages",pages.getPages());    //总页数
        map.put("total",pages.getTotal());    //总数据量
        return map;
    }

    /**
     * 删除对应的大学信息
     * @param universityId
     * @return
     */
    @Override
    public Map<String, Object> deleteUniversity(String universityId) {
        Map<String,Object> map = new HashMap<>();
        int i = universityMapper.deleteUniversity(universityId);
        if(i<0){
            map.put("type","error");
            map.put("msg","删除失败");
            return map;
        }
        map.put("type","success");
        map.put("msg","删除成功");
        return map;
    }
}
