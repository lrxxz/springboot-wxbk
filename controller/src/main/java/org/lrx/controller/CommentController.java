package org.lrx.controller;

import org.lrx.entity.UniversityComment;
import org.lrx.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     * 留言
     * @param universityComment
     * @return
     */
    @PostMapping("/commentInsert")
    @ResponseBody
    public Map<String,Object> commentInsert(@RequestBody UniversityComment universityComment){
        return commentService.insertComment(universityComment);
    }

    /**
     * 查询留言
     * @return
     */
    @ResponseBody
    @PostMapping("/selectComment")
    public Map<String,Object> selectComment(@RequestParam("comMid") String comMid,@RequestParam("page") Integer page,@RequestParam("rows") Integer rows){
        return commentService.selectComment(comMid,page,rows);
    }

    @ResponseBody
    @GetMapping("/deleteComment")
    public Map<String,Object> deleteComment(@RequestParam("comId") Integer comId){
        return commentService.deleteComment(comId);
    }
}
