package com.twj.spirngbasics.server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @作者: Jun
 * @创建时间: 2021-01-06 15:35:31
 * @Version 1.0
 * @描述: 字典表 
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysDictDto {


    /**
    * 创建人
    */
    private String createdBy;

    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

    /**
    * 更新人
    */
    private String updateBy;

    /**
    * 更新时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
    * 删除
    */
    private String dele;

    /**
    * 备注
    */
    private String remake;

    /**
    * 备用1
    */
    private String spare1;

    /**
    * 编号
    */
    private String id;

    /**
    * 数据值
    */
    private String value;

    /**
    * 标签名
    */
    private String label;

    /**
    * 类型
    */
    private String type;

    /**
    * 描述
    */
    private String description;

    /**
    * 排序（升序）
    */
    private String sort;

}
