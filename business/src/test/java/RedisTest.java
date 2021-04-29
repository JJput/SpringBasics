import com.twj.spirngbasics.business.BusinessApplication;
import com.twj.spirngbasics.server.config.RabbitMqConfig;
import com.twj.spirngbasics.server.mq.RabbitProducer;
import com.twj.spirngbasics.server.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author jjput
 * @Date 2021-04-29 16:12:11
 * @Version 1.0
 * @Describe redis测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusinessApplication.class)
public class RedisTest {


    @Test
    public void hashmapTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "test");
        map.put("age", "3");
        RedisUtils.setHash("test", map);
        System.out.println("存入map完毕------");
        System.out.println("取key------");
        System.out.println("name:" + RedisUtils.getHashValue("test", "name"));
        System.out.println("age:" + RedisUtils.getHashValue("test", "age"));
        System.out.println("删除age------");
        RedisUtils.delHash("test", "age");
        System.out.println("判断age是否存在------");
        System.out.println(RedisUtils.isEmptyHashKey("test", "age"));
        System.out.println("添加type------");
        RedisUtils.setHash("test", "type", "123");
        System.out.println("判断type是否存在------");
        System.out.println(RedisUtils.isEmptyHashKey("test", "type"));
        System.out.println("取整个map------");
        System.out.println("map长度为:" + RedisUtils.getHash("test").size());


    }

}
