package com.twj.spirngbasics.phonemessage.msg;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.tencentcloudapi.sms.v20190711.models.SendStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @作者: JJ
 * @创建时间: 2020/11/15 下午10:53
 * @Version 1.0
 * @描述: 腾讯短信发送
 */
@Component
public class TencentSendSms {
    private static final Logger LOG = LoggerFactory.getLogger(TencentSendSms.class);

    /**
     * 控制台添加应用后生成的实际SdkAppid，示例如1400006666
     */
    @Value("${tencent.sms.appid}")
    private String appid = "";

    /**
     * 短信签名名称(官方叫内容,就很奇怪了),腾讯云短信控制台-国内短信-签名管理-列:内容
     */
    @Value("${tencent.sms.sign}")
    private String sign = "";

    @Value("${tencent.secret.id}")
    private String secretId = "";

    @Value("${tencent.secret.key}")
    private String secretKey = "";

    public static void main(String[] args) {
        TencentSendSms tencentSendSms = new TencentSendSms();
        tencentSendSms.sendChina("TEMPLATE_ID", "11122223333", "123456");
    }

    /**
     * 发送单个手机短信验证码(中国)
     *
     * @param phoneNumbers   手机号
     * @param templateParams 验证码
     * @return true成功
     */
    public boolean sendChina(String templateID, String phoneNumbers, String templateParams) {
        return sendChinaList(templateID, new String[]{phoneNumbers}, new String[]{templateParams});
    }


    /**
     * 发送多个手机短信验证码(中国) 最多不超过200个
     *
     * @param phoneNumbers   手机号码数组 列如: 11112222333,22223333444
     * @param templateParams 内容参数，一般为验证码 列如：123456,888888
     * @return true成功
     */
    public boolean sendChinaList(String templateID, String[] phoneNumbers, String[] templateParams) {
        for (int i = 0; i < phoneNumbers.length; i++) {
            phoneNumbers[i] = PhoneCountry.CN + phoneNumbers[i];
        }
        return send(templateID, phoneNumbers, templateParams);
    }

    /**
     * 发送单个手机短信验证码(要加区号)
     *
     * @param phoneNumbers   手机号
     * @param templateParams 验证码
     * @return true成功
     */
    public boolean send(String templateID, String phoneNumbers, String templateParams) {
        return sendList(templateID, new String[]{phoneNumbers}, new String[]{templateParams});
    }


    /**
     * 发送多个手机短信验证码(要加区号) 最多不超过200个
     *
     * @param phoneNumbers   手机号码数组 列如: 11112222333,22223333444
     * @param templateParams 内容参数，一般为验证码 列如：123456,888888
     * @return true成功
     */
    public boolean sendList(String templateID, String[] phoneNumbers, String[] templateParams) {
        return send(templateID, phoneNumbers, templateParams);
    }


