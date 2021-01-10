package com.twj.create;


import com.twj.create.util.DbUtil;
import com.twj.create.util.Field;
import com.twj.create.util.FreemarkerUtil;
import com.twj.spirngbasics.server.util.DateUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.*;

/**
 * @作者: JJ
 * @创建时间: 2020/11/1 上午12:47
 * @Version 1.0
 * @描述: 根据generatorConfig批量生成controller、service、entity、dto
 */
public class ServerGenerator {


    //generatorConfig.xml位置
    public final static String GENERATORCONFIGPATH = "server/src/main/resources/generator/generatorConfig.xml";

    /**
     * 创建文件前，路径相关配置
     */
    //包名
    public final static String PATH_PACKAGE = "com/twj/spirngbasics/" + "server";
    //entity生成路径
    public final static String PATH_ENTITY = "server/src/main/java/" + PATH_PACKAGE + "/entity/";
    //dto生成路径
    public final static String PATH_DTO = "server/src/main/java/" + PATH_PACKAGE + "/dto/";
    //service生成路径
    public final static String PATH_SERVICE = "server/src/main/java/" + PATH_PACKAGE + "/service/";
    //controller生成路径
    public final static String PATH_CONTROLLER = "business/src/main/java/com/twj/spirngbasics/business/controller/";

    /**
     * 创建文件时，文件内部包路径等参数配置
     */
    //作者名称
    public final static String AUTHOR = "Jun";
    //基本包路径
    public final static String PACKAGE_BASIS = "com.twj.spirngbasics";
    //controller  package路径
    public final static String PACKAGE_CONTROLLER = PACKAGE_BASIS + ".business.controller";
    //dto package路径
    public final static String PACKAGE_DTO = PACKAGE_BASIS + ".server.dto";
    //entity package路径
    public final static String PACKAGE_ENTITY = PACKAGE_BASIS + ".server.entity";
    //service package路径
    public final static String PACKAGE_SERVICE = PACKAGE_BASIS + ".server.service";
    //工具包 导入路径
    public final static String PACKAGE_UTILS = PACKAGE_BASIS + ".server.util";
    //mapper 导入路径
    public final static String PACKAGE_MAPPER = PACKAGE_BASIS + ".server.mapper";
    //异常类 导入路径
    public final static String PACKAGE_EXCEPTION = PACKAGE_BASIS + ".server.exception";

    /**
     * 数据库配置 将直接读取generatorConfig.xml中的配置
     */
    public static String mysqlUrl;
    public static String mysqlUser;
    public static String mysqlPwd;

    public static void main(String[] args) throws Exception {

        // 只生成配置文件中的第一个table节点
        File file = new File(GENERATORCONFIGPATH);
        SAXReader reader = new SAXReader();
        //读取xml文件到Document中
        Document doc = reader.read(file);
        //获取xml文件的根节点
        Element rootElement = doc.getRootElement();
        //读取context节点
        Element contextElement = rootElement.element("context");

        //读取数据库配置
        Element mysqlElement = contextElement.elementIterator("jdbcConnection").next();
        mysqlUrl = mysqlElement.attributeValue("connectionURL");
        mysqlUser = mysqlElement.attributeValue("userId");
        mysqlPwd = mysqlElement.attributeValue("password");
        //取第一个“table”的节点
//     Element  tableElement = contextElement.elementIterator("table").next();
        //取所有节点
        Iterator<Element> tables = contextElement.elementIterator("table");
        while (tables.hasNext()) {
            Element tableElement = tables.next();
            createData(tableElement);
        }
    }

    private static void createData(Element tableElement) throws Exception {
        String Domain = tableElement.attributeValue("domainObjectName");
        String tableName = tableElement.attributeValue("tableName");
        String tableNameCn = DbUtil.getTableComment(tableName);
        String domain = Domain.substring(0, 1).toLowerCase() + Domain.substring(1);
        System.out.println("表：" + tableElement.attributeValue("tableName"));
        System.out.println("Domain：" + tableElement.attributeValue("domainObjectName"));

        List<Field> fieldList = DbUtil.getColumnByTableName(tableName);
        Set<String> typeSet = getJavaTypes(fieldList);
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("tableNameCn", tableNameCn);
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);
        setMap(map);

        // 生成entity
        FreemarkerUtil.initConfig("entity.ftl");
        FreemarkerUtil.generator(PATH_ENTITY + Domain + ".java", map);

        // 生成dto
        FreemarkerUtil.initConfig("dto.ftl");
        FreemarkerUtil.generator(PATH_DTO + Domain + "Dto.java", map);

        // 生成service
        FreemarkerUtil.initConfig("service.ftl");
        FreemarkerUtil.generator(PATH_SERVICE + Domain + "Service.java", map);

        // 生成controller
        FreemarkerUtil.initConfig("controller.ftl");
        FreemarkerUtil.generator(PATH_CONTROLLER + Domain + "Controller.java", map);
        addProperties(map);
    }

    /**
     * 给application-request.peoperties添加路径
     *
     * @param map
     */
    private static void addProperties(Map<String, Object> map) {
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(System.getProperty("user.dir") + "\\business\\src\\main\\resources\\application-request.properties", true);
            writer.write("\nrequest.path." + map.get("domain") + "=/" + map.get("domain"));
            writer.close();
        } catch (Exception e) {
            System.out.println("application-request.peoperties 添加出现错误");
            System.out.println(e);
        }
    }

    private static void setMap(Map<String, Object> map) {
        map.put("PACKAGE_BASIS", PACKAGE_BASIS);
        map.put("PACKAGE_CONTROLLER", PACKAGE_CONTROLLER);
        map.put("PACKAGE_DTO", PACKAGE_DTO);
        map.put("PACKAGE_ENTITY", PACKAGE_ENTITY);
        map.put("PACKAGE_SERVICE", PACKAGE_SERVICE);
        map.put("PACKAGE_UTILS", PACKAGE_UTILS);
        map.put("PACKAGE_MAPPER", PACKAGE_MAPPER);
        map.put("PACKAGE_EXCEPTION", PACKAGE_EXCEPTION);
        map.put("TIME", DateUtils.getDateYmdHms());
        map.put("AUTHOR", AUTHOR);
    }

    /**
     * 获取所有的Java类型，使用Set去重
     */
    private static Set<String> getJavaTypes(List<Field> fieldList) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            set.add(field.getJavaType());
        }
        return set;
    }
}
