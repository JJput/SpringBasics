package com.jjput.create;


import cn.hutool.core.date.DateUtil;
import com.jjput.create.util.DbUtil;
import com.jjput.create.util.Field;
import com.jjput.create.util.FreemarkerUtil;
import com.jjput.create.util.PropertiesUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

/**
 * @作者: JJ
 * @创建时间: 2020/11/1 上午12:47
 * @Version 1.0
 * @描述: 根据generatorConfig批量生成controller、service、entity、dto
 */
public class ServerGenerator {

    private static boolean generatorController = true;
    private static boolean generatorService = true;
    private static boolean generatorDto = true;
    private static boolean generatorEntity = true;
    private static boolean generatorMapper = true;


    //generatorConfig.xml位置
    public final static String GENERATORCONFIGPATH = "create/src/main/resources/generatorConfig.xml";

    /**
     * 创建文件前，路径相关配置
     */
    //包名
    public static String PATH_PACKAGE = "com/twj/spirngbasics/" + "server";
    public static String PATH_PROJECT = "server/src/main/java/";
    //mapper生成路径
    public static String PATH_MAPPER = PATH_PROJECT + PATH_PACKAGE + "/mapper/";
    //entity生成路径
    public static String PATH_ENTITY = PATH_PROJECT + PATH_PACKAGE + "/entity/";
    //dto生成路径
    public static String PATH_DTO = PATH_PROJECT + PATH_PACKAGE + "/dto/";
    //service生成路径
    public static String PATH_SERVICE = PATH_PROJECT + PATH_PACKAGE + "/service/";
    //controller生成路径
    public static String PATH_CONTROLLER = "business/src/main/java/com/twj/spirngbasics/business/controller/";
    //resources资源路径
    public static String PATH_RESOURCE = "business/src/main/resources/";


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
    //mapper 导入路径
    public final static String PACKAGE_MAPPER = PACKAGE_BASIS + ".server.mapper";
    //工具包 导入路径
    public final static String PACKAGE_SERVER = "com.twj.spirngbasics.server";

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
        JDBCConnectionConfiguration jdbcConnectionConfiguration = MapperGenerator.initJDBCConfig();
        mysqlUrl = jdbcConnectionConfiguration.getConnectionURL();
        mysqlUser = jdbcConnectionConfiguration.getUserId();
        mysqlPwd = jdbcConnectionConfiguration.getPassword();
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
        System.out.println("开始连接数据库...");
        System.out.println("URL:" + mysqlUrl);
        String tableNameCn = DbUtil.getTableComment(tableName, mysqlUrl, mysqlUser, mysqlPwd);
        String domain = Domain.substring(0, 1).toLowerCase() + Domain.substring(1);
        System.out.println("表：" + tableElement.attributeValue("tableName"));
        System.out.println("Domain：" + tableElement.attributeValue("domainObjectName"));

        List<Field> fieldList = DbUtil.getColumnByTableName(tableName, mysqlUrl, mysqlUser, mysqlPwd);
        Set<String> typeSet = getJavaTypes(fieldList);
        Map<String, Object> map = new HashMap<>();
        map.put("Domain", Domain);
        map.put("domain", domain);
        map.put("tableNameCn", tableNameCn);
        map.put("fieldList", fieldList);
        map.put("typeSet", typeSet);
        setMap(map);

        //获取项目里路径
        String projectPath = System.getProperty("user.dir");
        String entitySavePath = PATH_ENTITY + Domain + ".java";
        String dtoSavePath = PATH_DTO + Domain + "Dto.java";
        String serviceSavePath = PATH_SERVICE + Domain + "Service.java";
        String controllerSavePath = PATH_CONTROLLER + Domain + "Controller.java";
        String mapperSavePath = PATH_MAPPER + Domain + "Mapper.java";

        Scanner scanner = new Scanner(System.in);
        //判断要创建的文件是否存在
        boolean entityFileIsExists = new File(projectPath + "/" + entitySavePath).exists();
        boolean dtoFileIsExists = new File(projectPath + "/" + dtoSavePath).exists();
        boolean serviceFileIsExists = new File(projectPath + "/" + serviceSavePath).exists();
        boolean controllerFileIsExists = new File(projectPath + "/" + controllerSavePath).exists();
        boolean mapperFileIsExists = new File(projectPath + "/" + mapperSavePath).exists();

        //请求是否覆盖
        if ((entityFileIsExists && generatorEntity)
                || (dtoFileIsExists && generatorDto)
                || (serviceFileIsExists && generatorService)
                || (controllerFileIsExists && generatorController)
                || (mapperFileIsExists && generatorMapper)) {
            System.out.println("检测到有文件存在，请问是否覆盖");
            System.out.print("  Y/N:");
            while (true) {
                String res = scanner.nextLine();
                if (!StringUtils.isEmpty(res)) {
                    if (res.equals("Y") || res.equals("y")) {
                        System.out.println("覆盖生成中...");
                        break;
                    } else if (res.equals("N") || res.equals("n")) {
                        System.out.println("程序结束中...");
                        return;
                    } else {
                        System.out.println("输入有误，请重新输入！");
                    }
                } else {
                    System.out.println("请输入内容！");
                }
            }
        }

        //生成mapper
        if (generatorMapper) {
            MapperGenerator.mapperGenerator(PATH_PROJECT, PACKAGE_ENTITY, PATH_PROJECT, PACKAGE_MAPPER, tableName, Domain);
        }

        // 生成entity
        if (generatorEntity) {
            FreemarkerUtil.initConfig("entity.ftl");
            FreemarkerUtil.generator(entitySavePath, map);
        }

        // 生成dto
        if (generatorDto) {
            FreemarkerUtil.initConfig("dto.ftl");
            FreemarkerUtil.generator(dtoSavePath, map);
        }

        // 生成service
        if (generatorService) {
            FreemarkerUtil.initConfig("service.ftl");
            FreemarkerUtil.generator(serviceSavePath, map);
        }

        // 生成controller
        if (generatorController) {
            FreemarkerUtil.initConfig("controller.ftl");
            FreemarkerUtil.generator(controllerSavePath, map);
            addProperties(map);
        }
    }

    /**
     * 给application-request.peoperties添加路径
     *
     * @param map
     */
    private static void addProperties(Map<String, Object> map) {
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            String sourcePath = System.getProperty("user.dir");
            String requestPath = null;
            //若是以/开头 就是macos或linux系统路径
            if (sourcePath.substring(0, 1).equals("/")) {
                requestPath = System.getProperty("user.dir") + "/" + PATH_RESOURCE + "application-request.properties";
            }
            //否则认为是windows路径
            else {
                requestPath = System.getProperty("user.dir") + "/" + PATH_RESOURCE.replace("/", "\\") + "application-request.properties";
            }
            String value = PropertiesUtil.GetValueByKey(requestPath, "request.path." + map.get("domain"));
            if (StringUtils.isEmpty(value)) {
                PropertiesUtil.WriteProperties(requestPath, "request.path." + map.get("domain"), "/" + map.get("domain"));
            } else {
                System.out.println("application-request.peoperties 中" +
                        "\nrequest.path." + map.get("domain") +
                        "\n路径已存在：" + value);
            }

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
        map.put("PACKAGE_SERVER", PACKAGE_SERVER);
        map.put("PACKAGE_MAPPER", PACKAGE_MAPPER);
        map.put("TIME", DateUtil.now());
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
