



### 工具库
| 技术                 | 说明                | 官网                                           | 参考                                                         | 备注   |
| -------------------- | ------------------- | ---------------------------------------------- | ------------------------------------------------------------ | ------ |
| Lombok               | 简化对象封装工具    | https://github.com/rzwitserloot/lombok         |                                                              | 已整合 |
| Hutool               | Java工具类库        | https://github.com/looly/hutool                |                                                              | 待整合 |
| PageHelper           | MyBatis物理分页插件 | http://git.oschina.net/free/Mybatis_PageHelper |                                                              | 已整合 |
| Swagger-UI           | 文档生成工具        | https://github.com/swagger-api/swagger-ui      | [SpringBoot整合教程](https://developer.ibm.com/zh/languages/spring/articles/j-using-swagger-in-a-spring-boot-project/) | 已整合 |
| Hibernator-Validator | 验证框架            | http://hibernate.org/validator                 |                                                              | 待整合 |
| fastjson             | JSON工具            | https://github.com/alibaba/fastjson/           |                                                              | 已整合 |

### 数据库

| 技术             | 说明           | 官网                                                | 备注   |
| ---------------- | -------------- | --------------------------------------------------- | ------ |
| MyBatis          | ORM框架        | http://www.mybatis.org/mybatis-3/zh/index.html      | 已整合 |
| MyBatisGenerator | 数据层代码生成 | http://www.mybatis.org/generator/index.html         | 已整合 |
| Redis            | 分布式缓存     | https://redis.io/                                   | 已整合 |
| MongoDB          | NoSql数据库    | [https://www.mongodb.com](https://www.mongodb.com/) | 已整合 |
| Druid            | 数据库连接池   | https://github.com/alibaba/druid                    | 已整合 |


### 其他技术
| 技术     | 说明               | 官网                                  | 参考                                                         | 备注   |
| -------- | ------------------ | ------------------------------------- | ------------------------------------------------------------ | ------ |
| RabbitMQ | 消息队列           | https://www.rabbitmq.com/             | [Docker安装](https://michael728.github.io/2019/06/07/docker-rabbitmq-env/) [入门知识](https://www.cnblogs.com/sgh1023/p/11217017.html) SpringBoot整合[[1]](https://blog.csdn.net/qq_38455201/article/details/80308771)[[2]](https://blog.csdn.net/qq_35387940/article/details/100514134) | 已整合 |
| OSS      | 对象存储           | https://cloud.tencent.com/product/cos |                                                              | 已整合 |
| Jenkins  | 自动化部署工具     | https://github.com/jenkinsci/jenkins  |                                                              | 待整合 |
| Kibina   | 日志可视化查看工具 | https://github.com/elastic/kibana     |                                                              | 待整合 |

### 组织结构

```
spirngbasics
├── business 	------ Spring Boot，主要业务模块，基本只放controller，默认配置不连接Cloud
├── create 		------ 无需编译运行，仅用于模块代码生成（controller、service、entity、dto）
├── eureka 		------ Spring Cloud，根据实际情况采用
├── gateway 	------ Spring GateWay，网关模块，用于权限拦截、日志记录等
├── server 		------ 复用模块，主要服务于business。存放service、实体对象、工具类等
├── tencentOssFile 	-- Spring Boot，腾讯对象存储服务，可直接单独运行，也可以作为包添加（pom中添加即可，跟server模块一样）

```

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

## 使用说明

### 修改项目包名

> 暂时只支持mac使用，windows的小伙伴可以将/替换为\\\即可

使用ProjectPathUpdate类进行更新项目下所有模块的包名，主要修改的信息如下：

* 包路径(com.twj.finance -> com.twj.new)
    * 整个项目路径替换(xxx/com/twj/finance -> xxx/com/twj/new)
    * 所有文件中包名替换(import、mapper)
* pom包
    * 父模块pom中<artifactId>标签修改
    * 子模块pom中所有引用的父模块名称修改

### controller、service、mapper、entity生成流程

#### 1、配置`generatorConfig`中的数据库配置，生成mapper

①配置数据连接

在`server`模块下(`server/sec/main/resources/generatorConfig.xml`)，配置`generatorConfig.xml`中的`jdbcConnection`标签

```
<jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"
                        connectionURL="jdbc:mysql://ip:port/数据库名称?characterEncoding=UTF8&amp;autoReconnect=true&amp;allowPublicKeyRetrieval=true&amp;serverTimezone=Asia/Shanghai"
                        userId="账户"
                        password="密码">
</jdbcConnection>

```

②添加`table`标签（要生成的对象）

    <table tableName="sys_log" domainObjectName="SysLog"/>

 * tableName：数据库表名
 * domainObjectName：生成的对象名称，后面生成controller、service的名称也与这个相关。

③添加`Maven`运行`Mybatis生成器`

打开`Edit Configurations`按如下配置添加

![image-20210109175852027](https://i.loli.net/2021/01/09/vc27Lgrz5TNVY1I.png)

* Working directory选择server模块
* Command Line中添加以下内容 

    mybatis-generator:generate -e

![image-20210109175209179](https://i.loli.net/2021/01/09/iks6L3YqWyEfbZJ.png)



#### 2、生成controller、service、entity、dto

使用`create`模块下的`ServerGenerator`生成。

ServerGenerator配置注意事项：

1. 设置generatorConfig.xml路径，生成时根据`jdbcConnection` `table`标签，进行数据库连接和生成对应的controller等；
2. 配置生成路径；
3. 配置包路径；
4. 运行main。

> ftl文件下存放着生成模板。

### 其他工具

#### 1、Excel导入工具

使用create/util/excel下的ImportSubjectUtils类。

主要功能：读取xls数据导入数据库。
实现思路：从sourcePath路径中读取下面所有的xls文件数据，写入指定数据库中。

#### 2、角色权限关系表自动导入

使用create/role的RoleResourceCreate类。

主要功能：读取权限表权限，生成超级管理员权限。
实现思路：连接数据库读取权限表数据，生成超级管理员并设置其权限。

