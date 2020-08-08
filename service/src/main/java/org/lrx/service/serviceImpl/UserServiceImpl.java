package org.lrx.service.serviceImpl;

import org.lrx.dao.UserMapper;
import org.lrx.entity.UniversityUser;
import org.lrx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    /**
     * 用户注册
     * @param universityUser
     * @return
     */
    @Override
    public Map<String,Object> userInsert(UniversityUser universityUser) {
        Map<String,Object> map = new HashMap<>();
        UniversityUser universityUser1 = userMapper.userSelectByName(universityUser.getUsername());
        if (universityUser1 == null){
            int i = userMapper.userInsert(universityUser);
            if(i<0){
                map.put("type","error");
                map.put("msg","注册失败");
                return map;
            }else {
                map.put("type","success");
                map.put("msg","注册成功");
                return map;
            }
        }else {
            map.put("type","error");
            map.put("msg","该用户已存在");
            return map;
        }
    }

    /**
     * 用户登录
     * @param universityUser
     * @return
     */
    @Override
    public Map<String, Object> userSelectByName(UniversityUser universityUser,HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        UniversityUser universityUser1 = userMapper.userSelectByName(universityUser.getUsername());
        if(universityUser1 == null){
            map.put("type","error");
            map.put("msg","用户不存在");
            return map;
        }else {
            UniversityUser universityUser2 = userMapper.userSelect(universityUser.getUsername(), universityUser.getPassword());
            if(universityUser2==null){
                map.put("type","error");
                map.put("msg","密码错误");
                return map;
            }else{
                universityUser2.setPassword(null);
                map.put("type","success");
                map.put("msg","登录成功");
                map.put("universityUser",universityUser2);
                //保存登陆成功后的用户id，可以用redis来实现
                request.getSession().setAttribute("universityUser"+universityUser2.getUserId(),universityUser2.getUserId());
                //System.out.println(request.getSession().getAttribute("universityUser"+universityUser2.getUserId()));
                return map;
            }
        }
    }

    /**
     * 上传用户照片
     * @param userId
     * @param userPicture
     * @return
     */
    @Override
    public Map<String, Object> userInsertPicture(String userId, String userPicture) {
        Map<String,Object> map = new HashMap<>();
        int i = userMapper.userInsertPicture(userId, userPicture);
        if (i<0){
            map.put("type","error");
            map.put("msg","上传失败");
            return map;
        }
        map.put("type","success");
        map.put("msg","上传成功");
        map.put("url",userPicture);
        return map;
    }

    /**
     * 修改密码
     * @param username
     * @param password
     * @param xPassword
     * @return
     */
    @Override
    public Map<String, Object> userUpdatePassword(String username, String password, String xPassword) {
        Map<String, Object> map = new HashMap<>();
        UniversityUser universityUser = userMapper.userSelect(username, password);
        if(universityUser == null){
            map.put("type","error");
            map.put("msg","账号密码输入有误");
            return  map;
        }
        int i = userMapper.userUpdatePassword(xPassword, username);
        map.put("type","success");
        map.put("msg","密码修改成功，下次登录生效");
        return map;
    }


}
