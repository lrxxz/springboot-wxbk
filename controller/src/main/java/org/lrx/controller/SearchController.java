package org.lrx.controller;

import org.lrx.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    /**
     * 查询全部热搜词
     * @return
     */
    @GetMapping("/selectSearch")
    @ResponseBody
    public Map<String ,Object> selectSearch(){
        return searchService.selectSearch();
    }

    @ResponseBody
    @GetMapping("/selectSearchByContent/{searchContent}/{page}/{rows}")
    public Map<String,Object> selectUniver(@PathVariable("searchContent") String searchContent, @PathVariable("page") Integer page, @PathVariable("rows") Integer rows){
        return searchService.selectSearchByContent(searchContent,page,rows);
    }
}
