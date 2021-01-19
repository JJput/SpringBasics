package com.twj.spirngbasics.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageDto<T> {


    @ApiModelProperty("当前页码")
    @NotEmpty(message = "page不能为空")
    @Range(min = 0, max = Integer.MAX_VALUE, message = "id长度异常,取值范围0~" + Integer.MAX_VALUE)
    protected int page;

    @ApiModelProperty("每页条数")
    @NotEmpty(message = "size不能为空")
    @Range(min = 0, max = Integer.MAX_VALUE, message = "id长度异常,取值范围0~" + Integer.MAX_VALUE)
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
