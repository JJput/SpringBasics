package com.twj.spirngbasics.oss.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @Author jjput
 * @Date 2021-06-06 17:31:31
 * @Version 1.0
 * @Describe 阿里云上传
 */
@Service
@Component
public class AliyunCOSService {

    @Value("${aliyun.oss.sts.endpoint}")
    private String stsEndpoint;
    @Value("${aliyun.oss.accesskeyid}")
    private String accessKeyId;
    @Value("${aliyun.oss.accesskeysecret}")
    private String accessKeySecret;
    @Value("${aliyun.oss.rolearn}")
    private String roleArn;
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.bucketname}")
    private String bucketName;

    /**
     * 签发sts临时秘钥
     * @param path 路径不能以/开头,例如:data/xxx/xx
     * @return
     */
    public AssumeRoleResponse push(String path) {

        // 自定义角色会话名称，用来区分不同的令牌，例如可填写为SessionTest。
        String roleSessionName = "Athena";
        // 以下Policy用于限制仅允许使用临时访问凭证向目标存储空间examplebucket上传文件。
        // 临时访问凭证最后获得的权限是步骤4设置的角色权限和该Policy设置权限的交集，即仅允许将文件上传至目标存储空间examplebucket下的exampledir目录。
        String policy = "{\n" +
                "    \"Version\": \"1\", \n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Action\": [\n" +
                "                \"oss:PutObject\"\n" +
                "            ], \n" +
                "            \"Resource\": [\n" +
                "                \"acs:oss:*:*:athena-main/" + path + "\" \n" +
                "            ], \n" +
                "            \"Effect\": \"Allow\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        try {
            // 添加endpoint。
            DefaultProfile.addEndpoint("", "", "Sts", stsEndpoint);
            // 构造default profile。
            IClientProfile profile = DefaultProfile.getProfile("", accessKeyId, accessKeySecret);
            // 构造client。
            DefaultAcsClient client = new DefaultAcsClient(profile);
            AssumeRoleRequest request = new AssumeRoleRequest();
            request.setMethod(MethodType.POST);
            request.setRoleArn(roleArn);
            request.setRoleSessionName(roleSessionName);
            request.setPolicy(policy); // 如果policy为空，则用户将获得该角色下所有权限。
            request.setDurationSeconds(15 * 60L); // 设置临时访问凭证的有效时间最小为15Min。
            AssumeRoleResponse response = client.getAcsResponse(request);
            return response;
//            System.out.println("Expiration: " + response.getCredentials().getExpiration());
//            System.out.println("Access Key Id: " + response.getCredentials().getAccessKeyId());
//            System.out.println("Access Key Secret: " + response.getCredentials().getAccessKeySecret());
//            System.out.println("Security Token: " + response.getCredentials().getSecurityToken());
//            System.out.println("RequestId: " + response.getRequestId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 上传文件
     * @param ossPath 路径不能以/开头,例如:data/xxx/xx
     * @param file 要上传的文件
     * @throws FileNotFoundException
     */
    public void pushObject(String ossPath, File file) throws FileNotFoundException {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = new FileInputStream(file.getAbsolutePath());
        // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(bucketName, ossPath, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();


    }
}
