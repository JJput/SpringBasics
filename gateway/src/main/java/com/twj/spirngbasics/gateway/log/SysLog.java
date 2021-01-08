package com.twj.spirngbasics.gateway.log;


import lombok.Data;

import java.util.Date;

@Data
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
