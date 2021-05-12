package com.twj.spirngbasics.user.service;


import com.twj.spirngbasics.server.util.Constant;
import com.twj.spirngbasics.server.util.CopyUtils;
import com.twj.spirngbasics.user.dto.UserResourceDto;
import com.twj.spirngbasics.user.entity.UserResource;
import com.twj.spirngbasics.user.mapper.UserResourceDynamicSqlSupport;
import com.twj.spirngbasics.user.mapper.UserResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者: Jun
 * @创建时间: 2021-01-19 16:00:37
 * @Version 1.0
 * @描述: 资源
 */
@Service
@Slf4j
public class UserResourceService {

    @Resource
    private UserResourceMapper userResourceMapper;

    /**
     * 新增
     */
    public void insert(UserResource userResource) {
        userResource.insert();
        userResourceMapper.insert(userResource);
    }

    /**
     * 新增
     */
    public void insertList(List<UserResource> userResourceList) {
        List<UserResource> data = new ArrayList<>();
        for (UserResource userResource : userResourceList) {
            data.add(userResource);
            for (UserResource userResource1 : userResource.getChildren()) {
                data.add(userResource1);
            }
        }
        userResourceMapper.insertMultiple(data);
    }

    /**
     * 更新
     */
    public void update(UserResource userResource) {
        userResource.update();
        userResourceMapper.updateByPrimaryKeySelective(userResource);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        userResourceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 保存资源树
     *
     * @param jsonList
     */
    @Transactional
    public void saveJson(List<UserResourceDto> jsonList) {
        List<UserResourceDto> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(jsonList)) {
            for (UserResourceDto d : jsonList) {
                d.setParent("");
                add(list, d);
            }
        }
        log.info("共{}条", list.size());

        userResourceMapper.delete(DeleteDSLCompleter.allRows());
        for (int i = 0; i < list.size(); i++) {
            this.insert(CopyUtils.copy(list.get(i), UserResource.class));
        }
    }

    /**
     * 递归，将树型结构的节点全部取出来，放到list
     *
     * @param list
     * @param dto
     */
    public void add(List<UserResourceDto> list, UserResourceDto dto) {
        list.add(dto);
        if (!CollectionUtils.isEmpty(dto.getChildren())) {
            for (UserResourceDto d : dto.getChildren()) {
                d.setParent(dto.getId());
                add(list, d);
            }
        }
    }

    /**
     * 按约定将列表转成树
     * 要求：ID要正序排列
     *
     * @return
     */
    public List<UserResourceDto> loadTree(boolean isAdmin) {
        SelectStatementProvider selectStatement = SqlBuilder.select(UserResourceDynamicSqlSupport.id, UserResourceDynamicSqlSupport.parent, UserResourceDynamicSqlSupport.name)
                .from(UserResourceDynamicSqlSupport.userResource)
                .orderBy(UserResourceDynamicSqlSupport.request, UserResourceDynamicSqlSupport.page.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3);

        List<UserResourceDto> resourceDtoList = CopyUtils.copyList(userResourceMapper.selectMany(selectStatement), UserResourceDto.class);
        for (int i = resourceDtoList.size() - 1; i >= 0; i--) {
            // 当前要移动的记录
            UserResourceDto child = resourceDtoList.get(i);

            // 如果当前节点没有父节点，则不用往下了
            if (StringUtils.isEmpty(child.getParent())) {
                continue;
            }
            // 查找父节点
            for (int j = i - 1; j >= 0; j--) {
                UserResourceDto parent = resourceDtoList.get(j);
                if (child.getParent().equals(parent.getId())) {
                    if (CollectionUtils.isEmpty(parent.getChildren())) {
                        parent.setChildren(new ArrayList<>());
                    }
                    // 添加到最前面，否则会变成倒序，因为循环是从后往前循环的
                    parent.getChildren().add(0, child);

                    // 子节点找到父节点后，删除列表中的子节点
                    resourceDtoList.remove(child);
                }
            }
        }
        return resourceDtoList;
    }

}
