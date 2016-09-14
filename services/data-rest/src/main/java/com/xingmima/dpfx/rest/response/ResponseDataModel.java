/*
    Copyright (c) 2014, Felix
    All rights reserved.
 */
package com.xingmima.dpfx.rest.response;

import java.io.Serializable;

/**
 * @corporation 星密码
 * @author bojue
 * @date 2016年7月12日 上午10:11:09
 * @description 响应结果数据对象s
 */
public class ResponseDataModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * <p>Title: </p>
     * <p>Description: </p>
     */
    public ResponseDataModel() {}
    
    public ResponseDataModel(ApiStatusCode statusCode) {
        this.code = statusCode.code();
        this.msg = statusCode.msg();
    }
    
    public ResponseDataModel(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public ResponseDataModel(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    /**
      * @Fields code : 接口返回状态码
      */
    private String code ; 
    
    /**
     * @Fields msg : 接口返回消息
     */
    private String msg;
    
    /**
      * @Fields data : 返回数据
      */
    private Object data ;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    } 
}
