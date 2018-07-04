package com.threebrother.qofood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class ErrorController {

    @RequestMapping("/error/500")
    public String error500(){
        return "comm/error_500";
    }

    @RequestMapping("/error/404")
    public String error404(){
        return "comm/error_404";
    }

}
