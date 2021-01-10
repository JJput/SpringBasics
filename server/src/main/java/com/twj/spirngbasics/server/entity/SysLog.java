package com.twj.spirngbasics.server.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "SysLog")
public class SysLog {

    @JsonProperty("_id")
    private String id;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("请求时间")
    private Date createTime;

    @ApiModelProperty("请求路径")
    private String url;

    @ApiModelProperty("是否放行")
    private boolean isIntercept;

    @ApiModelProperty("备注")
    private String remake;

}
