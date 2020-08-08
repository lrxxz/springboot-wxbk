package org.lrx.service.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.lrx.dao.CommentMapper;
import org.lrx.dao.UserMapper;
import org.lrx.entity.UniversityComment;
import org.lrx.entity.UniversityUser;
import org.lrx.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 实现评论上传
     * @param universityComment
     * @return
     */
    @Override
    public Map<String, Object> insertComment(UniversityComment universityComment) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        universityComment.setComTime(df.format(new Date()));
        Map<String,Object> map = new HashMap<>();
        int i = commentMapper.insertComment(universityComment);
        if(i<0){
            map.put("type","error");
            map.put("msg","评论失败");
            return map;
        }
        map.put("type","success");
        map.put("msg","评论成功");
        return map;
    }

    /**
     * 查询评论根据每个说说
     * @param comMid
     * @return
     */
    @Override
    public Map<String, Object> selectComment(String comMid,Integer page,Integer rows) {
        Map<String,Object> map = new HashMap<>();
        Page<UniversityComment> pages = PageHelper.startPage(page,rows).doSelectPage(()->commentMapper.selectComment(comMid));
        List<UniversityComment> result = pages.getResult();
        Iterator<UniversityComment> universityCommentIterator = result.iterator();
        while(universityCommentIterator.hasNext()){
            UniversityComment universityComment = universityCommentIterator.next();
            //查出属于他的用户
            UniversityUser universityUser = userMapper.userSelectById(universityComment.getComUid());
            universityUser.setPassword(null);
            universityComment.setUniversityUser(universityUser);
        }
        map.put("universityComments",result);
        map.put("page", pages.getPageNum());  //当前页
        map.put("pages",pages.getPages());    //总页数
        map.put("total",pages.getTotal());    //总数据量
        return map;
    }

    @Override
    public Map<String, Object> deleteComment(Integer comId) {
        Map<String,Object> map = new HashMap<>();
        int i = commentMapper.deleteComment(comId);
        if(i<0){
            map.put("type","error");
            map.put("msg","删除失败");
            return  map;
        }
        map.put("type","success");
        map.put("msg","删除成功");
        return map;
    }
}
