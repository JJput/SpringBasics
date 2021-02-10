import com.twj.spirngbasics.business.BusinessApplication;
import com.twj.spirngbasics.server.config.RabbitMqConfig;
import com.twj.spirngbasics.server.mq.RabbitProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author jjput
 * @Date 2021-02-05 15:24:03
 * @Version 1.0
 * @Describe 短信测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusinessApplication.class)
public class PhoneMsgTest {

    @Resource
    private RabbitProducer rabbitProducer;

    @Test
    public void sendAliyunPhoneMsg() {
        rabbitProducer.sendMsg(RabbitMqConfig.EXCHANGE_PROJECT,
                RabbitMqConfig.ROUTINGKEY_PHONE_MSG ,
                "");
    }

    @Test
    public void sendTencentPhoneMsg() {
        rabbitProducer.sendMsg(RabbitMqConfig.EXCHANGE_PROJECT,
                RabbitMqConfig.ROUTINGKEY_PHONE_MSG + RabbitMqConfig.QUETYPE_TENCENT,
                "");
    }


}
