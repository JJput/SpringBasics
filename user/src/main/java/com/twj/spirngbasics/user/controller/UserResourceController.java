package com.twj.spirngbasics.user.controller;

import cn.hutool.core.util.IdUtil;
import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.user.dto.UserResourceDto;
import com.twj.spirngbasics.user.entity.UserResource;
import com.twj.spirngbasics.user.service.UserResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @作者: Jun
 * @创建时间: 2021-01-19 16:00:37
 * @Version 1.0
 * @描述: 资源
 */
@RestController
@RequestMapping("/user/resource")
@Api(tags = "资源")
@Slf4j
public class UserResourceController {

    @Resource
    private UserResourceService userResourceService;

    @Autowired
    WebApplicationContext applicationContext;

    /**
     * 自动扫描所有接口，可选是否添加到数据库中
     *
     * @return
     */
    @GetMapping("/getAllURL")
    public Object getAllURL() {
        List<UserResource> resultList = new ArrayList<>();

        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

        Map<String, UserResource> resultMap = new HashMap<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> mappingInfoHandlerMethodEntry : map.entrySet()) {
            UserResource userResourceDto = new UserResource();
            RequestMappingInfo requestMappingInfo = mappingInfoHandlerMethodEntry.getKey();
            HandlerMethod handlerMethod = mappingInfoHandlerMethodEntry.getValue();

            //handlerMethod.getMethod().getDeclaringClass().getName() // 类名
            Annotation[] parentAnnotations = handlerMethod.getBeanType().getAnnotations();
            for (Annotation annotation : parentAnnotations) {
                if (annotation instanceof Api) {
                    Api api = (Api) annotation;
                    if (resultMap.containsKey(api.tags()[0])) {
                        resultMap.get(api.tags()[0]).getChildren().add(userResourceDto);
                        userResourceDto.setName(api.tags()[0]);
                        userResourceDto.setId(IdUtil.simpleUUID().substring(0, 8));
                        userResourceDto.setParent(resultMap.get(api.tags()[0]).getId());
                        userResourceDto.setCreatedBy("SYSTEM");
                        userResourceDto.setCreatedTime(new Date());
                    } else {
                        UserResource dto = new UserResource();
                        dto.setId(IdUtil.simpleUUID().substring(0, 8));
                        dto.setCreatedBy("SYSTEM");
                        dto.setCreatedTime(new Date());
                        dto.setName(api.tags()[0]);
                        resultMap.put(api.tags()[0], dto);
                        dto.setChildren(new ArrayList<>());
                        dto.getChildren().add(userResourceDto);
                        userResourceDto.setId(IdUtil.simpleUUID().substring(0, 8));
                        userResourceDto.setName(api.tags()[0]);
                        userResourceDto.setParent(dto.getId());
                        userResourceDto.setCreatedBy("SYSTEM");
                        userResourceDto.setCreatedTime(new Date());
                        resultList.add(dto);
                    }
                }
//                else if (annotation instanceof RequestMapping) {
//                    RequestMapping requestMapping = (RequestMapping) annotation;
//                    if (null != requestMapping.value() && requestMapping.value().length > 0) {
//                        resultMap.put("classURL", requestMapping.value()[0]);//类URL
//                    }
//                }
            }
            String requestURL = "";
            String desc = "";

//            resultMap.put("methodName", handlerMethod.getMethod().getName()); // 方法名
            Annotation[] annotations = handlerMethod.getMethod().getDeclaredAnnotations();
            if (annotations != null) {
                // 处理具体的方法信息
                for (Annotation annotation : annotations) {
                    if (annotation instanceof ApiOperation) {
                        ApiOperation methodDesc = (ApiOperation) annotation;
                        desc = methodDesc.value();
//                        resultMap.put("methodDesc", desc);//接口描述
                    }
                }
            }
            PatternsRequestCondition p = requestMappingInfo.getPatternsCondition();
            for (String url : p.getPatterns()) {
//                resultMap.put("methodURL", url);//请求URL
                requestURL = applicationContext.getApplicationName() + url;
            }
            userResourceDto.setRequest(requestURL);
            userResourceDto.setName(desc);
//            RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
//            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
//                resultMap.put("requestType", requestMethod.toString());//请求方式：POST/PUT/GET/DELETE
//            }
        }
        userResourceService.insertList(resultList);
        return resultList;
    }


}
