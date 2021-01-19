import com.twj.spirngbasics.business.BusinessApplication;
import com.twj.spirngbasics.server.util.DictUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @作者: JJ
 * @创建时间: 2020/11/12 下午1:54
 * @Version 1.0
 * @描述:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusinessApplication.class)
public class DictUtilsTest {

    @Test
    public void test(){
        System.out.println(DictUtils.getDict("","1").toString());
        System.out.println(DictUtils.getDictLabel("","1"));
        System.out.println(DictUtils.getDictValue("","狗狗"));
    }
}
