package com.threebrother.qofood.model;

import io.swagger.annotations.ApiModelProperty;

/**
 *http请求返回的最外层对象
 * @author zhaoxiaolezi
 * @date 2018/5/31 13:42
 */
public class Result<T> {

    @ApiModelProperty(value = "返回码", name = "code", example = "0000-成功； xxxx-失败")
    private String code;

    @ApiModelProperty(value = "返回信息", name = "msg", example = "success-成功； xxxx-失败信息")
    private String msg;

    @ApiModelProperty(value = "返回数据", name = "data", example = "部分接口返回操作数据")
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
