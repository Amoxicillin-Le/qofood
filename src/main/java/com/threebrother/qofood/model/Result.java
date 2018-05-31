package com.threebrother.qofood.model;
/**  
 *http请求返回的最外层对象
 * @author zhaoxiaolezi
 * @date 2018/5/31 13:42  
 */
public class Result<T> {

    private String code;

    private String msg;

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
