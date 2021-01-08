package com.twj.spirngbasics.server.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "SysLog")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysLog {
    private Integer id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 请求时间
     */
    private Date createTime;

    /**
     * 请求路径
     */
    private String url;

    /**
     * 是否放行
     */
    private boolean isIntercept;

    /**
     * 备注
     */
    private String remake;

}
