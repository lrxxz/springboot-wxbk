package org.lrx.controller;

import org.lrx.entity.UniversityMatter;
import org.lrx.entity.UniversityPicture;
import org.lrx.service.FileService;
import org.lrx.service.MatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/wxbk")
public class MatterController {

    @Autowired
    MatterService matterService;

    @Autowired
    FileService fileService;


    /**
     * 照片的上传
     * @param file
     * @param pMid
     * @return
     */
    @PostMapping("/fileInsert")
    @ResponseBody
    public Map<String, Object> fileIndex(@RequestParam("pMid") String pMid,@RequestParam("file") MultipartFile[] file) {

        List<UniversityPicture> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for (MultipartFile multipartFile : file) {
            String uploadPathDB = fileService.uploadImg(multipartFile);
            if (uploadPathDB.isEmpty()) {
                map.put("type", "fail");
                map.put("msg", "文件上传出错");
                return map;
            } else {
                UniversityPicture universityPicture = new UniversityPicture();
                universityPicture.setpMid(pMid);
                universityPicture.setpAdress(uploadPathDB);
                list.add(universityPicture);
            }
        }
        int i = fileService.insertFile(list);
        if(i<0){
            map.put("type", "fail");
            map.put("msg", "文件存入数据库出错");
            return map;
        }
        map.put("type", "success");
        map.put("msg", "文件上传成功");
        return map;
    }


    /**
     * 新闻插入
     * @param universityMatter
     * @return
     */
    @ResponseBody
    @PostMapping("/matterInsert")
    public Map<String,Object> matterInsert(@RequestBody UniversityMatter universityMatter){
        return matterService.matterInsert(universityMatter);
    }

    /**
     * 新闻的查询
     * @return
     */
    @ResponseBody
    @GetMapping("/selectUniver/{page}/{rows}")
    public Map<String,Object> selectUniver(@PathVariable("page") Integer page,@PathVariable("rows") Integer rows){
        return matterService.selectUniver(page,rows);
    }

    /**
     * 查询单个新闻详情
     * @param mId
     * @return
     */
    @ResponseBody
    @GetMapping("/selectUniverOne/{mId}")
    public Map<String,Object> selectUniverOne(@PathVariable("mId") String mId){
        Map<String,Object> map = new HashMap<>();
        UniversityMatter universityMatter = matterService.selectUniverOne(mId);
        map.put("universityMatter",universityMatter);
        return map;
    }

    /**
     * 根据新闻的种类查询
     * @return
     */
    @ResponseBody
    @GetMapping("/selectUniverKind/{mKind}/{page}/{rows}")
    public Map<String,Object> selectUniverKind(@PathVariable("mKind") String mKind,@PathVariable("page") Integer page,@PathVariable("rows") Integer rows){
        return matterService.selectUniverKind(page,rows,mKind);
    }

    /**
     * 根据用户的id来查询它的发布
     * @return
     */
    @ResponseBody
    @GetMapping("/selectUniverByUser/{mUid}")
    public Map<String,Object> selectUniverByUser(@PathVariable("mUid") String mUid){
        return matterService.selectUniverByUser(mUid);
    }

    /**
     * 用户删除自己发布的作品
     * @return
     */
    @ResponseBody
    @GetMapping("/deleteUniverMatterById/{mId}")
    public Map<String,Object> deleteUniverMatterById(@PathVariable("mId") String mId){
        return matterService.deleteUniverMatterById(mId);
    }
}
