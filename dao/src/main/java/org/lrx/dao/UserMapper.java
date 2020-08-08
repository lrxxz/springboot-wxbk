package org.lrx.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.lrx.entity.UniversityUser;

@Mapper
public interface UserMapper {
    //注册用户
    int userInsert(UniversityUser universityUser);
    //查询单个用户，根据用户名
    UniversityUser userSelectByName(String username);
    //用户登录
    UniversityUser userSelect(@Param("username") String username,@Param("password") String password);
    //用户id查询
    UniversityUser userSelectById(String userId);
    //上传用户头像
    int userInsertPicture(@Param("userId") String userId,@Param("userPicture") String userPicture);
    //修改密码
    int userUpdatePassword(@Param("xPassword") String xPassword,@Param("username") String username);
    //更新用户信息
    int udateUser(UniversityUser universityUser);
}
