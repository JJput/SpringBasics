package com.twj.spirngbasics.server.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.twj.spirngbasics.server.exception.BaseException;
import com.twj.spirngbasics.server.util.Constant;
import lombok.Data;


@Data
public class ResponseDto<T> {

    /**
     * 业务上的成功或失败
     */
    private boolean success = true;

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回泛型数据，自定义类型
     */
    private T content;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String token;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String longToken;


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResponseDto{");
        sb.append("success=").append(success);
        sb.append(", code='").append(code).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", content=").append(content);
        sb.append('}');
        return sb.toString();
    }

    public static ResponseDto createBySuccess() {
        return createBySuccess(null, Constant.Http.SUCCESS.DEFAULT);
    }

    public static ResponseDto createBySuccess(Object o) {
        return createBySuccess(o, Constant.Http.SUCCESS.DEFAULT);
    }

    public static ResponseDto createBySuccess(Object o, ResponseCode response) {
        ResponseDto resourceDto = new ResponseDto();
        resourceDto.setSuccess(true);
        resourceDto.setCode(response.getCode() + "");
        resourceDto.setContent(o);
        resourceDto.setMessage(response.getMessage());
        return resourceDto;
    }

    public static ResponseDto createByFail(ResponseCode response) {
        return createByFail(null, response.getCode() + "", response.getMessage());
    }

    public static ResponseDto createByFail(BaseException e) {
        return createByFail(null, e.getCode() + "", e.getMessage());
    }

    public static ResponseDto createByFail(String message) {
        return createByFail(null, Constant.Http.ERROR.DEFAULT.getCode() + "", message);
    }

    public static ResponseDto createByFail(Object o, ResponseCode response) {
        return createByFail(o, response.getCode() + "", response.getMessage());
    }
    public static ResponseDto createByFail(Object o, String code, String message) {
        ResponseDto resourceDto = new ResponseDto();
        resourceDto.setSuccess(false);
        resourceDto.setCode(code);
        resourceDto.setContent(o);
        resourceDto.setMessage(message);
        return resourceDto;
    }

    public interface ResponseCode {
        int getCode();

        String getMessage();
    }

}
