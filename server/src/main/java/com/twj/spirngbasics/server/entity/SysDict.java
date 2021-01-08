package com.twj.spirngbasics.server.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.twj.spirngbasics.server.entity.BaseEntity;

/**
 * @作者: Jun
 * @创建时间: 2021-01-07 16:51:13
 * @Version 1.0
 * @描述: 字典表 
 */
@Data
public class SysDict extends BaseEntity {

    /**
    * 备注
    */
    private String remake;
    /**
    * 备用1
    */
    private String spare1;
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
