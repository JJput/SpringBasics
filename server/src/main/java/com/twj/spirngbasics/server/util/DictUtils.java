package com.twj.spirngbasics.server.util;


import com.twj.spirngbasics.server.dto.SysDictDto;
import com.twj.spirngbasics.server.entity.SysDict;
import com.twj.spirngbasics.server.mapper.SysDictDynamicSqlSupport;
import com.twj.spirngbasics.server.mapper.SysDictMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @作者: JJ
 * @创建时间: 2020/11/11 下午8:41
 * @Version 1.0
 * @描述: 字典缓存
 */
@Component
@Slf4j
public class DictUtils {

    @Resource
    private SysDictMapper sysDictMapper;

    private static Map<String, List<SysDict>> map = new HashMap<>();

    @PostConstruct
    public void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long time1 = System.currentTimeMillis();
                log.info("------开始缓存数据-字典------");
                SelectStatementProvider selectStatement = SqlBuilder.select(sysDictMapper.selectList)
                        .from(SysDictDynamicSqlSupport.sysDict)
                        .orderBy(SysDictDynamicSqlSupport.sort)
                        .build()
                        .render(RenderingStrategies.MYBATIS3);
                List<SysDict> sysDictList =sysDictMapper.selectMany(selectStatement);
                for (SysDict sysDict : sysDictList) {
                    if (map.containsKey(sysDict.getType())) {
                        map.get(sysDict.getType()).add(sysDict);
                    } else {
                        List<SysDict> list = new ArrayList<>();
                        list.add(sysDict);
                        map.put(sysDict.getType(), list);
                    }
                }

                long time2 = System.currentTimeMillis();
                log.info("------数据缓存完毕------" + (time2 - time1) + "ms");
            }
        }).start();
    }

    /**
     * 根据type返回该类型所有字典数据
     *
     * @param type 类型
     * @return
     */
    public static List<SysDictDto> getDictList(String type) {
        return CopyUtils.copyList(map.get(type), SysDictDto.class);
    }

    /**
     * 根据type和value 返回字典数据
     *
     * @param type  类型
     * @param value 值
     * @return
     */
    public static SysDictDto getDict(String type, String value) {
        List<SysDict> sysDictList = map.get(type);
        for (SysDict sysDict : sysDictList) {
            if (sysDict.getValue().equals(value))
                return CopyUtils.copy(sysDict, SysDictDto.class);
        }
        return null;
    }

    /**
     * 根据type和value 返回字典的label
     *
     * @param type  类型
     * @param value 值
     * @return
     */
    public static String getDictLabel(String type, String value) {
        SysDictDto sysDictDto = getDict(type, value);
        if (sysDictDto != null) return sysDictDto.getLabel();
        return null;
    }


    /**
     * 根据type和lable 返回字典的value
     *
     * @param type  类型
     * @param label 显示内容
     * @return
     */
    public static String getDictValue(String type, String label) {
        List<SysDict> sysDictList = map.get(type);
        for (SysDict sysDict : sysDictList) {
            if (sysDict.getLabel().equals(label))
                return sysDict.getValue();
        }
        return null;
    }

    @PreDestroy
    public void destroy() {
        //系统运行结束
        map.clear();
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void update() {
        //每天凌晨2点执行一次缓存
        map.clear();
        init();
    }

}
