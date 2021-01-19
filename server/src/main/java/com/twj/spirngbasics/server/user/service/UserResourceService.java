package com.twj.spirngbasics.server.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.user.dto.UserResourceDto;
import com.twj.spirngbasics.server.user.entity.UserResource;
import com.twj.spirngbasics.server.user.entity.UserResourceExample;
import com.twj.spirngbasics.server.user.mapper.UserResourceMapper;
import com.twj.spirngbasics.server.util.CopyUtils;
import com.twj.spirngbasics.server.util.ValidatorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者: Jun
 * @创建时间: 2021-01-05 17:27:49
 * @Version 1.0
 * @描述: 资源
 */
@Service
@Slf4j
public class UserResourceService {

    @Resource
    private UserResourceMapper userResourceMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) throws Exception {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        UserResourceExample userResourceExample = new UserResourceExample();
        List<UserResource> userResourceList = userResourceMapper.selectByExample(userResourceExample);
        PageInfo<UserResource> pageInfo = new PageInfo<>(userResourceList);
        pageDto.setTotal(pageInfo.getTotal());
        List<UserResourceDto> userUserResourceDtoList = CopyUtils.copyList(userResourceList, UserResourceDto.class);
        pageDto.setList(userUserResourceDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(UserResource userResource) throws Exception {
        // 保存校验
        ValidatorUtils.require(userResource.getName(), "名称");
        ValidatorUtils.length(userResource.getName(), "名称:Length overrun", 100);
        ValidatorUtils.length(userResource.getPage(), "页面:Length overrun", 50);
        ValidatorUtils.length(userResource.getRequest(), "请求:Length overrun", 200);
        ValidatorUtils.length(userResource.getUpdateBy(), "更新人:Length overrun", 32);
        if (StringUtils.isEmpty(userResource.getId())) {
            this.insert(userResource);
        } else {
            this.update(userResource);
        }
    }

    /**
     * 新增
     */
    private void insert(UserResource userResource) {
        userResource.insert();
        userResourceMapper.insert(userResource);
    }

    /**
     * 更新
     */
    private void update(UserResource userResource) {
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

        userResourceMapper.deleteByExample(null);
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
    public List<UserResourceDto> loadTree() {
        UserResourceExample example = new UserResourceExample();
        example.setOrderByClause("id asc");
        List<UserResource> resourceList = userResourceMapper.selectByExample(example);
        List<UserResourceDto> resourceDtoList = CopyUtils.copyList(resourceList, UserResourceDto.class);
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
