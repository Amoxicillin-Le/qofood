package com.threebrother.qofood.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.threebrother.qofood.common.RequestConstant;
import com.threebrother.qofood.common.exception.BusinessException;
import com.threebrother.qofood.interceptor.MngInterceptor;
import com.threebrother.qofood.model.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Resource
    private MngInterceptor mngInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mngInterceptor);
        super.addInterceptors(registry);
    }

    //重写configureMessageConverters 消息转换器 更换为阿里的FastJosn
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        fastJsonConfig.setSerializerFeatures();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteDateUseDateFormat);

        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastJsonHttpMessageConverter);
    }


    /**
     * 用于传递到页面的值
     */
    protected Map<String, Object> data = new HashMap<String, Object>();

    // 统一异常处理
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

                // 打印日志
                e.printStackTrace();
                Result result = new Result();

                if(o instanceof HandlerMethod){

                    HandlerMethod handlerMethod = (HandlerMethod) o;

                    // 自定义的业务异常
                    if (e instanceof BusinessException) {
                        String requestType = httpServletRequest.getHeader("X-Requested-With");
                        if("XMLHttpRequest".equals(requestType)){
                            System.out.println("AJAX请求..");
                        }else{
                            System.out.println("非AJAX请求..");
                            String uri = httpServletRequest.getRequestURI();
                            System.out.println("请求路径：" + uri);

                            if (uri.startsWith("/mng")){
                                ModelAndView modelAndView = new ModelAndView("comm/error_500");
                                return modelAndView;
                            }
                        }

                        result.setCode(((BusinessException) e).getCode());
                        result.setMsg(e.getMessage());
                    }else{
                        result.setCode(RequestConstant.REQUEST_BUSINESS_EXCEPTION_CODE);
                        result.setMsg(e.getMessage());
                    }

                }else{

                    if (e instanceof NoHandlerFoundException) {
                        result.setCode(RequestConstant.REQUEST_BUSINESS_EXCEPTION_CODE);
                        result.setMsg(e.getMessage());
                    } else {
                        result.setCode(RequestConstant.REQUEST_BUSINESS_EXCEPTION_CODE);
                        result.setMsg(e.getMessage());
                    }
                }

                responseResult(httpServletResponse, result);
                return new ModelAndView();
            }
        });
    }

    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
        }
    }
}










