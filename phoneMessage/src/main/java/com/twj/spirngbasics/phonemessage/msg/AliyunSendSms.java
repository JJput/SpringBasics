package com.twj.spirngbasics.phonemessage.msg;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @作者: JJ
 * @创建时间: 2020/11/15 下午10:53
 * @Version 1.0
 * @描述: 阿里短信发送
 */
@Component
public class AliyunSendSms {

    private static final Logger LOG = LoggerFactory.getLogger(AliyunSendSms.class);

    /**
     * 地区编号
     */
    private static final String REGION_ID = "cn-hangzhou";

    @Value("${aliyun.sms.sign}")
    //中文需要转unicode编码
    private String sign;

    @Value("${aliyun.accesskey.id}")
    private String accesskeyId;

    @Value("${aliyun.accesskey.secret}")
    private String accesskeySecret;

    public static void main(String[] args) {
        AliyunSendSms aliyunSendSms = new AliyunSendSms();
        aliyunSendSms.sendCode("模板id", "123456", "11122223333");
    }

    /**
     * 发送阿里云短信
     *
     * @param templateCode 短信模板ID
     * @param phone        手机号
     * @param code         验证码
     * @return
     */
    public boolean sendCode(final String templateCode, final String phone, final String code) {
        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, accesskeyId, accesskeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", REGION_ID);
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", sign);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject jsonObject = JSONObject.parseObject(response.getData());
            String resCode = jsonObject.getString("Code");
            if (!resCode.equals("OK")) {
                //错误code https://help.aliyun.com/document_detail/101346.html?spm=a2c1g.8271268.10000.143.5932df25z0Nrp7
                LOG.error("send phoneNumbers error rescode={}, pnumber:{}}", resCode, phone);
                return true;
            } else {
                LOG.info("send phoneNumber success, pnumbers:{} code:{}", phone, code);
            }
        } catch (ClientException e) {
            e.printStackTrace();
            LOG.error("阿里短信-发送失败-ClientException");
        } catch (JSONException e) {
            e.printStackTrace();
            LOG.error("阿里短信-发送失败-JSON解析错误！！");
        }
        return false;
    }

}

