package org.lrx.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.lrx.dao.MatterMapper;
import org.lrx.dao.UserMapper;
import org.lrx.entity.UniversityMatter;
import org.lrx.entity.UniversityPicture;
import org.lrx.entity.UniversityUser;
import org.lrx.service.MatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MatterServiceImpl implements MatterService {

    @Autowired
    MatterMapper matterMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 发布新闻
     * @param universityMatter
     * @return
     */
    @Override
    public Map<String,Object> matterInsert(UniversityMatter universityMatter) {
        universityMatter.setmId(UUID.randomUUID().toString());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        universityMatter.setmTime(df.format(new Date()));
        Map<String ,Object> map = new HashMap<>();
        int i = matterMapper.matterInsert(universityMatter);
        if(i<0){
            map.put("type","error");
            map.put("msg","发布错误");
            return map;
        }
        map.put("type","success");
        map.put("msg","发布成功");
        map.put("pMid",universityMatter.getmId());
        return map;
    }

    /**
     * 查询全部新闻
     * @return
     */
    @Override
    public Map<String, Object> selectUniver(Integer page,Integer rows) {
        //先将新闻查出
        Map<String,Object> map = new HashMap<>();
        Page<UniversityMatter> pages = PageHelper.startPage(page,rows).doSelectPage(()->matterMapper.selectUniver());
        List<UniversityMatter> result = pages.getResult();
        //迭代查出的新闻，为其查询对应的多张图片（为什么要这么做，因为直接两个表关联查询会出bug）
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

    /**
     * 查询单个
     * @param mId
     * @return
     */
    @Override
    public UniversityMatter selectUniverOne(String mId) {

        UniversityMatter universityMatter = matterMapper.selectUniverOne(mId);
        UniversityUser universityUser = userMapper.userSelectById(universityMatter.getmUid());
        universityUser.setPassword(null);
        universityMatter.setUniversityUser(universityUser);
        return universityMatter;
    }

    /**
     * 根据种类查询新闻
     * @param page
     * @param rows
     * @param mKind
     * @return
     */
    @Override
    public Map<String,Object> selectUniverKind(Integer page,Integer rows,String mKind) {

        Map<String,Object> map = new HashMap<>();
        Page<UniversityMatter> pages = PageHelper.startPage(page,rows).doSelectPage(()->matterMapper.selectUniverKind(mKind));
        List<UniversityMatter> result = pages.getResult();

        Iterator<UniversityMatter> universityMatterIterator = result.iterator();
        while(universityMatterIterator.hasNext()){
            UniversityMatter universityMatter = universityMatterIterator.next();
            List<UniversityPicture> universityPictureList = matterMapper.selectUniverPicture(universityMatter.getmId());
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

    /**
     * 根据用户的id来查询它的发布
     * @param mUid
     * @return
     */
    @Override
    public Map<String, Object> selectUniverByUser(String mUid) {
        Map<String,Object> map = new HashMap<>();
        List<UniversityMatter> universityMatters = matterMapper.selectUniverByUser(mUid);
        Iterator<UniversityMatter> universityMatterIterator = universityMatters.iterator();
        while(universityMatterIterator.hasNext()){
            UniversityMatter universityMatter = universityMatterIterator.next();
            List<UniversityPicture> universityPictureList = matterMapper.selectUniverPicture(universityMatter.getmId());
            universityMatter.setUniversityPictureList(universityPictureList);
        }
        map.put("universityMatters",universityMatters);
        return map;
    }

    /**
     * 用户删除自己发布的作品
     * @param mId
     * @return
     */
    @Override
    public Map<String, Object> deleteUniverMatterById(String mId) {
        Map<String,Object> map = new HashMap<>();
        int i = matterMapper.deleteUniverMatterById(mId);
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
