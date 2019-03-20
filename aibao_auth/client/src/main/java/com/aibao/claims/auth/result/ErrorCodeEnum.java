package com.aibao.claims.auth.result;

/**
 * 错误异常CODE定义
 */
public enum ErrorCodeEnum {
    RC_0000(200, "SUCCESS"),
    RC_500(500, "服务器发生异常"),
    RC_400(400, "系统网络异常，暂时无法服务"),
    RC_1002(1002, "请求内容不合法，有字段为空"),
    RC_1003(1003, "上传文件异常"),
    RC_1004(1004, "VIN码识别为空"),
    RC_1005(1005, "阿里云识别暂时无法提供服务，请稍后再试"),
    RC_1006(1006, "获取邦邦授权码失败！"),
    RC_1007(1007, "未获取到车辆信息！"),
    RC_1008(1008, "行驶证识别失败！"),
    RC_1009(1009, "识别车辆受损部位失败！"),
    RC_1010(1010, "未发现车辆受损部位！"),
    RC_1011(1011, "获取短信验证码失败！"),
    RC_1012(1012, "短信验证码校验失败！"),
    RC_1013(1013, "验证码失效或输入错误！"),
    RC_1014(1014, "未获取到待消费信息！"),
    RC_1015(1015, "登陆账号不存在！"),
    RC_1016(1016, "密码错误！"),
    RC_1017(1017, "坐席id不能为空！"),
    RC_1018(1018, "待接入！"),
    RC_1019(1019, "坐席登录信息不存在！或密码错误！"),
    RC_1020(1020, "会话id不能为空！"),
    RC_1021(1021, "发送内容不能为空！"),
    RC_1022(1022, "登陆状态过期！"),
    RC_1023(1023, "案件已被绑定，不可重复绑定！"),
    RC_1024(1024, "当前案件已失效无法发起视频，请联系理赔员重新预约！"),
    RC_1025(1025, "非法案件ID"),
    RC_1026(1026, "坐席空闲,监听会话中......！"),
    RC_1027(1027, "用户呼叫过程中挂断！"),
    RC_1028(1028, "用户报案号已被绑定，暂未失效！"),
    RC_1029(1029, "校验坐席信息失败！"),
    RC_1030(1030, "获取案件信息失败！"),
    RC_1031(1031, "获取短链失败！"),
    RC_1032(1032, "手机号不存在白名单！"),
    RC_1033(1033, "当前查询结果数据为空"),
    RC_1034(1034, "当前查询数据量过大"),;

    /**
     * 枚举的值
     */
    private int code;

    /**
     * 枚举的中文描述
     */
    private String desc;


    public static String getDesc(long code) {
        for (ErrorCodeEnum b : ErrorCodeEnum.values()) {
            if (b.code == code) {
                return b.desc;
            }
        }
        return "";
    }


    /**
     * 匹配提示码 未匹配的返回错误码1000
     *
     * @param code
     * @return
     */
    public static ErrorCodeEnum get(int code) {
        for (ErrorCodeEnum b : ErrorCodeEnum.values()) {
            if (b.code == code) {
                return b;
            }
        }
        return null;
    }


    private ErrorCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public int getCode() {
        return code;
    }


    public String getDesc() {
        return desc;
    }


}
