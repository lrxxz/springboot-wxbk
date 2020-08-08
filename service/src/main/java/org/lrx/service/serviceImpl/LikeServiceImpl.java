package org.lrx.service.serviceImpl;

import org.lrx.dao.LikeMapper;
import org.lrx.dao.MatterMapper;
import org.lrx.dao.UserMapper;
import org.lrx.entity.UniversityLike;
import org.lrx.entity.UniversityMatter;
import org.lrx.entity.UniversityUser;
import org.lrx.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    LikeMapper likeMapper;

    @Autowired
    MatterMapper matterMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 插入收藏
     * @param universityLike
     * @return
     */
    @Override
    public Map<String, Object> likeInsert(UniversityLike universityLike) {
        Map<String,Object> map = new HashMap<>();
        int num = 0;
        List<UniversityLike> universityLikes = likeMapper.likeSelect(universityLike.getLikeUid());
        for (UniversityLike like:universityLikes){
           if (like.getLikeMid().equals(universityLike.getLikeMid())){
               num = 1;
               break;
           }
        }
        if (num == 0) {
            int i = likeMapper.likeInsert(universityLike);
            if (i < 0) {
                map.put("type", "error");
                map.put("msg", "收藏失败");
                return map;
            }else {
                map.put("type", "success");
                map.put("msg", "收藏成功");
                return map;
            }
        }else {
            map.put("type","error");
            map.put("msg","你已收藏该作品");
            return map;
        }
    }

    /**
     * 根据用户的id查询用户的收藏
     * @param likeUid
     * @return
     */
    @Override
    public Map<String, Object> likeSelect(String likeUid) {
        Map<String,Object> map = new HashMap<>();
        List<UniversityLike> universityLikes = likeMapper.likeSelect(likeUid);
        Iterator<UniversityLike> UniversityLikeIterator = universityLikes.iterator();
        while(UniversityLikeIterator.hasNext()){
            UniversityLike universityLike = UniversityLikeIterator.next();
            UniversityMatter universityMatter = matterMapper.selectUniverOne(universityLike.getLikeMid());
            UniversityUser universityUser = userMapper.userSelectById(universityMatter.getmUid());
            universityLike.setUniversityMatter(universityMatter);
            universityLike.setUniversityUser(universityUser);
        }
        map.put("universityLikes",universityLikes);
        return map;
    }

    /**
     * 删除收藏
     * @param likeMid
     * @return
     */
    @Override
    public Map<String, Object> likeDelete(String likeMid) {
        Map<String ,Object> map = new HashMap<>();
        int i = likeMapper.likeDelete(likeMid);
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
