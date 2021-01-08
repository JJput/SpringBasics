package com.twj.spirngbasics.server.util;


import com.twj.spirngbasics.server.dto.ResponseDto;

/**
 * @author Airness
 * @date 2019/12/20 12:05
 * @description TODO
 * 常量
 */
public class Constant {

    public static class Http {

        public enum SUCCESS implements ResponseDto.ResponseCode {
            DEFAULT(200, "成功"),

            ;
            private int code;
            private String message;

            SUCCESS(int i, String s) {
                this.code = i;
                this.message = s;
            }

            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return message;
            }
        }

        /**
         * 命名规范要求!!
         * 业务名_具体错误对象
         * 业务名_类型_错误对象
         */
        public enum ERROR implements ResponseDto.ResponseCode {
            DEFAULT(44444, "未知错误"),

            //500-1000登录注册相关
            LOGIN_USER_OR_PWD_ERROR(500,"用户名或密码错误"),
            LOGIN_NOT_REGISTER(501,"未注册"),
            REGISTER_FAIL(530, "注册失败"),
            REGISTER_EXISTS(531, "该账户已注册"),
            WECHAT_NO_REGISTER(551, "该微信用户未注册"),
            WECHAT_CODE_ERROR(552, "微信小程序的code已失效"),
            WECHAT_BINDING(553, "已成功绑定微信"),
            WECHAT_OTHER_BINDING(554, "该手机已被其他微信用户绑定"),
            WECHAT_REGISTER_TOKEN_ERROR(556, "非法请求"),


            TOKEN_NO(900, "token不存在"),
            TOKEN_IS_NULL(901, "token为空"),
            TOKEN_ERROR(902, "token异常:解析用户数据错误"),
            //1000-2000     通用错误
            OBJECT_IS_NULL(1000, ""),
            LENGTH_OVERRUN(1001, ""),
            OBJECT_NULL(1002, "对象为空"),
            VALUE_ERROR(1003, "值有误"),
            PICTURE_NULL(1004, "图片为空"),


            PHONE_NUMBER_ERROR(1020, "手机号有误"),
            PHONE_AREA_ERROR(1021, "区号异常"),
            PHONE_SENDED_CODE(1022, "已发送验证码，请勿重复发送！"),
            PHONE_NOT_SEND_CODE(1023, "未发送验证码，请发送验证码后再提交"),
            PHONE_CODE_ERROR(1024, "验证码有误"),
            PHONE_CODE_IS_NULL(1025,"验证码为空"),

            ACCESS_DENIED(1444, "访问被拒绝"),       //一般是横向越权等
            NOT_YET_OPEN(1445, "暂未开放"),
            SQL_NO_SELECT(1501, "未找到"),
            SQL_UPDATE_FAIL(1502, "未能成功更新"),
            SQL_UPDATE_OBJECT_IS_NULL(1503, "更新对象不存在"),
            PAGER_INFO_ERROR(1801, "分页参数错误"),

            ;



            private int code;
            private String message;

            ERROR(int i, String s) {
                this.code = i;
                this.message = s;
            }

            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return message;
            }
        }


    }

    public static final String SYSTEM_USER = "SYSTEM";

    /**
     * 字典类型
     */
    public static class Dict {


    }



}
