package cn.william.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by william on 2017/1/10.
 * 页面跳转controller
 */
@Controller
public class PageController {

    /**
     * 打开首页
     */
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    /**
     * 展示其他页面
     * @param page
     * @return
     */
    @RequestMapping("/{page}")
    public String showpage(@PathVariable String page){
        return page;
    }

}
