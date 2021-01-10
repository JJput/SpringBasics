package com.twj.spirngbasics.server.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PageDto<T> {


    @ApiModelProperty("当前页码")
    protected int page;

    @ApiModelProperty("每页条数")
    protected int size;

    @ApiModelProperty("总条数")
    protected long total;

    @ApiModelProperty("分页查询后返回的列表")
    protected List<T> list;

    protected T data;


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PageDto{");
        sb.append("page=").append(page);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append(", list=").append(list);

        sb.append('}');
        return sb.toString();
    }
}
