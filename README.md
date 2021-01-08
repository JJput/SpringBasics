# 使用说明
## create模块
### 修改项目包名

使用ProjectPathUpdate类进行更新项目下所有模块的包名，主要修改的信息如下：
* 包路径(com.twj.finance -> com.twj.new)
    * 整个项目路径替换(xxx/com/twj/finance -> xxx/com/twj/new)
    * 所有文件中包名替换(import、mapper)
* pom包
    * 父模块pom中<artifactId>标签修改
    * 子模块pom中所有引用的父模块名称修改

### controller、service、mapper、entity生成流程


#### 1、配置generatorConfig并生成mapper

配置server模块下的generatorConfig.xml中的table标签

    <table tableName="sys_log" domainObjectName="SysLog"/>

 * tableName：数据库表名
 * domainObjectName：生成的对象名称，后面生成controller、service的名称也与这个相关。
 
添加maven运行
在Working directory选择server模块
在Command Line中添加以下内容 

    mybatis-generator:generate -e

>注意配置数据库连接地址与数据库名

#### 2、生成controller、service、entity、dto

使用create模块下的ServerGenerator生成。

ServerGenerator配置说明:

1. 设置generatorConfig.xml路径，生成时根据generatorConfig中的table标签，生成controller等；
2. 配置生成的controller、service、entity、dto路径；
3. 数据库连接地址与数据库名称：生成时需要读取数据库。

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

