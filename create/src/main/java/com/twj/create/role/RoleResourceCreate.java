package com.twj.create.role;

import com.twj.create.ServerGenerator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者: JJ
 * @创建时间: 2020/7/31 下午10:42
 * @Version 1.0
 * @描述: 根据资源生成角色权限关系表
 */
public class RoleResourceCreate {

    private static Connection conn;
    private static List<String> roleList = new ArrayList<>();

    public static void main(String[] args) {
        getConnection();
        select();
        String id = "00000";
        String roleId = "00000000";
        for (int i = 0; i < roleList.size(); i++) {
            insertRoleResource(id + (i < 10 ? "0" + i : i), roleId, roleList.get(i));
        }
    }

    private static void select() {
        String sql = "select * from user_resource order by id asc";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 创建会话
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                roleList.add(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertRoleResource(String id, String roleId, String resourceId) {
        int n = 0;
        String sql = "insert into user_role_resource values (?,?,?)";
        PreparedStatement ps = null;
        java.sql.Date pubDate = null;
        try {
            ps = conn.prepareStatement(sql);
            // 设置 ? 的值
            ps.setString(1, id);
            ps.setString(2, roleId);
            ps.setString(3, resourceId);
            // 执行sql
            n = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            // 只生成配置文件中的第一个table节点
            File file = new File(ServerGenerator.GENERATORCONFIGPATH);
            SAXReader reader = new SAXReader();
            //读取xml文件到Document中
            Document doc = reader.read(file);
            //获取xml文件的根节点
            Element rootElement = doc.getRootElement();
            //读取context节点
            Element contextElement = rootElement.element("context");
            //读取数据库配置
            Element mysqlElement = contextElement.elementIterator("jdbcConnection").next();
            conn = DriverManager.getConnection(mysqlElement.attributeValue("connectionURL"),
                    mysqlElement.attributeValue("userId"),
                    mysqlElement.attributeValue("password"));
//            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/finance?characterEncoding=UTF8&autoReconnect=true", "root", "123");
            System.out.println("数据库连接成功");
        } catch (SQLException | DocumentException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
