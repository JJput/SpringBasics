package com.twj.spirngbasics.server.service;


import com.twj.spirngbasics.server.entity.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SysLogService {

    private static final String ROW_CREATE_TIME = "createTime";
    private static final String COLLECTION_NAME = "SysLog";


    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     */
    public void save(String log) {
        mongoTemplate.save(log, COLLECTION_NAME);
    }


    /**
     * 分页查询
     *
     * @param page 第几页
     * @param size 数量
     * @return
     */
    public List<SysLog> list(int page, int size) {
        //根据宠物id
        Query query = new Query();
        // 时间降序排序
        query.with(Sort.by(Sort.Direction.DESC, ROW_CREATE_TIME));
        if (page > 1) {
            // number 参数是为了查上一页的最后一条数据
            int number = (page - 1) * size;
            query.limit(number);

            List<SysLog> cardTracks = mongoTemplate.find(query, SysLog.class);
            // 取出最后一条
            SysLog record = cardTracks.get(cardTracks.size() - 1);

            // 取到上一页的最后一条数据 id，当作条件查接下来的数据
            Date date = record.getCreateTime();

            // 从上一页最后一条开始查（大于不包括这一条）
            query.addCriteria(Criteria.where(ROW_CREATE_TIME).lt(date));
        }
        // 页大小重新赋值，覆盖 number 参数
        query.limit(size);
        return mongoTemplate.find(query, SysLog.class);
    }

}
