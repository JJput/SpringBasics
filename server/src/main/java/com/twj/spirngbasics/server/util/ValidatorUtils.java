package com.twj.spirngbasics.server.util;


import com.twj.spirngbasics.server.dto.ResponseCode;
import com.twj.spirngbasics.server.exception.BusinessException;
import com.twj.spirngbasics.server.exception.ValidatorException;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;

import static com.twj.spirngbasics.server.util.Constant.Http.ERROR.*;


/**
 * 校验工具
 * 描述:
 * 1、校验对象是否为空 @require
 * 2、校验长度是否在范围 @length
 * 3、校验长度是否过长 @length
 */
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
    public static void require(Object str, ResponseCode code) {
        if (StringUtils.isEmpty(str)) {
            throw new BusinessException(code);
        }
    }

    /**
     * 长度校验
     */
    public static void length(String str, String fieldName, int max) {
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
    public static void length(String str, String fieldName, int min, int max) {
        int length = 0;
        if (!StringUtils.isEmpty(str)) {
            length = str.length();
        }
        if (length > max || length < min) {
            throw new ValidatorException(VIOLATION_EXCEPTION, fieldName);
        }
    }

    /**
     * list去重
     *
     * @param list 操作对象
     * @return 新的数组
     */
    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }
}
