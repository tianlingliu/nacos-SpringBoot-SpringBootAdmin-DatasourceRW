
package com.aibao.claims.auth.result;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Setter;

import java.io.Serializable;

/**
 * 返回数据
 */
@Setter
@JsonSerialize
public class R<T> implements Serializable {
    private static final long serialVersionUID = 57879798981L;

    @JsonSerialize
    private boolean success = true;
    @JsonSerialize
    private int code;
    @JsonSerialize
    private String msg;
    @JsonSerialize
    private T data;


    public R() {
        this.code = ErrorCodeEnum.RC_0000.getCode();
        this.msg = ErrorCodeEnum.RC_0000.getDesc();
    }

    public R(ErrorCodeEnum... r) {

        if (r != null && r.length > 0) {
            success = false;

            this.code = r[0].getCode();
            this.msg = r[0].getDesc();
        } else {
            this.code = ErrorCodeEnum.RC_0000.getCode();
            this.msg = ErrorCodeEnum.RC_0000.getDesc();
        }

    }

    public R<T> setData(T t) {
        this.data = t;
        return this;
    }


    public static R error(ErrorCodeEnum e) {
        return new R(e);
    }


    public static R error(ErrorCodeEnum e, Object... msg) {
        R r = new R(e);
        r.setData(JSON.toJSONString(msg));
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.setData(data);
        return r;
    }

    public static R ok() {
        return new R();
    }

}
