package org.lrx;

import org.junit.jupiter.api.Test;
import org.lrx.controller.CommentController;
import org.lrx.controller.MatterController;
import org.lrx.controller.UniversityController;
import org.lrx.controller.UserController;
import org.lrx.dao.SearchMapper;
import org.lrx.entity.UniversityComment;
import org.lrx.entity.UniversityMatter;
import org.lrx.service.CommentService;
import org.lrx.service.LikeService;
import org.lrx.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ControllerApplicationTests {

    @Autowired
    UniversityController universityController;

    @Test
    void contextLoads() {
        Map<String, Object> map = universityController.selectUniverMessage(1, 1);
        System.out.println(map);
    }

}
