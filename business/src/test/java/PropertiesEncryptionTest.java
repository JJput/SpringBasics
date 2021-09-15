import com.twj.spirngbasics.business.BusinessApplication;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author jjput
 * @Date 2021-09-15 13:48:10
 * @Version 1.0
 * @Describe 生成properties加密秘钥
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusinessApplication.class)
public class PropertiesEncryptionTest {


    @Autowired
    private StringEncryptor encryptor;

    /**
     * 加密
     */
    @Test
    public void encrypt() {
        System.out.println(encryptor.encrypt("jdbc:mysql://localhost:3306/test?characterEncoding=UTF8&autoReconnect=true&allowPublicKeyRetrieval=true&serverTimezone=UTC"));
        System.out.println(encryptor.encrypt("admin"));
        System.out.println(encryptor.encrypt("root"));
        System.out.println(encryptor.encrypt("123456"));
    }

    /**
     * 解密
     */
    @Test
    public void decrypt() {
        System.out.println(encryptor.decrypt("uaNBj4ZmzCD83uedRYUXqQ=="));
        System.out.println(encryptor.decrypt("oKBQENfbbQiMyPvECAgPGA=="));
    }


}
