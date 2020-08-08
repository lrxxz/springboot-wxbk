package org.lrx.service;

import org.lrx.entity.UniversityUser;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {
    //注册用户
    Map<String,Object> userInsert(UniversityUser universityUser);
    Map<String,Object> userSelectByName(UniversityUser universityUser, HttpServletRequest request);
    //上传头像
    Map<String,Object> userInsertPicture(String userId,String userPicture);
    //修改用户的密码
    Map<String,Object> userUpdatePassword(String username,String password,String xPassword);
}
