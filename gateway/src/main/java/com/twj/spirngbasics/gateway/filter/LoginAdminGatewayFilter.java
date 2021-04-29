package com.twj.spirngbasics.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.twj.spirngbasics.gateway.config.RabbitMqConfig;
import com.twj.spirngbasics.gateway.config.RabbitProducer;
import com.twj.spirngbasics.gateway.log.SysLog;
import com.twj.spirngbasics.gateway.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class LoginAdminGatewayFilter implements GatewayFilter, Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(LoginAdminGatewayFilter.class);

    /**
     * 是否开启测试
     */
    private static final boolean IS_TEST = true;
    /**
     * 测试Token值
     */
    private static final String TEST_TOKEN = "123";

    private static final String[] PATH = {
            "/business/test/test",
    };
    private static Set<String> ePath = null;

    @Resource
    private RabbitProducer rabbitProducer;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
//        LOG.info("请求:" + path);
        // 不需要拦截
        if (isRelease(path)) {
//            LOG.info("不拦截");
            return chain.filter(exchange);
        }
        //获取header的token参数
        String token = exchange.getRequest().getHeaders().getFirst("token");
//        LOG.info("GateWay token校验 ：{}", token);
        //没有token说明没登录
        if (token == null || token.isEmpty()) {
            LOG.info("{} token为空，请求被拦截", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            byte[] bytes = "无token".getBytes(StandardCharsets.UTF_8);
            return exchange.getResponse().writeWith(Flux.just(exchange.getResponse().bufferFactory().wrap(bytes)));
        }
        //测试token直接放行
        if (IS_TEST) {
            if (token.equals(TEST_TOKEN)) {
                //判断redis是否缓存testtoken
                Object testtoken = RedisUtils.get(token);
                if (testtoken == null) {
                    RedisUtils.set(TEST_TOKEN, "{\"id\":\"TEST_TOEKN\"}", 10, TimeUnit.MINUTES);
                }
                LOG.info("test token放行");
                return chain.filter(exchange);
            }
        }
        Object userObject = RedisUtils.get(token);

        //token获取对应对象不存在,说明token过期等..
        if (userObject == null) {

            LOG.warn("{} token无效，请求被拦截", path);
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            byte[] bytes = "token无效".getBytes(StandardCharsets.UTF_8);
            return exchange.getResponse().writeWith(Flux.just(exchange.getResponse().bufferFactory().wrap(bytes)));
        } else {
            String strUser = userObject.toString();
//            LOG.info("GateWay登录验证成功");
            JSONObject jsonUser = JSON.parseObject(strUser);
            /*********************
             ****** 权限校验 *******
             *********************/
            try {
                //获取用户角色id
                String roleId = jsonUser.getString("roleId");
                if (roleId == null) {
                    byte[] bytes = "权限获取失败".getBytes(StandardCharsets.UTF_8);
                    return exchange.getResponse().writeWith(Flux.just(exchange.getResponse().bufferFactory().wrap(bytes)));
                }

                //最终校验的权限路径需要先处理一下
                String checkUrl = null;
                //如果是删除权限需要特殊处理
                if (path.contains("delete")) {
                    int end = path.lastIndexOf("delete");
                    checkUrl = path.substring(0, end + 6);
                } else {
                    checkUrl = path.split("-")[0];
                }
                //通过redis判断当前用户是否有权限
                //redis中缓存了所有角色权限hashmap结构,通过用户的角色id,判断是否有权限
                if (RedisUtils.isEmptyHashKey(roleId, checkUrl)) {
//                    LOG.info("权限校验通过");
                    this.logCreate(jsonUser.getString("id"), path, RedisUtils.getHashValue(roleId, checkUrl).toString(), false);
                } else {
                    LOG.warn("{} 权限校验未通过 403", path);
                    this.logCreate(jsonUser.getString("id"), path, true);
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    byte[] bytes = "无权限".getBytes(StandardCharsets.UTF_8);
                    return exchange.getResponse().writeWith(Flux.just(exchange.getResponse().bufferFactory().wrap(bytes)));
                }
            } catch (Exception e) {
                byte[] bytes = "权限获取失败".getBytes(StandardCharsets.UTF_8);
                return exchange.getResponse().writeWith(Flux.just(exchange.getResponse().bufferFactory().wrap(bytes)));
            }
            return chain.filter(exchange);
        }
    }

    /**
     * 哪些接口不做拦截。
     * 使用hashset提高效率
     *
     * @param path 请求路径
     * @return true放行 false拦截
     */
    private boolean isRelease(String path) {
        if (this.ePath == null) {
            this.ePath = new HashSet<>();
            for (String c : PATH) {
                this.ePath.add(c);
            }
        }
        if (ePath.contains(path)) {
            return true;
        }
        return false;
    }

    public void logCreate(String userId, String path, boolean isIntercept) {
        logCreate(userId, path, null, isIntercept);
    }

    public void logCreate(String userId, String path, String pathName, boolean isIntercept) {
        SysLog sysLog = new SysLog();
        sysLog.setUserId(userId);
        sysLog.setUrl(path);
        sysLog.setPathName(pathName);
        sysLog.setCreateTime(new Date());
        //权限是否拦截
        sysLog.setIntercept(isIntercept);

        rabbitProducer.sendMsg(RabbitMqConfig.EXCHANGE_LOG,
                RabbitMqConfig.ROUTINGKEY_LOG
                , JSON.toJSONString(sysLog));
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
