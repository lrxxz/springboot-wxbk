package org.lrx.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.lrx.dao.UserMapper;
import org.lrx.entity.UniversityUser;
import org.lrx.service.FileService;
import org.lrx.service.UserService;
import org.lrx.until.AesCbcUtil;
import org.lrx.until.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    AesCbcUtil aesCbcUtil;

    @Autowired
    HttpRequest httpRequest;

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @Autowired
    UserMapper userMapper;

    /**
     * 用户的注册
     * @param universityUser
     * @return
     */
    @PostMapping("/userInsert")
    @ResponseBody
    public Map<String,Object> userInsert(@RequestBody UniversityUser universityUser){
        universityUser.setUserId(RandomStringUtils.randomAlphanumeric(6));
       return userService.userInsert(universityUser);
    }

    /**
     * 用户的登录
     * @param universityUser
     * @return
     */
    @PostMapping("/userSelectByName")
    @ResponseBody
    public Map<String,Object> userSelectByName(@RequestBody UniversityUser universityUser,HttpServletRequest request){
        return userService.userSelectByName(universityUser,request);
    }

    /**
     * 用户注销
     * @param userId
     * @param request
     * @return
     */
    @PostMapping("/userLogout")
    @ResponseBody
    public Map<String,Object> userLogout(String userId,HttpServletRequest request ){
        Map<String,Object> map = new HashMap<>();
        request.getSession().setAttribute("universityUser"+ userId,null);
        //System.out.println(request.getSession().getAttribute("universityUser"+ userId));
        map.put("msg","注销成功");
        return map;
    }

    /**
     * 上传用户图片
     * @param userId
     * @param file
     * @return
     */
    @PostMapping("/userInsertPicture")
    @ResponseBody
    public Map<String,Object> userInsertPicture(@RequestParam("userId") String userId,@RequestParam("file") MultipartFile file){
        Map<String,Object> map = new HashMap<>();
        String uploadPathDB = fileService.uploadImg(file);
        if (uploadPathDB.isEmpty()) {
            map.put("type", "fail");
            map.put("msg", "文件上传出错");
            return map;
        }
        return userService.userInsertPicture(userId,uploadPathDB);
    }

    /**
     * 修改密码
     * @param username
     * @param password
     * @param xPassword
     * @return
     */
    @PostMapping("/userUpdatePassword")
    @ResponseBody
    public Map<String,Object> userUpdatePassword(@RequestParam("username") String username,
                                                 @RequestParam("password") String password,
                                                 @RequestParam("xPassword") String xPassword){
        return userService.userUpdatePassword(username,password,xPassword);
    }

    @PostMapping("/wxTo")
    @ResponseBody
    public Map<String,Object> wxTo(String encryptedData, String iv, String code) throws JSONException {
        Map<String,Object> map = new HashMap<>();
        // 登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }

        // 小程序唯一标识 (在微信小程序管理后台获取)
        String wxspAppid = "wx2d4eb6db996d8132";
        // 小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "664dd0bbf63a792b71e8f324740d1c71";
        // 授权（必填）
        String grant_type = "authorization_code";

        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        //////////////// ////////////////
        // 请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type="
                + grant_type;
        // 发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        // 解析相应内容（转换成json对象）
        JSONObject json = new JSONObject(sr);
        // 获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        // 用户的唯一标识（openid）
        String openid = (String) json.get("openid");

        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");

                JSONObject userInfoJSON = new JSONObject(result);
//                Map userInfo = new HashMap();
//                userInfo.put("openId", userInfoJSON.get("openId"));
//                userInfo.put("nickName", userInfoJSON.get("nickName"));
//                userInfo.put("gender", userInfoJSON.get("gender"));
//                userInfo.put("city", userInfoJSON.get("city"));
//                userInfo.put("province", userInfoJSON.get("province"));
//                userInfo.put("country", userInfoJSON.get("country"));
//                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                // 解密unionId & openId;
                UniversityUser universityUser = new UniversityUser();
                universityUser.setUserId(userInfoJSON.get("openId").toString());
                universityUser.setUsername(userInfoJSON.get("nickName").toString());
                universityUser.setUserPicture(userInfoJSON.get("avatarUrl").toString());
                UniversityUser universityUser1 = userMapper.userSelectById(universityUser.getUserId());
                if(universityUser1 == null){
                    userService.userInsert(universityUser);
                }else {
                    userMapper.udateUser(universityUser);
                }
                map.put("universityUser", universityUser);
            } else {
                map.put("status", 0);
                map.put("msg", "解密失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }       	return map;
    }

}
