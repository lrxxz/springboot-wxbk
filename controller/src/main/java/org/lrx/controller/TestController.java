package org.lrx.controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class TestController {

    @GetMapping("/hello")
    public String test(){
        return "hello,springwxbk";
    }




}
