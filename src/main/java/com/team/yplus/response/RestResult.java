package com.team.yplus.response;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * api 接口返回结果封装
 * @author lqc
 */
@Data
public class RestResult implements Serializable {
    private String msg;
    private int code;
    private Object data;

    public static RestResult succ(Object data){
        return succ(200,"操作成功",data);

    }

    public static RestResult succ( int code,String msg, Object data) {
        RestResult restResult = new RestResult();
        restResult.setCode(code);
        restResult.setMsg(msg);
        restResult.setData(data);
        return restResult;
    }
    public static RestResult succ(ResultCode resultCode) {
        RestResult restResult = new RestResult();
        restResult.setCode(resultCode.getCode());
        restResult.setMsg(resultCode.getMessage());
        restResult.setData("");
        return restResult;
    }
    public static RestResult fail(String msg){
        return fail(400,msg,null);
    }
    public static RestResult fail(String msg,Object data){
        return fail(400,msg,data);
    }

    public static RestResult fail(int code,String msg,Object data){
        RestResult restResult = new RestResult();
        restResult.setCode(code);
        restResult.setMsg(msg);
        restResult.setData(data);
        return restResult;
    }
    public static RestResult fail(ResultCode resultCode){
        RestResult restResult = new RestResult();
        restResult.setCode(resultCode.getCode());
        restResult.setMsg(resultCode.getMessage());
        restResult.setData("");
        return restResult;
    }

    public static RestResult newInstance(){
        return new RestResult();
    }


    public void put(String key,Object data){
        if(this.data==null) {
            this.data = new HashMap<String,Object>();
        }
        ((Map)this.data).put(key,data);
    }

}
