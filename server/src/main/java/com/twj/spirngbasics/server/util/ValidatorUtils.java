package com.twj.spirngbasics.server.util;


import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.server.exception.BusinessException;
import com.twj.spirngbasics.server.exception.ValidatorException;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;

import static com.twj.spirngbasics.server.util.Constant.Http.ERROR.*;


public class ValidatorUtils {

    /**
     * 空校验（null or ""）
     */
    public static void require(Object str, String fieldName) {
        if (StringUtils.isEmpty(str)) {
            throw new ValidatorException(VIOLATION_EXCEPTION, fieldName);
        }
    }

    /**
     * 空校验（null or ""）
     */
    public static void require(Object str, ResponseDto.ResponseCode code) {
        if (StringUtils.isEmpty(str)) {
            throw new BusinessException(code);
        }
    }

    /**
     * 长度校验
     */
    public static void length(String str, String fieldName,int max) {
        int length = 0;
        if (!StringUtils.isEmpty(str)) {
            length = str.length();
        }
        if (length > max) {
            throw new ValidatorException(VIOLATION_EXCEPTION, fieldName);
        }
    }
    /**
     * 长度校验
     */
    public static void length(String str, String fieldName,int min,int max) {
        int length = 0;
        if (!StringUtils.isEmpty(str)) {
            length = str.length();
        }
        if (length > max) {
            throw new ValidatorException(VIOLATION_EXCEPTION, fieldName);
        }
    }
    /**
     * list去重
     *
     * @param list
     * @return
     */
    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }
}
