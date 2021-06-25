package com.twj.spirngbasics.oss.tencent.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.exception.MultiObjectDeleteException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.twj.spirngbasics.oss.tencent.cloud.CosStsClient;
import com.twj.spirngbasics.oss.tencent.entity.CosKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

/**
 * @作者: JJ
 * @创建时间: 2020/8/17 下午3:43
 * @Version 1.0
 * @描述:
 */
@Service
@Component
public class TencentCOSService {

    @Value("${tencent.secret.id}")
    private String secretId;

    @Value("${tencent.secret.key}")
    private String secretKey;

    @Value("${tencent.cos.bucketname}")
    private String bucketName;

    public String handURL;

    private COSClient cosClient;

    public void initCosClient() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-guangzhou");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        cosClient = new COSClient(cred, clientConfig);
        handURL = "https://"+bucketName+".cos.ap-guangzhou.myqcloud.com/";

    }

    /**
     * 获取存储桶列表
     *
     * @return
     */
    public List<Bucket> getBucketList() {
        if (cosClient == null) {
            initCosClient();
        }
        return cosClient.listBuckets();
    }


    /**
     * 获取指定存储桶所有文件
     */
    public List<Map<String, Object>> getRootDirectory(String path) {
        // Bucket的命名格式为 BucketName-APPID ，此处填写的存储桶名称必须为此格式
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        // 设置bucket名称
        listObjectsRequest.setBucketName(bucketName);
        // prefix表示列出的object的key以prefix开始
        listObjectsRequest.setPrefix(path);
        // deliter表示分隔符, 设置为/表示列出当前目录下的object, 设置为空表示列出所有的object
        listObjectsRequest.setDelimiter("/");
        // 设置最大遍历出多少个对象, 一次listobject最大支持1000
        listObjectsRequest.setMaxKeys(1000);
        ObjectListing objectListing = null;
//        do {
        try {
            if (cosClient == null) {
                initCosClient();
            }
            objectListing = cosClient.listObjects(listObjectsRequest);
        } catch (CosServiceException e) {
            e.printStackTrace();
            return null;
        } catch (CosClientException e) {
            e.printStackTrace();
            return null;
        }
        // common prefix表示表示被delimiter截断的路径, 如delimter设置为/, common prefix则表示所有子目录的路径
//            List<String> commonPrefixs = objectListing.getCommonPrefixes();

        // object summary表示所有列出的object列表
        List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();
        List<Map<String, Object>> data = new ArrayList<>();
        for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
            Map<String, Object> map = new HashMap<>();
            String filePath = cosObjectSummary.getKey();
            // 文件的路径key
            map.put("path", filePath);
            map.put("url", "https://" + bucketName + ".cos.ap-guangzhou.myqcloud.com/"
                    + filePath);
            //获取最后一个.的位置
            int lastIndexOf = filePath.lastIndexOf(".");
            //获取文件的后缀名
            String suffix = filePath.substring(lastIndexOf);
            map.put("isImage", isImageFile(suffix));
            map.put("suffix", suffix);
            map.put("down", "https://" + bucketName + ".cos.ap-guangzhou.myqcloud.com/"
                    + filePath);
            data.add(map);
        }
        return data;
