package com.twj.spirngbasics.server.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@Configuration
public class ValidatorConfiguration {

    /**
     * Hibernate-Validator实体类常用注解示例
     *     @NotEmpty 用来校验字符串、集合、map、数组不能为null或空
     *              （字符串传入空格也不可以）（集合需至少包含一个元素）
     *              @NotEmpty(message = "错误提示信息")
     *     @Size(max=, min=)指定的字符串、集合、map、数组长度必须在指定的max和min内
     *                      允许元素为null，字符串允许为空格
     *                      @Size(min = 1, max = 6, message = "")
     *     @Length(min=,max=) 只用来校验字符串，长度必须在指定的max和min内
     *                      允许元素为null
     *     @Range(min=,max=) 用来校验数字或字符串的大小必须在指定的min和max内
     *                       ->字符串会转成数字进行比较，如果不是数字校验不通过
     *                       允许元素为null
     *     @Min() 校验数字（包括integer short long int 等）的最小值，不支持小数即double和float
     *              允许元素为null
     *     @Max() 校验数字（包括integer short long int 等）的最小值，不支持小数即double和float
     *              允许元素为null
     *     @Pattern() 正则表达式匹配，可用来校验年月日格式，是否包含特殊字符等
     *                  @Pattern(regexp = "", message ="")
     */

    /**
     * 配置
     *
     * @return
     */
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)     //极速模式
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }


}