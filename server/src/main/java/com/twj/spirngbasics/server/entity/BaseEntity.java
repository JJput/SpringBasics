package com.twj.spirngbasics.server.entity;

import com.twj.spirngbasics.server.manage.UserManage;
import com.twj.spirngbasics.server.util.Constant;
import com.twj.spirngbasics.server.util.UuidUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.Generated;
import java.util.Date;

/**
 * @作者: JJ
 * @创建时间: 2020/7/21 下午5:39
 * @Version 1.0
 * @描述:
 */
@Data
public class BaseEntity {

    //使用
    public static final String DELE_USE = "1";
    //删除
    public static final String DELE_YES = "0";

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    protected String id;

    @ApiModelProperty(value = "创建对象")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    protected String createdBy;

    @ApiModelProperty(value = "创建时间")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    protected Date createdTime;

    @ApiModelProperty(value = "更新对象")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    protected String updateBy;

    @ApiModelProperty(value = "更新时间")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    protected Date updateTime;

    @ApiModelProperty(value = "是否删除")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    protected String dele;

    public void insert(String userId) {
        Date date = new Date();
        this.id = UuidUtils.getUuid32();
        this.createdBy = userId;
        this.createdTime = date;
        this.updateBy = userId;
        this.updateTime = date;
        this.dele = DELE_USE;
    }

    public void insert() {
        Date date = new Date();
        this.id = UuidUtils.getUuid32();
        this.createdBy = UserManage.getUserId();
        this.createdTime = date;
        this.updateBy = UserManage.getUserId();
        this.updateTime = date;
        this.dele = DELE_USE;
    }

    public void insertSystem() {
        Date date = new Date();
        this.id = UuidUtils.getUuid32();
        this.createdBy = Constant.SYSTEM_USER;
        this.createdTime = date;
        this.updateBy = Constant.SYSTEM_USER;
        this.updateTime = date;
        this.dele = DELE_USE;
    }

    public void insert(String id, Date date) {
        this.id = id;
        this.createdBy = UserManage.getUserId();
        this.createdTime = date;
        this.updateBy = UserManage.getUserId();
        this.updateTime = date;
        this.dele = DELE_USE;
    }

    public void del() {
        this.dele = DELE_YES;
    }

    public void update() {
        this.updateBy = UserManage.getUserId();
        this.updateTime = new Date();
    }

    public void update(String userId) {
        this.updateBy = userId;
        this.updateTime = new Date();
    }
}