    /**
     * * 下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]
     *
     * @param phoneNumbers   示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号
     * @param templateParams 发送内容参数
     * @return true成功
     */
    public boolean send(String templateID, String[] phoneNumbers, String[] templateParams) {
        try {
            /* 必要步骤：
             * 实例化一个认证对象，入参需要传入腾讯云账户密钥对secretId，secretKey。
             * 这里采用的是从环境变量读取的方式，需要在环境变量中先设置这两个值。
             * 你也可以直接在代码中写死密钥对，但是小心不要将代码复制、上传或者分享给他人，
             * 以免泄露密钥对危及你的财产安全。
             * CAM密匙查询: https://console.cloud.tencent.com/cam/capi*/
            Credential cred = new Credential(secretId, secretKey);

            // 实例化一个http选项，可选，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            // 设置代理
//            httpProfile.setProxyHost("host");
//            httpProfile.setProxyPort(port);
            /* SDK默认使用POST方法。
             * 如果你一定要使用GET方法，可以在这里设置。GET方法无法处理一些较大的请求 */
            httpProfile.setReqMethod("POST");
            /* SDK有默认的超时时间，非必要请不要进行调整
             * 如有需要请在代码中查阅以获取最新的默认值 */
            httpProfile.setConnTimeout(60);
            /* SDK会自动指定域名。通常是不需要特地指定域名的，但是如果你访问的是金融区的服务
             * 则必须手动指定域名，例如sms的上海金融区域名： sms.ap-shanghai-fsi.tencentcloudapi.com */
//            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            /* 非必要步骤:
             * 实例化一个客户端配置对象，可以指定超时时间等配置 */
            ClientProfile clientProfile = new ClientProfile();
            /* SDK默认用TC3-HMAC-SHA256进行签名
             * 非必要请不要修改这个字段 */
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);
            /* 实例化要请求产品(以sms为例)的client对象
             * 第二个参数是地域信息，可以直接填写字符串ap-guangzhou，或者引用预设的常量 */
            SmsClient client = new SmsClient(cred, "", clientProfile);
            /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数
             * 你可以直接查询SDK源码确定接口有哪些属性可以设置
             * 属性可能是基本类型，也可能引用了另一个数据结构
             * 推荐使用IDE进行开发，可以方便的跳转查阅各个接口和数据结构的文档说明 */
            SendSmsRequest req = new SendSmsRequest();

            /* 填充请求参数,这里request对象的成员变量即对应接口的入参
             * 你可以通过官网接口文档或跳转到request对象的定义处查看请求参数的定义
             * 基本类型的设置:
             * 帮助链接：
             * 短信控制台: https://console.cloud.tencent.com/sms/smslist
             * sms helper: https://cloud.tencent.com/document/product/382/3773 */

            /* 短信应用ID: 短信SdkAppid在 [短信控制台] 添加应用后生成的实际SdkAppid，示例如1400006666 */
            req.setSmsSdkAppid(appid);

            /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，签名信息可登录 [短信控制台] 查看 */
            req.setSign(sign);

            /* 国际/港澳台短信 senderid: 国内短信填空，默认未开通，如需开通请联系 [sms helper] */
//            String senderid = ;
//            req.setSenderId(senderid);

            /* 用户的 session 内容: 可以携带用户侧 ID 等上下文信息，server 会原样返回 */
//            String session = "xxx";
//            req.setSessionContext(session);

            /* 短信码号扩展号: 默认未开通，如需开通请联系 [sms helper] */
//            String extendcode = "xxx";
//            req.setExtendCode(extendcode);

            /* 模板 ID: 必须填写已审核通过的模板 ID。模板ID可登录 [短信控制台] 查看 */
            req.setTemplateID(templateID);

            /* 下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]
             * 示例如：+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号*/
            req.setPhoneNumberSet(phoneNumbers);

            /* 模板参数: 若无模板参数，则设置为空*/
            req.setTemplateParamSet(templateParams);

            /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
             * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
            SendSmsResponse res = client.SendSms(req);

            if (res.getSendStatusSet() == null) {
                LOG.error("res.getSendStatusSet() is null");
                return false;
            }
            SendStatus sendStatus = res.getSendStatusSet()[0];
            if (sendStatus == null) {
                LOG.error("sendStatus is null");
                return false;
            }
            if (sendStatus.getCode().equals("Ok")) {
                LOG.info("send phoneNumber success, pnumbers:{},content:{}", Arrays.stream(phoneNumbers).toArray(), Arrays.stream(templateParams).toArray());
                return true;
            }
            LOG.error("send phoneNumbers error, pnumbers:{}}", Arrays.stream(phoneNumbers).toArray());
            LOG.error("message:{}", sendStatus.getMessage());
            //可以通过官网接口文档或跳转到response对象的定义处查看返回字段的定义
            LOG.error("RequestId:{}", res.getRequestId());

        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            LOG.error("腾讯短信-发送失败-解析错误");
        }
        return false;
    }
}
