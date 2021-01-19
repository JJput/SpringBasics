import com.twj.spirngbasics.business.BusinessApplication;
import com.twj.spirngbasics.server.service.SysLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusinessApplication.class)
public class MongoTest {

    @Autowired
    private SysLogService sysLogService;



    @Test
    public void saveTest() {

    }



}