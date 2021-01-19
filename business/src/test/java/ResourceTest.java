import com.twj.spirngbasics.business.BusinessApplication;
import com.twj.spirngbasics.user.entity.UserResource;
import com.twj.spirngbasics.user.mapper.UserResourceMapper;
import com.twj.spirngbasics.user.service.UserResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 资源测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusinessApplication.class)
public class ResourceTest {

    @Resource
    private UserResourceService userResourceService;

    @Resource
    private UserResourceMapper userResourceMapper;

    @Test
    public void add() {
        for (int i = 1; i < 4; i++) {
            UserResource userResource = new UserResource();
            userResource.setName("t" + i);
            userResource.setRequest("/" + i);
            userResource.insertSystem();
            userResource.setId("0" + i);
            userResourceMapper.insert(userResource);
        }

        for (int i = 1; i < 3; i++) {
            for (int j = 1; j < 3; j++) {
                UserResource userResource = new UserResource();
                userResource.setName("t" + i + "" + j);
                userResource.setRequest("/" + i + "/" + j);
                userResource.insertSystem();
                userResource.setId("0" + i + "0" + j);
                userResource.setParent("0" + i);
                userResourceMapper.insert(userResource);
            }
        }
    }

    @Test
    public void tree() {
        System.out.println(userResourceService.loadTree());
    }
}
