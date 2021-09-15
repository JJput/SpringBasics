## 使用说明

* [配置介绍]() - 待编写
* [代码生成]() - 待编写
* [工具说明]() - 待编写
* [如何打包]() - 待编写
* 附录
    * [网关鉴权设计]() - 待编写
    * [用户令牌设计]() - 待编写
    * [权限如何生成]() - 待编写

## 项目介绍

框架主要针对SpringBoot、SpringGateWay进行封装，开发一个新业务时，能够快速的根据数据库模型生成业务基础代码，从而实现敏捷开发。（Controller、service、entity、dto、mapper）

<img src="https://i.loli.net/2021/01/11/wCYxgnkSzJXHMBN.png" alt="image-20210111145251553" style="zoom:50%;" />

### 项目结构

```
spirngbasics
├── business ------------ Spring Boot，主要业务模块，基本只放controller，默认配置不连接Cloud
├── create -------------- 无需编译运行，仅用于模块代码生成（controller、service、entity、dto）
├── eureka -------------- Spring Cloud，根据实际情况采用
├── gateway ------------- Spring GateWay，网关模块，用于权限拦截、日志记录等
├── server -------------- 复用模块，主要服务于business。存放service、实体对象、工具类等
├── oss ----------------- 腾讯/阿里云对象存储模块，需要则引入。
├── phoneMessage -------- 腾讯/阿里云短信发送模块，需要则引入。
├── user ---------------- 用户模块，主要功能：用户登录、注册、角色、权限。需要引用即可。

```

### 引用库说明

#### 基础依赖

| 技术                            | 说明             | 版本号      | 参考                                                         | 备注   |
| ------------------------------- | ---------------- | ----------- | ------------------------------------------------------------ | ------ |
| Lombok                          | 简化对象封装工具 | 1.18.12     | [简单介绍](https://www.jianshu.com/p/49a642bdb99a) - [详细使用](https://mp.weixin.qq.com/s/Ys6ksYasfUj7TSCGICHM8w) | 已整合 |
| Hutool                          | Java工具类库     | 5.5.7       | [官方教程](https://www.hutool.cn/docs/#/)                    | 已整合 |
| Swagger2 & swagger-bootstrap-ui | 文档生成工具     | 2.9.2       | [集成教程](https://developer.ibm.com/zh/languages/spring/articles/j-using-swagger-in-a-spring-boot-project/) - [官方文档](https://swagger.io/docs/) | 已整合 |
| Hibernator-Validator            | 验证框架         | 6.0.9.Final | [集成教程](https://blog.csdn.net/java_collect/article/details/85534054) | 已整合 |
| fastjson                        | JSON工具         | 1.2.60      |                                                              | 已整合 |
| log4j2                          | 日志管理         | 2.12.1      | [集成教程](https://www.cnblogs.com/keeya/p/10101547.html#%E4%B8%BA%E4%BB%80%E4%B9%88%E9%80%89%E7%94%A8log4j2) | 已整合 |
| jasypt                          | 配置信息加密      | 3.0.4      | [集成教程](https://segmentfault.com/a/1190000021284801) | 已整合 |
| MyBatis                         | ORM框架             | 1.3.2         |                                                              | 已整合 |
| MyBatisGenerator                | 数据层代码生成      | 1.3.7         | 采用DynamicSql风格生成代码[教程](https://mp.weixin.qq.com/s/6skQR5Nq-LZm0AeWc5-xqg) - [使用指南](https://jjput.com/archives/dynamicsql) | 已整合 |
| PageHelper                      | MyBatis物理分页插件 | 1.2.10        |                                                              | 已整合 |
| Druid                           | 数据库连接池        | 1.1.20        |                                                              | 已整合 |

#### 第三方服务引用

| 技术     | 说明               | 版本号                               | 参考                                                         | 备注   |
| -------- | ------------------ | ------------------------------------ | ------------------------------------------------------------ | ------ |
| Redis    | 缓存数据库          | 2.2.8.RELEASE |                                                              | 已整合 |
| MongoDB  | 日志数据库         | 2.0.0.RELEASE |                                                              | 已整合 |
| RabbitMQ | 消息队列           | 2.1.3.RELEASE                        | [Docker安装](https://michael728.github.io/2019/06/07/docker-rabbitmq-env/) - [入门知识](https://www.cnblogs.com/sgh1023/p/11217017.html) - [集成1](https://blog.csdn.net/qq_38455201/article/details/80308771) - [集成2](https://blog.csdn.net/qq_35387940/article/details/100514134) | 已整合 |
| Sentry   | 异常信息收集      | 5.0.1      | [集成教程](https://docs.sentry.io/platforms/java/guides/spring-boot/) - [sentry部署教程](https://segmentfault.com/a/1190000038839629) | 已整合 |
| Tencent-OSS | 腾讯对象存储       | 5.6.24                               | [官方文档](https://cloud.tencent.com/document/product/436/35215) | 已整合 |
| Aliyun-OSS  | 阿里对象存储       | 存在多个依赖                               | [官方文档](https://www.alibabacloud.com/help/zh/doc-detail/100624.htm) | 已整合 |
| Tencent-sms | 腾讯短信服务       | 3.1.62  |  | 已整合 |
| Aliyun-sms  | 阿里短信服务       | 4.5.0|  | 已整合 |

### 使用工具推荐

| 工具            | 说明                | 官网                                                  |
| --------------- | ------------------- | ----------------------------------------------------- |
| XMind           | 思维导图设计工具    | https://www.xmind.cn/                                 |
| PowerDesigner   | 数据库设计工具      | http://powerdesigner.de/                              |
| Axure           | 原型设计工具        | https://www.axure.com/                                |
| Draw.io         | 绘图工具      | https://app.diagrams.net/                            |
| Snipaste        | 贴图工具            | https://www.snipaste.com/                             |
| IDEA            | 开发IDE             | https://www.jetbrains.com/idea/download               |
| Typora          | Markdown编辑器      | https://typora.io/                                    |
| RedisDesktop    | redis客户端连接工具 | https://github.com/qishibo/AnotherRedisDesktopManager |
| Termius/FinalShell         | Linux远程连接管理工具   | [Termius](https://www.termius.com/) [FinalShell](https://www.hostbuf.com/)                              |
| Navicat Premium | 数据库连接工具      | http://www.formysql.com/xiazai.html                   |
| ApiPost         | API接口调试工具     | https://www.apipost.cn/                              |
| Typora          | Markdown编辑器      | https://typora.io/                                    |

#### IDEA插件介绍

| 名称              | 说明                                                         |
| ----------------- | ------------------------------------------------------------ |
| Codota            | 人工智能代码索引，根据你的代码习惯学习你的写法，或者在调用某些对象方法时推荐大部分选择的。 |
| Grep Console      | Log颜色管理工具                                              |
| Rainbow Barackets | 括号颜色插件，同级括号相同颜色（不管是小括号大括号中括号），不同等级括号颜色不同。方便看代码 |



