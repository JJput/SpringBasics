package com.jjput.create;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 用于生产MBG的代码
 * Created by macro on 2018/4/26.
 */
public class MapperGenerator {

    private static final String PROFILES_SOURCE_PATH = "/business/src/main/resources/";
    private static final String PROFILES_SOURCE_NAME = "application";
    private static final String PROFILES_SOURCE_SUFFIX = ".properties";
    private static final String PROFILES_NAME = "spring.profiles.active";
    private static final String MYSQL_URL_KEY = "spring.datasource.url";
    private static final String MYSQL_USERNAME_KEY = "spring.datasource.username";
    private static final String MYSQL_PASSWORD_KEY = "spring.datasource.password";
    private static final String MYSQL_DRIVER_KEY = "spring.datasource.driver-class-name";

    public static void main(String[] args) throws Exception {
        mapperGenerator();
    }

    public static void mapperGenerator(String entityProject, String entityPackage, String mapperProject, String mapperPackage) throws Exception {
        //MBG 执行过程中的警告信息
        List<String> warnings = new ArrayList<String>();
        //当生成的代码重复时，覆盖原代码
        boolean overwrite = true;

        //读取我们的 MBG 配置文件
        InputStream is = MapperGenerator.class.getResourceAsStream("/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        is.close();

        //覆盖generatorConfig.xml中的jdbc设置
        Context context = config.getContexts().get(0);
        context.setJdbcConnectionConfiguration(initJDBCConfig());
        if (!StringUtils.isEmpty(entityPackage) && !StringUtils.isEmpty(entityProject)) {
            JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
            javaModelGeneratorConfiguration.setTargetPackage(entityPackage);
            javaModelGeneratorConfiguration.setTargetProject(entityProject);
            context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
        }
        if (!StringUtils.isEmpty(mapperProject) && !StringUtils.isEmpty(mapperPackage)) {
            JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
            javaClientGeneratorConfiguration.setTargetPackage(mapperPackage);
            javaClientGeneratorConfiguration.setTargetProject(mapperProject);
            context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        //创建 MBG
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        //执行生成代码
        myBatisGenerator.generate(null);
        //输出警告信息
        for (String warning : warnings) {
            System.out.println(warning);
        }
    }

    public static void mapperGenerator() throws Exception {
        mapperGenerator(null, null, null, null);
    }

    public static JDBCConnectionConfiguration initJDBCConfig() throws Exception {
        //读取Properties配置
        Properties properties = new Properties();
        // 使用InPutStream流读取properties文件
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(System.getProperty("user.dir") +
                        PROFILES_SOURCE_PATH +
                        PROFILES_SOURCE_NAME +
                        PROFILES_SOURCE_SUFFIX));
        properties.load(bufferedReader);
        // 获取key对应的value值
        String[] profilesNames = properties.getProperty(PROFILES_NAME).split(",");
        bufferedReader = new BufferedReader(
                new FileReader(System.getProperty("user.dir") +
                        PROFILES_SOURCE_PATH +
                        PROFILES_SOURCE_NAME +
                        "-" + profilesNames[1] +
                        PROFILES_SOURCE_SUFFIX));
        properties.load(bufferedReader);
        //读取并设置jdbc
        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(properties.getProperty(MYSQL_URL_KEY));
        jdbcConnectionConfiguration.setUserId(properties.getProperty(MYSQL_USERNAME_KEY));
        jdbcConnectionConfiguration.setPassword(properties.getProperty(MYSQL_PASSWORD_KEY));
        jdbcConnectionConfiguration.setDriverClass(properties.getProperty(MYSQL_DRIVER_KEY));
        return jdbcConnectionConfiguration;
    }
}
