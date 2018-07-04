package com.threebrother.qofood.controller.mng;

import com.threebrother.qofood.common.Constant;
import com.threebrother.qofood.common.MngRequestConstant;
import com.threebrother.qofood.common.exception.BusinessException;
import com.threebrother.qofood.entity.MngUser;
import com.threebrother.qofood.model.Result;
import com.threebrother.qofood.service.MngUserServier;
import com.threebrother.qofood.util.MapCache;
import com.threebrother.qofood.util.ResultUtil;
import com.threebrother.qofood.util.TaleUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/mng")
@ApiIgnore
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    MngUserServier mngUserServier;

    protected MapCache cache = MapCache.single();

    @GetMapping(value = "/login")
    public String loginPage(HttpServletRequest request) {
        return "mng/login";
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public Result doLogin(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam(required = false) String remeber_me,
                          HttpServletRequest request,
                          HttpServletResponse response) {


        Integer error_count = cache.get("login_error_count");

        // 这里实现登陆逻辑
        try {
            MngUser mngUser = mngUserServier.login(username, password);
            request.getSession().setAttribute(Constant.LOGIN_SESSION_KEY, mngUser);

            if (StringUtils.isNotBlank(remeber_me)) {
                TaleUtil.setCookie(response, mngUser.getMngUserId());
            }
            //TODO 这里实现登陆日志
            //logService.insertLog(LogActions.LOGIN.getAction(), null, request.getRemoteAddr(), user.getUid());
        } catch (Exception e) {
            e.printStackTrace();
            error_count = null == error_count ? 1 : error_count + 1;

            if (error_count > 3) {
                return ResultUtil.error(MngRequestConstant.NUMBER_OF_LANDINGS_IS_BEYOND_THE_LIMIT_CODE,
                        MngRequestConstant.NUMBER_OF_LANDINGS_IS_BEYOND_THE_LIMIT_MSG);
            }

            cache.set("login_error_count", error_count, 10 * 60);

            String msg = MngRequestConstant.LOGIN_FAILED_MSG;
            if (e instanceof BusinessException) {
                msg = e.getMessage();
            } else {
                LOGGER.debug(msg, e);
            }

            return ResultUtil.error(MngRequestConstant.LOGIN_FAILED_CODE, msg);
        }

        return ResultUtil.success();
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




}
