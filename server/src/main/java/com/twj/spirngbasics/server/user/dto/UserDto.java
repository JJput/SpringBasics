package com.twj.spirngbasics.server.user.dto;

import lombok.Data;

import java.util.HashSet;

@Data
public class UserDto {


    /**
     * 账号ID
     */
    private String id;

    /**
     * 公司ID
     */
    private String companyId;

    /**
     * 电话
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 姓名
     */
    private String name;

    /**
     * 状态
     */
    private String status;

    /**
     * 角色
     */
    private String roleId;

    private String code;

    private String token;

    private HashSet requests;

}
