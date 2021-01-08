package com.twj.spirngbasics.server.user.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.twj.spirngbasics.server.entity.BaseEntity;

/**
* @作者: Jun
* @创建时间: 2021-01-05 17:27:49
* @Version 1.0
* @描述: 资源
*/
@Data
public class UserResource extends BaseEntity {

    /**
    * 名称|菜单或按钮
    */
    private String name;
    /**
    * 页面|路由
    */
    private String page;
    /**
    * 请求|接口
    */
    private String request;
    /**
    * 父id
    */
    private String parent;

}
