package org.lrx.controller;

import org.lrx.entity.University;
import org.lrx.entity.UniversityPicture2;
import org.lrx.service.FileService;
import org.lrx.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    UniversityService universityService;

    @Autowired
    FileService fileService;


    /**
     * 大学照片的上传
     * @param file
     * @param universityId
     * @return
     */
    @PostMapping("/insertUniversityFile")
    @ResponseBody
    public Map<String, Object> insertUniversityFile(@RequestParam("universityId") String universityId, @RequestParam("file") MultipartFile[] file) {

        List<UniversityPicture2> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for (MultipartFile multipartFile : file) {
            String uploadPathDB = fileService.uploadImg(multipartFile);
            if (uploadPathDB.isEmpty()) {
                map.put("type", "fail");
                map.put("msg", "文件上传出错");
                return map;
            } else {
                UniversityPicture2 universityPicture2 = new UniversityPicture2();
                universityPicture2.setUniversityId(universityId);
                universityPicture2.setUniversityPicture(uploadPathDB);
                list.add(universityPicture2);
            }
        }
        int i = fileService.insertFile2(list);
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
     * 大学信息插入
     * @param university
     * @return
     */
    @ResponseBody
    @PostMapping("/universityInsert")
    public Map<String,Object> universityInsert(@RequestBody University university){
        return universityService.universityInsert(university);
    }

    /**
     * 大学信息的查询
     * @return
     */
    @ResponseBody
    @GetMapping("/selectUniverMessage/{page}/{rows}")
    public Map<String,Object> selectUniverMessage(@PathVariable("page") Integer page,@PathVariable("rows") Integer rows){
        return universityService.selectUniverMessage(page,rows);
    }

    /**
     * 大学信息的删除
     * @param universityId
     * @return
     */
    @ResponseBody
    @GetMapping("/deleteUniversity/{universityId}")
    public Map<String,Object> deleteUniversity(@PathVariable("universityId") String universityId){
        return universityService.deleteUniversity(universityId);
    }
}
