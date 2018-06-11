package com.threebrother.qofood.controller.mng;

import com.threebrother.qofood.util.Commons;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/mng")
public class IndexController {

    @Resource
    private Commons commons;

    @GetMapping(value = "/loginPage")
    public String loginPage(HttpServletRequest
                                    request) {
        request.setAttribute("commons", commons);//一些工具类和公共方法

        return "mng/login";
    }

    /**
     * 页面跳转
     * @return
     */
    @GetMapping(value = {"", "/index"})
    public String index(HttpServletRequest request){
//        List<CommentVo> comments = siteService.recentComments(5);
//        List<ContentVo> contents = siteService.recentContents(5);
//        StatisticsBo statistics = siteService.getStatistics();
//        取最新的20条日志
//        List<LogVo> logs = logService.getLogs(1, 5);
//        request.setAttribute("comments", comments);
//        request.setAttribute("articles", contents);
//        request.setAttribute("statistics", statistics);
//        request.setAttribute("logs", logs);

        return "mng/index";
    }


    /**
     * 登陆函数
     * TODO 目前展示不实现
     * @author zhaoxiaolezi
     * @date 2018/6/11 18:09
     */
    @GetMapping(value = "/login")
    public String doLogin(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam(required = false) String remeber_me,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        request.setAttribute("commons", commons);//一些工具类和公共方法

        return "mng/login";
    }

}
