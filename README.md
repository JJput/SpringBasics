
## 使用说明


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
├── oss ------                                                                                                                                                                                                                              Spring Boot，腾讯对象存储服务，可直接单独运行，也可以作为包添加（pom中添加即可，跟server模块一样）

```

### 引用库说明

#### 工具库

| 技术                            | 说明             | 版本号      | 参考                                                         | 备注   |
| ------------------------------- | ---------------- | ----------- | ------------------------------------------------------------ | ------ |
| Lombok                          | 简化对象封装工具 | 1.18.12     | [简单介绍](https://www.jianshu.com/p/49a642bdb99a) [详细使用](https://mp.weixin.qq.com/s/Ys6ksYasfUj7TSCGICHM8w) | 已整合 |
| Hutool                          | Java工具类库     | 5.5.7       | [官方教程](https://www.hutool.cn/docs/#/)                    | 已整合 |
| Swagger2 & swagger-bootstrap-ui | 文档生成工具     | 2.9.2       | [SpringBoot整合教程](https://developer.ibm.com/zh/languages/spring/articles/j-using-swagger-in-a-spring-boot-project/) [官方文档](https://swagger.io/docs/) | 已整合 |
| Hibernator-Validator            | 验证框架         | 6.0.9.Final | [SpringBoot整合教程](https://blog.csdn.net/java_collect/article/details/85534054) | 已整合 |
| fastjson                        | JSON工具         | 1.2.60      |                                                              | 已整合 |
| log4j2                          | 日志管理         | 2.12.1      | [SpringBoot整合教程](https://www.cnblogs.com/keeya/p/10101547.html#%E4%B8%BA%E4%BB%80%E4%B9%88%E9%80%89%E7%94%A8log4j2) | 已整合 |

#### 数据库相关

| 技术             | 说明                | 版本号        | 参考                                                         | 备注   |
| ---------------- | ------------------- | ------------- | ------------------------------------------------------------ | ------ |
| MyBatis          | ORM框架             | 1.3.2         |                                                              | 已整合 |
| MyBatisGenerator | 数据层代码生成      | 1.3.7         | 采用DynamicSql风格生成代码[教程](https://mp.weixin.qq.com/s/6skQR5Nq-LZm0AeWc5-xqg) [进阶使用指南](http://139.224.40.241/archives/dynamicsql) | 已整合 |
| PageHelper       | MyBatis物理分页插件 | 1.2.10        |                                                              | 已整合 |
| Redis            | 分布式缓存          | 2.2.8.RELEASE |                                                              | 已整合 |
| MongoDB          | NoSql数据库         | 2.0.0.RELEASE |                                                              | 已整合 |
| Druid            | 数据库连接池        | 1.1.20        |                                                              | 已整合 |

#### 其他技术

| 技术     | 说明               | 版本号                               | 参考                                                         | 备注   |
| -------- | ------------------ | ------------------------------------ | ------------------------------------------------------------ | ------ |
| RabbitMQ | 消息队列           | 2.1.3.RELEASE                        | [Docker安装](https://michael728.github.io/2019/06/07/docker-rabbitmq-env/) [入门知识](https://www.cnblogs.com/sgh1023/p/11217017.html) SpringBoot整合[[1]](https://blog.csdn.net/qq_38455201/article/details/80308771)[[2]](https://blog.csdn.net/qq_35387940/article/details/100514134) | 已整合 |
| OSS      | 腾讯对象存储       | 5.6.24                               | [官方文档](https://cloud.tencent.com/document/product/436/35215) | 已整合 |
| Jenkins  | 自动化部署工具     | https://github.com/jenkinsci/jenkins |                                                              | 待整合 |
| Kibina   | 日志可视化查看工具 | https://github.com/elastic/kibana    |                                                              | 待整合 |



### 开发工具

| 工具            | 说明                | 官网                                                  |
| --------------- | ------------------- | ----------------------------------------------------- |
| XMind           | 思维导图设计工具    | https://www.xmind.cn/                                 |
| PowerDesigner   | 数据库设计工具      | http://powerdesigner.de/                              |
| Axure           | 原型设计工具        | https://www.axure.com/                                |
| ProcessOn       | 流程图绘制工具      | https://www.processon.com/                            |
| Snipaste        | 贴图工具            | https://www.snipaste.com/                             |
| IDEA            | 开发IDE             | https://www.jetbrains.com/idea/download               |
| RedisDesktop    | redis客户端连接工具 | https://github.com/qishibo/AnotherRedisDesktopManager |
| Termius         | Linux远程连接工具   | https://www.termius.com/                              |
| Navicat Premium | 数据库连接工具      | http://www.formysql.com/xiazai.html                   |
| Postman         | API接口调试工具     | https://www.postman.com/                              |
| Typora          | Markdown编辑器      | https://typora.io/                                    |

#### IDEA插件介绍

| 名称              | 说明                                                         |
| ----------------- | ------------------------------------------------------------ |
| Codota            | 人工智能代码索引，根据你的代码习惯学习你的写法，或者在调用某些对象方法时推荐大部分选择的。 |
| Grep Console      | Log颜色管理工具                                              |
| Rainbow Barackets | 括号颜色插件，同级括号相同颜色（不管是小括号大括号中括号），不同等级括号颜色不同。方便看代码！！ |



