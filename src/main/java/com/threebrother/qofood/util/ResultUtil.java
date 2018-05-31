package com.threebrother.qofood.util;

import com.threebrother.qofood.common.RequestConstant;
import com.threebrother.qofood.model.Result;

public class ResultUtil {

    public static Result success(Object object) {

        Result result = new Result();
        result.setCode(RequestConstant.REQUEST_SUCCESS_CODE);
        result.setMsg(RequestConstant.REQUEST_SUCCESS_MSG);
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(String code, String msg) {

        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