//            String nextMarker = objectListing.getNextMarker();
//            listObjectsRequest.setMarker(nextMarker);
//        } while (objectListing.isTruncated());

    }

    private boolean isImageFile(String suffix) {
        switch (suffix) {
            case ".jpg":
                return true;
            case ".JPG":
                return true;
            case ".jpeg":
                return true;
            case ".JPEG":
                return true;
            case ".png":
                return true;
            case ".PNG":
                return true;
            case ".bmp":
                return true;
            case ".BMP":
                return true;
            case ".gif":
                return true;
            case ".GIF":
                return true;
            case ".heic":
                return true;
            default:
                return false;
        }
    }

    public void delObjects(List<String> names) {
        delObjects(bucketName, names);
    }

    /**
     * 批量删除存储桶里的对象
     */
    private void delObjects(String bucketName, List<String> names) {
        // Bucket的命名格式为 BucketName-APPID ，此处填写的存储桶名称必须为此格式
        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName);
        // 设置要删除的key列表, 最多一次删除1000个
        ArrayList<DeleteObjectsRequest.KeyVersion> keyList = new ArrayList<DeleteObjectsRequest.KeyVersion>();
        // 传入要删除的文件名
        for (String name : names) {
            keyList.add(new DeleteObjectsRequest.KeyVersion(name));
        }
        deleteObjectsRequest.setKeys(keyList);

        // 批量删除文件
        try {
            if (cosClient == null) {
                initCosClient();
            }
            DeleteObjectsResult deleteObjectsResult = cosClient.deleteObjects(deleteObjectsRequest);
            List<DeleteObjectsResult.DeletedObject> deleteObjectResultArray = deleteObjectsResult.getDeletedObjects();
        } catch (MultiObjectDeleteException mde) { // 如果部分删除成功部分失败, 返回MultiObjectDeleteException
            List<DeleteObjectsResult.DeletedObject> deleteObjects = mde.getDeletedObjects();
            List<MultiObjectDeleteException.DeleteError> deleteErrors = mde.getErrors();
        } catch (CosServiceException e) { // 如果是其他错误，例如参数错误， 身份验证不过等会抛出 CosServiceException
            e.printStackTrace();
            throw e;
        } catch (CosClientException e) { // 如果是客户端错误，例如连接不上COS
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 创建密钥
     *
     * @param path   指定密钥路径
     * @param isDown 是否下载  否为上传
     * @return
     */
    public CosKey createKey(String path, boolean isDown) {
        TreeMap<String, Object> config = new TreeMap<>();
        try {
            config.put("SecretId", secretId);
            config.put("SecretKey", secretKey);

            // 临时密钥有效时长，单位是秒，默认1800秒，最长可设定有效期为7200秒
            config.put("durationSeconds", 60);

            // 换成您的 bucket
            config.put("bucket", bucketName);
            // 换成 bucket 所在地区
            config.put("region", "ap-guangzhou");

            // 这里改成允许的路径前缀，可以根据自己网站的用户登录态判断允许上传的具体路径，例子：a.jpg 或者 a/* 或者 * 。
            // 如果填写了“*”，将允许用户访问所有资源；除非业务需要，否则请按照最小权限原则授予用户相应的访问权限范围。
            config.put("allowPrefix", path);

            // 密钥的权限列表。简单上传、表单上传和分片上传需要以下的权限，其他权限列表请看 https://cloud.tencent.com/document/product/436/31923
            String[] allowActions = new String[]{
                    !isDown ? //简单上传操作
                            "name/cos:PutObject"
//                    //表单上传对象
//                            "name/cos:PostObject"
//                    //分块上传：初始化分块操作
//                    "name/cos:InitiateMultipartUpload",
//                    //分块上传：List 进行中的分块上传
//                    "name/cos:ListMultipartUploads",
//                    //分块上传：List 已上传分块操作
//                    "name/cos:ListParts",
//                    //分块上传：上传分块块操作
//                    "name/cos:UploadPart",
//                    //分块上传：完成所有分块上传操作
//                    "name/cos:CompleteMultipartUpload",
//                    //取消分块上传操作
//                    "name/cos:AbortMultipartUpload",
                            //下载
                            : "name/cos:GetObject"
            };
            config.put("allowActions", allowActions);

            CosKey credential = com.alibaba.fastjson.JSONObject.parseObject(
                    CosStsClient.getCredential(config).toString(),
                    CosKey.class);
            //成功返回临时密钥信息，如下打印密钥信息
//            System.out.println(credential);
            return credential;
        } catch (Exception e) {
            //失败抛出异常
//            throw new IllegalArgumentException("no valid secret !");
            return null;
        }
    }

    /**
     * 对象上传
     * @param key
     * @param file
     * @return
     */
    public String putObject(String key,File file)
    {
        if (cosClient == null) {
            initCosClient();
        }
        PutObjectResult putObjectResult = cosClient.putObject(bucketName, key, file);
        String etag = putObjectResult.getETag();  // 获取文件的 etag
        return etag;
    }
}
