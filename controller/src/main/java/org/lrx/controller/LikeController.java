package org.lrx.controller;

import org.lrx.entity.UniversityLike;
import org.lrx.service.LikeService;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/like")
public class LikeController {

    @Autowired
    LikeService likeService;

    @PostMapping("/likeInsert")
    @ResponseBody
    public Map<String,Object> likeInsert(@RequestBody UniversityLike universityLike){
        return likeService.likeInsert(universityLike);
    }

    @GetMapping("/likeSelect/{likeUid}")
    @ResponseBody
    public Map<String,Object> likeSelect(@PathVariable("likeUid") String likeUid){
        return likeService.likeSelect(likeUid);
    }

    @GetMapping("/likeDelete/{likeMid}")
    @ResponseBody
    public Map<String,Object> likeDelete(@PathVariable("likeMid") String likeMid){
        return likeService.likeDelete(likeMid);
    }
}
